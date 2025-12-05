import java.sql.SQLOutput;

public class PerformanceAnalysis implements AiAnalysis{
    @Override
    public void analyseScript (String script){ // checking performance flaws
        System.out.println("=== Performance Analysis ===");
        int alterCount = script.split("ADD COLUMN").length + script.split("DROP COLUMN").length + script.split("MODIFY COLUMN").length;
        if (alterCount > 4){ //checking if too many operations under one ALTER command
            System.out.println("- Too many ALTER operations. Consider batching changes.");
        }
        String[] lines = script.split("\n"); // breaking into lines for better analysis

        for(String line : lines){
            if (line.contains("SELECT *")) {
                System.out.println("- Avoid SELECT * - choose only needed columns");
            }

            if (line.contains("ADD COLUMN") && script.contains("NULL")) {
                System.out.println("- Many nullable columns may slow down scans.");
            }


            if (line.contains("ADD COLUMN") &&
                    (line.contains("email") || line.contains("phone") || line.contains("user_id"))) {
                System.out.println("- Consider adding an INDEX for faster lookups.");
            }
            if (line.contains("MODIFY") && line.contains("INT")) {
                System.out.println("- Verify integer changes carefully for overflow risk.");
            }
            if (line.contains("ADD COLUMN") && !line.contains("DEFAULT")) {
                System.out.println("- New column added without DEFAULT value; may cause slower INSERTs.");
            }
            if (line.contains("MODIFY") && line.contains("BIGINT")) {
                System.out.println("- Column converted to BIGINT. This increases storage size per row (8 bytes instead of 4).");
            }



        }






    }
}
