import java.util.Scanner;

// This class provides a menu for AI-based SQL analysis
// It allows users to run performance, security, style, and rollback analysis
public class AIAnalysisMenu {

    // This method displays the AI analysis menu
    // and handles user interaction
    public static void menu() {

        Scanner sc = new Scanner(System.in);

        // Loop keeps the menu active until the user exits
        while (true) {

            // Displaying AI analysis options
            System.out.println("Select an option: ");
            System.out.println(
                    "1.View AI Analysis For Predefined script\n" +
                            "2.View AI Analysis for your latest custom Script\n" +
                            "3. Rollback Script generator for predefined Script\n" +
                            "4.Rollback Script generator for your latest custom Script.\n" +
                            "5.Exit"
            );

            // Creating analysis objects
            PerformanceAnalysis performanceAnalysis = new PerformanceAnalysis();
            SecurityAnalysis securityAnalysis = new SecurityAnalysis();
            StyleAnalysis styleAnalysis = new StyleAnalysis();
            RollbackScript rollbackScript = new RollbackScript();

            // Reading the latest custom SQL script from file
            FileHandler f = new FileHandler("Latest Custom SQL Script.txt");
            String script = f.readFile();

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    // Running AI analysis on predefined SQL script
                    performanceAnalysis.analyseScript(SqlScriptAnalysis.getSqlScript());
                    securityAnalysis.analyseScript(SqlScriptAnalysis.getSqlScript());
                    styleAnalysis.analyseScript(SqlScriptAnalysis.getSqlScript());
                    break;

                case 2:
                    // Running AI analysis on user-provided custom SQL script
                    performanceAnalysis.analyseScript(script);
                    securityAnalysis.analyseScript(script);
                    styleAnalysis.analyseScript(script);
                    break;

                case 3:
                    // Generating rollback script for predefined SQL script
                    rollbackScript.analyseScript(SqlScriptAnalysis.getSqlScript());
                    break;

                case 4:
                    // Generating rollback script for latest custom SQL script
                    rollbackScript.analyseScript(script);
                    break;

                case 5:
                    // Exiting the AI analysis menu
                    return;

                default:
                    System.out.println("Invalid choice. Try again");
            }
        }
    }
}
