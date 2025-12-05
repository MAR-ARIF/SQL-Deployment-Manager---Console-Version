import java.util.HashMap;

public class CustomSchemaDiff extends SchemaDiff {
    private String customTargetSchema;
    private HashMap<String, String> customTargetSchemaMap = new HashMap<>();

    public CustomSchemaDiff(String customTargetSchema) {
        this.customTargetSchema = customTargetSchema;
        parseCustomTargetSchema(customTargetSchema,customTargetSchemaMap);

    }
    public HashMap<String, String> getCustomTargetSchemaMap() {
        return customTargetSchemaMap;
    }
    //parsing inputted custom schema string into map for better comparison
    private void parseCustomTargetSchema(String schema,HashMap <String,String> map) {
        String[] lines = schema.split("\n");

        try {
            for (String line : lines) {
                line = line.trim(); // trimming extra spaces

                if (line.startsWith("CREATE ") || line.startsWith("(") || line.startsWith(");")) {
                    continue;
                } // skips non-column lines

                if (line.endsWith(",")){
                    line = line.substring(0, line.length()-1); // removing comma
                }
                String[] fields = line.split(" ",2);
                if (fields.length == 2) {
                    map.put(fields[0], fields[1]); // putting as key value pair in map
                }


            }
        }  catch (Exception e) {
            System.err.println("Invalid schema " + e.getMessage());
        }




    }


}
