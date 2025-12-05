public class StyleAnalysis implements AiAnalysis {
    @Override
    public void analyseScript(String script) {
        System.out.println("=== Style / Best Practice Analysis ===");

        if (!script.equals(script.toUpperCase())) {
            System.out.println("- SQL keywords should be uppercase.");
        }

        if (script.contains("ADD COLUMN") && script.contains(",")) {
            System.out.println("- Group ALTER TABLE operations — good practice.");
        }

        if (script.contains("\t")) {
            System.out.println("- Avoid TAB characters; use spaces for consistency.");
        }

        if (!script.trim().endsWith(";")) {
            System.out.println("- SQL statement should end with a semicolon.");
        }

        if (script.contains("  ")) {
            System.out.println("- Extra spaces detected — cleanup recommended.");
        }

        for (String line : script.split("\n")) {
            if (line.length() > 120) {
                System.out.println("- A line exceeds 120 characters — break into smaller lines.");
            }
        }

        System.out.println();
    }
}


