import java.util.HashMap;
import java.util.Set;

public class SqlScriptAnalysis {
    private static final String sqlScript = """
            ALTER TABLE users
                MODIFY COLUMN email VARCHAR(150),
                ADD COLUMN phone VARCHAR(20),
                ADD COLUMN last_login DATETIME,
                DROP COLUMN created_at;
            
            """; // sql script for predefined schema change
    public Set<String> validTypes = Set.of( // to check if valid type entered when adding column
            "INT", "INTEGER", "BIGINT", "SMALLINT",
            "VARCHAR", "CHAR", "TEXT",
            "DATE", "DATETIME", "TIMESTAMP",
            "BOOLEAN", "FLOAT", "DOUBLE", "DECIMAL"
    );
    public static String getSqlScript() {
        return sqlScript; //static to access without object
    }

    public void analyseScript (String script){
        System.out.println("----------SQL Script Analysis----------");
        // the following conditions check syntax
        if (script.startsWith("ALTER TABLE users") && (script.contains("MODIFY")
                || script.contains("ADD COLUMN") || script.contains("DROP COLUMN") )) {


            String[] lines = script.split("\n"); // splitting into lines
            for(String line : lines){
                line = line.trim();
                if (line.startsWith("ADD COLUMN")) {
                    for(String adds : line.split(",")) {
                        String add = adds.substring("ADD COLUMN".length()).trim(); // gets the column name and type
                        String[] parts = add.split("\\s+" , 2);
                        if(parts.length == 2){
                            String colName = parts[0].trim();
                            String colType = parts[1].trim();
                            String baseType = colType.contains("(")
                                    ? colType.substring(0, colType.indexOf("("))
                                    : colType;
                            if (validTypes.contains(baseType)) {
                                System.out.println("**New Column added : " + colName + " " + colType+", defaults to null if not filled");
                            } else {
                                System.out.println("Invalid column type");
                            }
                        } else{
                            System.out.println("No column type entered or Invalid type.");
                        }
                    }


                } else if (line.startsWith("DROP COLUMN")) {
                    for(String drops : line.split(",")) {
                        String drop = drops.substring("DROP COLUMN".length()).trim(); // gets the column name
                        //checks if the column exist in original schema
                        drop = drop.replace(";", "").trim();

                        if (!SchemaDiff.getCurSchemaMap().containsKey(drop.toLowerCase())) {
                            System.out.println("The column " + drop + " does not exist in original Schema");
                        } else{
                            System.out.println("**(HIGH RISK)- the column " + drop + " removed completely, which means all data will be erased!!!");
                        }
                    }
                } else if (line.startsWith("MODIFY COLUMN")) {
                    for(String modifies : line.split(",")) {
                        String modify = modifies.substring("MODIFY COLUMN".length()).trim();
                        String[] parts = modify.split("\\s+" , 2);
                        if(parts.length == 2){
                            String colName = parts[0].trim();
                            String colType = parts[1].trim();
                            //checks if column name and type exist in original schema
                            if (SchemaDiff.getCurSchemaMap().containsKey(colName) && !SchemaDiff.getCurSchemaMap().get(colName).equals(colType)){
                                System.out.println("**The column " + colName + " modified from " + SchemaDiff.getCurSchemaMap().get(colName) +" to " + colType);
                                if (colName.equalsIgnoreCase("email") && colType.startsWith("VARCHAR")) {
                                    String colTypeNum = colType.substring(colType.indexOf("(") + 1, colType.indexOf(")"));
                                    //taking the number inside VARCHAR
                                    int varCharNum =  Integer.parseInt(colTypeNum);
                                    if (varCharNum < 100) {
                                        System.out.println("HIGH RISK: The column email's length decreased from 100 to" + varCharNum);
                                        System.out.println("Data may be truncated");
                                    }
                                }
                                if (colName.equalsIgnoreCase("username") && colType.startsWith("VARCHAR")) {
                                    String colTypeNum = colType.substring(colType.indexOf("(") + 1, colType.indexOf(")"));
                                    //taking the number inside VARCHAR
                                    int varCharNum =  Integer.parseInt(colTypeNum);
                                    if (varCharNum < 50) {
                                        System.out.println("HIGH RISK: The column username's length decreased from 50 to" + varCharNum +". Data may be truncated");

                                    }
                                }


                            } else{
                                System.out.println("Invalid column name or type");
                            }

                        } else{
                            System.out.println("No column type entered or Invalid type.");
                        }


                    }
                }


                }
            }  else {
            System.out.println("Invalid SQL Script");
        }



    }

    }




