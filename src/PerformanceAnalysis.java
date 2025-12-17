import java.sql.SQLOutput;

// This class performs performance-related analysis on SQL scripts
// It checks for operations that may negatively impact database performance
public class PerformanceAnalysis implements AiAnalysis {

    // This method analyses the SQL script for potential performance issues
    // It is an implementation of the AiAnalysis interface
    @Override
    public void analyseScript(String script) { // checking performance flaws

        System.out.println("=== Performance Analysis ===");

        // Counting ALTER operations to detect too many changes in one command
        int alterCount = script.split("ADD COLUMN").length
                + script.split("DROP COLUMN").length
                + script.split("MODIFY COLUMN").length;

        // Warning if too many ALTER operations are detected
        if (alterCount > 4) {
            System.out.println("- Too many ALTER operations. Consider batching changes.");
        }

        // Splitting script into lines for more detailed analysis
        String[] lines = script.split("\n");

        for (String line : lines) {

            // Checking for inefficient SELECT queries
            if (line.contains("SELECT *")) {
                System.out.println("- Avoid SELECT * - choose only needed columns");
            }

            // Warning about multiple nullable columns affecting performance
            if (line.contains("ADD COLUMN") && script.contains("NULL")) {
                System.out.println("- Many nullable columns may slow down scans.");
            }

            // Suggesting index creation for frequently searched columns
            if (line.contains("ADD COLUMN")
                    && (line.contains("email")
                    || line.contains("phone")
                    || line.contains("user_id"))) {

                System.out.println("- Consider adding an INDEX for faster lookups.");
            }

            // Warning about integer modifications and overflow risks
            if (line.contains("MODIFY") && line.contains("INT")) {
                System.out.println("- Verify integer changes carefully for overflow risk.");
            }

            // Warning when new columns are added without a DEFAULT value
            if (line.contains("ADD COLUMN") && !line.contains("DEFAULT")) {
                System.out.println("- New column added without DEFAULT value; may cause slower INSERTs.");
            }

            // Warning about increased storage when converting to BIGINT
            if (line.contains("MODIFY") && line.contains("BIGINT")) {
                System.out.println("- Column converted to BIGINT. This increases storage size per row (8 bytes instead of 4).");
            }
        }
    }
}

