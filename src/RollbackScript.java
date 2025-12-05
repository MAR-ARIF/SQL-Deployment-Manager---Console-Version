import java.util.ArrayList;
import java.util.List;

public class RollbackScript implements AiAnalysis {

    @Override
    public void analyseScript(String script) {
    System.out.println("====== Rollback Script ======");
    String[] lines = script.split("\n");
    List<String> rollbackLines = new ArrayList<>(); // a list to save rollback script lines
    rollbackLines.add("ALTER TABLE users"); // adding first line
    for (String line : lines) {
        if (line.contains("ADD")) {
            String line2 = line.replace("ADD", "DROP"); //if the column added then it goes back to drop
            rollbackLines.add(line2);
        }
        if (line.contains("DROP")) {
            String line2 = line.replace("DROP", "ADD"); //if the column dropped then it goes back to add
            rollbackLines.add(line2);
        }
        if (line.contains("MODIFY")) {
            for (String key : SchemaDiff.getCurSchemaMap().keySet()) { // searching if the modified column is there in original script
                if (line.contains(key)) {
                    String line2 = "    MODIFY COLUMN " + key +" "+ SchemaDiff.getCurSchemaMap().get(key);
                    rollbackLines.add(line2);

                }
            }


        }

    }
    for (String line : rollbackLines) {
        System.out.println(line);
    }


    }
}
