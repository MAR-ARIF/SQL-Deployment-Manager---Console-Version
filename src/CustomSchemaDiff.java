import java.util.HashMap;

// This class extends SchemaDiff to support user-defined (custom) schemas
// It allows comparison between the current schema and a custom target schema
public class CustomSchemaDiff extends SchemaDiff {

    // Stores the custom target schema as a raw SQL string
    private String customTargetSchema;

    // HashMap used to store parsed column definitions from the custom schema
    // Key = column name, Value = column definition
    private HashMap<String, String> customTargetSchemaMap = new HashMap<>();

    // Constructor accepts a custom schema provided by the user
    // It immediately parses the schema for comparison
    public CustomSchemaDiff(String customTargetSchema) {
        this.customTargetSchema = customTargetSchema;
        parseCustomTargetSchema(customTargetSchema, customTargetSchemaMap);
    }

    // Getter method to return the parsed custom target schema map
    public HashMap<String, String> getCustomTargetSchemaMap() {
        return customTargetSchemaMap;
    }

    // This method parses the custom schema string into a HashMap
    // Parsing is done to allow easier column-by-column comparison
    private void parseCustomTargetSchema(String schema, HashMap<String, String> map) {

        // Splitting the schema string into individual lines
        String[] lines = schema.split("\n");

        try {
            for (String line : lines) {
                line = line.trim(); // Trimming extra spaces for clean parsing

                // Skipping lines that are not column definitions
                if (line.startsWith("CREATE ")
                        || line.startsWith("(")
                        || line.startsWith(");")) {
                    continue;
                }

                // Removing trailing comma from column definitions
                if (line.endsWith(",")) {
                    line = line.substring(0, line.length() - 1);
                }

                // Splitting column name and data type
                String[] fields = line.split(" ", 2);

                // Storing column name and type as key-value pair
                if (fields.length == 2) {
                    map.put(fields[0], fields[1]);
                }
            }
        } catch (Exception e) {
            // Handles any parsing errors gracefully
            System.err.println("Invalid schema " + e.getMessage());
        }
    }
}
