import java.util.HashMap;
import java.util.Set;

// This class is responsible for analysing SQL ALTER scripts
// It checks the script for ADD, DROP, and MODIFY operations
// and prints warnings or confirmations based on schema impact
public class SqlScriptAnalysis {

    // Predefined SQL script used for schema change analysis
    // This represents a typical ALTER TABLE command
    private static final String sqlScript = """
            ALTER TABLE users
                MODIFY COLUMN email VARCHAR(150),
                ADD COLUMN phone VARCHAR(20),
                ADD COLUMN last_login DATETIME,
                DROP COLUMN created_at;
            
            """;

    // Set of valid SQL data types
    // Used to validate column types when adding new columns
    public Set<String> validTypes = Set.of(
            "INT", "INTEGER", "BIGINT", "SMALLINT",
            "VARCHAR", "CHAR", "TEXT",
            "DATE", "DATETIME", "TIMESTAMP",
            "BOOLEAN", "FLOAT", "DOUBLE", "DECIMAL"
    );

    // Static getter method to return the predefined SQL script
    // Static access allows usage without creating an object
    public static String getSqlScript() {
        return sqlScript;
    }

    // This method analyses the given SQL script line by line
    // It validates syntax and detects schema changes
    public void analyseScript(String script) {

        System.out.println("----------SQL Script Analysis----------");

        // Basic syntax validation for ALTER TABLE operations
        if (script.startsWith("ALTER TABLE users")
                && (script.contains("MODIFY")
                || script.contains("ADD COLUMN")
                || script.contains("DROP COLUMN"))) {

            // Splitting the SQL script into individual lines
            String[] lines = script.split("\n");

            for (String line : lines) {
                line = line.trim(); // Removing extra whitespace

                // Handling ADD COLUMN statements
                if (line.startsWith("ADD COLUMN")) {

                    // Multiple columns can be added using commas
                    for (String adds : line.split(",")) {

                        // Extracts column name and type
                        String add = adds.substring("ADD COLUMN".length()).trim();
                        String[] parts = add.split("\\s+", 2);

                        if (parts.length == 2) {
                            String colName = parts[0].trim();
                            String colType = parts[1].trim();

                            // Extracting base SQL type (e.g., VARCHAR from VARCHAR(150))
                            String baseType = colType.contains("(")
                                    ? colType.substring(0, colType.indexOf("("))
                                    : colType;

                            // Validating the SQL data type
                            if (validTypes.contains(baseType)) {
                                System.out.println("**New Column added : "
                                        + colName + " " + colType
                                        + ", defaults to null if not filled");
                            } else {
                                System.out.println("Invalid column type");
                            }
                        } else {
                            System.out.println("No column type entered or Invalid type.");
                        }
                    }

                    // Handling DROP COLUMN statements
                } else if (line.startsWith("DROP COLUMN")) {

                    for (String drops : line.split(",")) {

                        // Extracting column name
                        String drop = drops.substring("DROP COLUMN".length()).trim();
                        drop = drop.replace(";", "").trim();

                        // Checking if column exists in original schema
                        if (!SchemaDiff.getCurSchemaMap().containsKey(drop.toLowerCase())) {
                            System.out.println("The column " + drop
                                    + " does not exist in original Schema");
                        } else {
                            System.out.println("**(HIGH RISK)- the column "
                                    + drop
                                    + " removed completely, which means all data will be erased!!!");
                        }
                    }

                    // Handling MODIFY COLUMN statements
                } else if (line.startsWith("MODIFY COLUMN")) {

                    for (String modifies : line.split(",")) {

                        // Extracting column name and new type
                        String modify = modifies.substring("MODIFY COLUMN".length()).trim();
                        String[] parts = modify.split("\\s+", 2);

                        if (parts.length == 2) {
                            String colName = parts[0].trim();
                            String colType = parts[1].trim();

                            // Checking if column exists and definition is changing
                            if (SchemaDiff.getCurSchemaMap().containsKey(colName)
                                    && !SchemaDiff.getCurSchemaMap().get(colName).equals(colType)) {

                                System.out.println("**The column "
                                        + colName
                                        + " modified from "
                                        + SchemaDiff.getCurSchemaMap().get(colName)
                                        + " to "
                                        + colType);

                                // Special validation for email column size reduction
                                if (colName.equalsIgnoreCase("email")
                                        && colType.startsWith("VARCHAR")) {

                                    // Extracting VARCHAR size
                                    String colTypeNum = colType.substring(
                                            colType.indexOf("(") + 1,
                                            colType.indexOf(")")
                                    );

                                    int varCharNum = Integer.parseInt(colTypeNum);

                                    if (varCharNum < 100) {
                                        System.out.println("HIGH RISK: The column email's length decreased from 100 to "
                                                + varCharNum);
                                        System.out.println("Data may be truncated");
                                    }
                                }

                                // Special validation for username column size reduction
                                if (colName.equalsIgnoreCase("username")
                                        && colType.startsWith("VARCHAR")) {

                                    String colTypeNum = colType.substring(
                                            colType.indexOf("(") + 1,
                                            colType.indexOf(")")
                                    );

                                    int varCharNum = Integer.parseInt(colTypeNum);

                                    if (varCharNum < 50) {
                                        System.out.println("HIGH RISK: The column username's length decreased from 50 to "
                                                + varCharNum
                                                + ". Data may be truncated");
                                    }
                                }

                            } else {
                                System.out.println("Invalid column name or type");
                            }

                        } else {
                            System.out.println("No column type entered or Invalid type.");
                        }
                    }
                }
            }

        } else {
            // If script does not meet basic ALTER TABLE requirements
            System.out.println("Invalid SQL Script");
        }
    }
}





