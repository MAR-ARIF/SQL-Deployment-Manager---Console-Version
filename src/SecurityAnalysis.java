public class SecurityAnalysis implements AiAnalysis {

    @Override
    public void analyseScript(String script) { //checking security flaws
        System.out.println("=== Security Analysis ===");
            String[] lines = script.split("\n");// breaking into lines for better analysis

            for (String line : lines) {
                if (line.contains("DROP")) {
                    System.out.println("- 'DROP COLUMN' detected, it removes audit trail data, high risk for compliance. Consider before deployment");

                }
                if(line.contains("VARCHAR")){
                    String num = line.substring(line.indexOf("(")+1, line.indexOf(")"));
                    int varCharNum = Integer.parseInt(num); // to calculate length inside varchar
                    if (varCharNum > 150){
                        System.out.println("- VARCHAR length too big, may take more storage. Consider decreasing length!");
                    }
                    if (varCharNum < 20){
                        System.out.println("- VARCHAR length too small, may result in truncating data. Consider increasing it ! .");
                    }
                }
                if (line.contains("ADD COLUMN") && !line.contains("NOT NULL")) {
                    System.out.println("- Added column has no NOT NULL constraint. Could allow bad data.");
                }
                if (line.contains("password") || line.contains("secret")) {
                    System.out.println("- Sensitive data detected. Use HASHED/ENCRYPTED storage instead of plain VARCHAR.");
                }
                if (line.contains("DATETIME")) {
                    System.out.println("- DATETIME used. Consider TIMESTAMP WITH TIMEZONE for security logs.");
                }
                if (line.contains("VARCHAR") && !line.matches(".*VARCHAR\\(\\d+\\).*")) {
                    System.out.println("- VARCHAR without length limit → security risk (SQL injection, overflow).");
                }




            }
    }
}
