import java.util.Scanner;

public class AIAnalysisMenu {

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("Select an option: ");
            System.out.println("1.View AI Analysis For Predefined script\n" +
                    "2.View AI Analysis for your latest custom Script\n3. Rollback Script generator for " +
                    "predefined Script\n4.Rollback Script generator for your latest custom Script.\n5.Exit");
            PerformanceAnalysis performanceAnalysis = new PerformanceAnalysis();
            SecurityAnalysis securityAnalysis = new SecurityAnalysis();
            StyleAnalysis styleAnalysis = new StyleAnalysis();
            RollbackScript rollbackScript = new RollbackScript();
            FileHandler f = new FileHandler("Latest Custom SQL Script.txt");
            String script= f.readFile();
            int choice = sc.nextInt();
            switch(choice) {
                case 1: //script analysis for predefined script
                    performanceAnalysis.analyseScript(SqlScriptAnalysis.getSqlScript());
                    securityAnalysis.analyseScript(SqlScriptAnalysis.getSqlScript());
                    styleAnalysis.analyseScript(SqlScriptAnalysis.getSqlScript());
                    break;
                case 2: // script analysis for custom script

                    performanceAnalysis.analyseScript(script);
                    securityAnalysis.analyseScript(script);
                    styleAnalysis.analyseScript(script);
                    break;
                case 3: //generates rollback script for predefined script
                    rollbackScript.analyseScript(SqlScriptAnalysis.getSqlScript());
                    break;
                case 4: //generates rollback script for custom script
                    rollbackScript.analyseScript(script);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Try again");



            }
        }


    }






}
