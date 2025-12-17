import java.util.HashMap;

// This class is responsible for comparing two database schemas
// It identifies added, removed, and modified columns between schemas
public class SchemaDiff {

    // Making both currentSchema and targetSchema final
    // because schema definitions should not change once initialized

    // This represents the current (existing) database schema
    protected final String currentSchema = """
        CREATE TABLE users (
            id INT PRIMARY KEY,
            username VARCHAR(50),
            email VARCHAR(100),
            created_at DATETIME,
            is_active BOOLEAN
        );
    """;

    // This represents the proposed / target database schema
    protected final String targetSchema = """
        CREATE TABLE users (
              id INT PRIMARY KEY,
              username VARCHAR(50),
              email VARCHAR(150),
              phone VARCHAR(20),
              last_login DATETIME,
              is_active BOOLEAN
        );
    """;

    /* In the proposed schema the changes are:
       ~ the column 'email' updated with maximum characters increased to 150 from 100
       - the column 'created_at' removed
       + the column 'phone' added
       + the column 'last_login' added
    */

    // HashMap used to store the current schema columns and their data types
    // Key = column name, Value = column definition
    // This is static so it can be accessed directly from other classes if needed
    private static HashMap<String, String> curSchema = new HashMap<>() {{
        put("id", "INT PRIMARY KEY");
        put("username", "VARCHAR(50)");
        put("email", "VARCHAR(100)");
        put("created_at", "DATETIME");
        put("is_active", "BOOLEAN");
    }};

    // HashMap used to store the target schema columns and their data types
    // This represents the updated version of the schema
    private HashMap<String, String> tarSchema = new HashMap<>() {{
        put("id", "INT PRIMARY KEY");
        put("username", "VARCHAR(50)");
        put("email", "VARCHAR(150)");
        put("phone", "VARCHAR(20)");
        put("last_login", "DATETIME");
        put("is_active", "BOOLEAN");
    }};

    // Getter method to return the current schema SQL definition
    public String getCurrentSchema() {
        return currentSchema;
    }

    // Getter method to return the target schema SQL definition
    public String getTargetSchema() {
        return targetSchema;
    }

    // Getter method to return the target schema as a HashMap
    // Used for comparison logic
    public HashMap<String, String> getTarSchemaMap() {
        return tarSchema;
    }

    // Static getter method to return the current schema HashMap
    // Static access allows usage without creating an object
    public static HashMap<String, String> getCurSchemaMap() {
        return curSchema;
    }

    // This method compares the current schema with the target schema
    // It identifies added, removed, and modified columns
    public void compareSchemas(HashMap<String, String> targSchema) {

        // Validation check to avoid null pointer exceptions
        // Ensures schemas are valid before comparison
        if (curSchema == null || targSchema == null || curSchema.isEmpty() || targSchema.isEmpty()) {
            System.err.println("❌ Error: Target schemas is empty or invalid.");
            return;
        }

        System.out.println("\n===== SCHEMA COMPARISON =====\n");

        // Checking for newly added columns in the target schema
        System.out.println("-----Added Columns-----");
        for (String col : targSchema.keySet()) {
            if (!curSchema.containsKey(col)) {
                System.out.println("\n+ Added column: " + col);
            }
        }

        // Checking for columns removed from the current schema
        System.out.println("\n-----Removed Columns-----");
        for (String col : curSchema.keySet()) {
            if (!targSchema.containsKey(col)) {
                System.out.println("\n- Removed column: " + col);
            }
        }

        // Checking for columns that exist in both schemas but have changed definitions
        System.out.println("\n-----Modified Columns-----\n");
        for (String col : curSchema.keySet()) {
            if (targSchema.containsKey(col)
                    && !curSchema.get(col).equals(targSchema.get(col))) {

                // Displays old definition and new definition for clarity
                System.out.println("~ Modified column: "
                        + curSchema.get(col) + " --> " + targSchema.get(col));
            }
        }
    }
}
