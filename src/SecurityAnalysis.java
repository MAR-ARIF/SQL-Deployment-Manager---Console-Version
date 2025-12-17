// This class performs security-related analysis on SQL scripts
// It checks for common security risks in schema changes
public class SecurityAnalysis implements AiAnalysis {

    // This method analyses the SQL script for potential security flaws
    // It is required by the AiAnalysis interface
    @Override
    public void analyseScript(String script) { // checking security flaws

        System.out.println("=== Security Analysis ===");

        // Splitting the SQL script into lines for detailed inspection
        String[] lines = script.split("\n");

        for (String line : lines) {

            // Detects DROP operations which may remove important or audit-related data
            if (line.contains("DROP")) {
                System.out.println("- 'DROP COLUMN' detected, it removes audit trail data, high risk for compliance. Consider before deployment");
            }

            // Checks VARCHAR length to prevent data truncation or excessive storage usage
            if (line.contains("VARCHAR")) {
                String num = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
                int varCharNum = Integer.parseInt(num); // extracting length value from VARCHAR

                if (varCharNum > 150) {
                    System.out.println("- VARCHAR length too big, may take more storage. Consider decreasing length!");
                }

                if (varCharNum < 20) {
                    System.out.println("- VARCHAR length too small, may result in truncating data. Consider increasing it!");
                }
            }

            // Warns when columns are added without NOT NULL constraint
            if (line.contains("ADD COLUMN") && !line.contains("NOT NULL")) {
                System.out.println("- Added column has no NOT NULL constraint. Could allow bad data.");
            }

            // Detects sensitive fields that should not be stored in plain text
            if (line.contains("password") || line.contains("secret")) {
                System.out.println("- Sensitive data detected. Use HASHED/ENCRYPTED storage instead of plain VARCHAR.");
            }

            // Suggests safer time data types for security and auditing
            if (line.contains("DATETIME")) {
                System.out.println("- DATETIME used. Consider TIMESTAMP WITH TIMEZONE for security logs.");
            }

            // Checks for VARCHAR usage without explicit length definition
            if (line.contains("VARCHAR") && !line.matches(".*VARCHAR\\(\\d+\\).*")) {
                System.out.println("- VARCHAR without length limit → security risk (SQL injection, overflow).");
            }
        }
    }
}
