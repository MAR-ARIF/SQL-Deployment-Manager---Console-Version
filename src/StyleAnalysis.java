// This class performs style and best-practice analysis on SQL scripts
// It checks formatting, readability, and SQL coding standards
public class StyleAnalysis implements AiAnalysis {

    // This method analyses the SQL script for style and best-practice issues
    // It is an implementation of the AiAnalysis interface
    @Override
    public void analyseScript(String script) {

        System.out.println("=== Style / Best Practice Analysis ===");

        // Checking if SQL keywords are written in uppercase
        if (!script.equals(script.toUpperCase())) {
            System.out.println("- SQL keywords should be uppercase.");
        }

        // Detecting grouped ALTER TABLE operations (considered good practice)
        if (script.contains("ADD COLUMN") && script.contains(",")) {
            System.out.println("- Group ALTER TABLE operations — good practice.");
        }

        // Checking for TAB characters which reduce formatting consistency
        if (script.contains("\t")) {
            System.out.println("- Avoid TAB characters; use spaces for consistency.");
        }

        // Ensuring the SQL statement ends with a semicolon
        if (!script.trim().endsWith(";")) {
            System.out.println("- SQL statement should end with a semicolon.");
        }

        // Detecting unnecessary extra spaces
        if (script.contains("  ")) {
            System.out.println("- Extra spaces detected — cleanup recommended.");
        }

        // Checking for overly long lines that reduce readability
        for (String line : script.split("\n")) {
            if (line.length() > 120) {
                System.out.println("- A line exceeds 120 characters — break into smaller lines.");
            }
        }

        // Adding a blank line for cleaner output separation
        System.out.println();
    }
}
