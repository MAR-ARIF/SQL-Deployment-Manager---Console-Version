import java.util.Scanner;

public class ScriptAnalysisMenu {

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nSelect an option: ");
            System.out.println("1.View Script Analysis for Predefined SQL Script\n2. View Script" +
                    " Comparison for Custom SQL Script\n3.G0 Back");
            SqlScriptAnalysis sq = new SqlScriptAnalysis();
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("*** SQL Script for predefined Schema Change ***");
                    SqlScriptAnalysis.getSqlScript();
                    sq.analyseScript(SqlScriptAnalysis.getSqlScript());
                    break;
                case 2:
                    String script = User.customScriptInput();
                    sq.analyseScript(script);
                    FileHandler f = new FileHandler("Latest Custom SQL Script.txt");
                    f.createFile();
                    f.writeFile(script);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Try again");

            }

        }
    }
}
