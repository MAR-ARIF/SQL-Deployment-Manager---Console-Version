import java.util.HashMap;

public class SchemaDiff {
    // making both currentSchema and targetSchema final as it won't change
    //this is the current schema version
  protected final String currentSchema ="""
    CREATE TABLE users (
        id INT PRIMARY KEY,
        username VARCHAR(50),
        email VARCHAR(100),
        created_at DATETIME,
        is_active BOOLEAN
    );
   """;// this one is the proposed schema version
  protected final String targetSchema ="""
    CREATE TABLE users (
          id INT PRIMARY KEY,
          username VARCHAR(50),
          email VARCHAR(150),
          phone VARCHAR(20),
          last_login DATETIME,
          is_active BOOLEAN
    );
   """; /* In the proposed schema the changes are:
            ~ the column 'email' updated with maximum characters increased to 150 from 100
            - the column 'created_at' removed
            + the column 'phone' added
            + the column 'is_active' added
        */

    // Two hashmap for comparing both schemas
    // putting key value pairs inside both hashmaps to compare
    private static HashMap<String, String> curSchema = new HashMap<>() {{ //making it static to access in SqlScriptAnalysis class
        put("id", "INT PRIMARY KEY");
        put("username", "VARCHAR(50)");
        put("email", "VARCHAR(100)");
        put("created_at", "DATETIME");
        put("is_active", "BOOLEAN");
    }};

    private HashMap<String, String> tarSchema = new HashMap<>() {{
        put("id", "INT PRIMARY KEY");
        put("username", "VARCHAR(50)");
        put("email", "VARCHAR(150)");
        put("phone", "VARCHAR(20)");
        put("last_login", "DATETIME");
        put("is_active", "BOOLEAN");
    }};




    public String getCurrentSchema() {
        return currentSchema;
    }
    public String getTargetSchema() {
        return targetSchema;
    }
    public HashMap <String, String> getTarSchemaMap() {
        return tarSchema;
    }
    public static HashMap <String, String> getCurSchemaMap() {
        return curSchema;
    }

    public void compareSchemas(HashMap <String, String> targSchema) {
        if (curSchema == null || targSchema == null || curSchema.isEmpty() || targSchema.isEmpty()) {
            System.err.println("❌ Error: Target schemas is empty or invalid.");
            return;
        }

        System.out.println("\n===== SCHEMA COMPARISON =====\n");
        //checking added columns
        System.out.println("-----Added Columns-----");
        for(String col : targSchema.keySet()) {
            if(!curSchema.containsKey(col)){
                System.out.println("\n+ Added column: " + col);
            }
        }

        //checking removed columns
        System.out.println("\n-----Removed Columns-----");
        for(String col : curSchema.keySet()) {
            if(!targSchema.containsKey(col)){
                System.out.println("\n- Removed column: " + col);
            }
        }

        //Checking Modified columns
        System.out.println("\n-----Modified Columns-----\n");
        for(String col : curSchema.keySet()) {
            if(targSchema.containsKey(col) && !curSchema.get(col).equals(targSchema.get(col))){
                System.out.println("~ Modified column: " + curSchema.get(col) + "-->" + targSchema.get(col));
            }
        }

    }
}
