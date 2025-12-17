import java.util.ArrayList;
import java.util.List;

// This class generates a rollback SQL script
// It reverses the effects of an ALTER TABLE operation
public class RollbackScript implements AiAnalysis {

    // This method analyses the given SQL script
    // and produces a rollback script to undo the changes
    @Override
    public void analyseScript(String script) {

        System.out.println("====== Rollback Script ======");

        // Splitting the original SQL script into individual lines
        String[] lines = script.split("\n");

        // List used to store rollback SQL statements
        List<String> rollbackLines = new ArrayList<>();

        // Adding the base ALTER TABLE statement
        rollbackLines.add("ALTER TABLE users");

        // Iterating through each line of the original script
        for (String line : lines) {

            // If a column was added, rollback should drop that column
            if (line.contains("ADD")) {
                String line2 = line.replace("ADD", "DROP");
                rollbackLines.add(line2);
            }

            // If a column was dropped, rollback should add it back
            if (line.contains("DROP")) {
                String line2 = line.replace("DROP", "ADD");
                rollbackLines.add(line2);
            }

            // If a column was modified, rollback restores the original definition
            if (line.contains("MODIFY")) {

                // Looping through original schema columns
                for (String key : SchemaDiff.getCurSchemaMap().keySet()) {

                    // Checking if the modified column exists in the original schema
                    if (line.contains(key)) {

                        // Rebuilding the MODIFY statement using the original column definition
                        String line2 =
                                "    MODIFY COLUMN " + key + " " + SchemaDiff.getCurSchemaMap().get(key);

                        rollbackLines.add(line2);
                    }
                }
            }
        }

        // Printing the generated rollback script
        for (String line : rollbackLines) {
            System.out.println(line);
        }
    }
}
