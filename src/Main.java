
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        while (true) { //  outer loop
            System.out.println("Welcome to SQL Deployment Manager");
            System.out.println("Select the user: ");
            System.out.println("1.Developer\n2.DBA.\n3.Exit program");

            int choice = sc.nextInt();


            switch (choice) {
                case 1:

                    while (true) { // inner loop for Developer submenu
                        System.out.println("Choose an option: ");
                        System.out.println("""
                            1.View Schema Comparison
                            2.View Sql Script Analysis
                            3.View AI Analysis
                            4.Deployment Workflows
                            5.Deployment History
                            6.Go Back""");

                        int choice2 = sc.nextInt();


                        switch (choice2) {
                            case 1 -> SchemaComparionMenu.menu();
                            case 2 -> ScriptAnalysisMenu.menu();
                            case 3 -> AIAnalysisMenu.menu();
                            case 4 -> DeploymentWorkflow.DeploymentProcess(new User("Arif", "DEV"));
                            case 5 -> {
                                FileHandler f = new FileHandler("Deployment Log.txt");
                                String history = f.readFile();
                                System.out.println("                        *** DEPLOYMENT HISTORY ***\n");
                                System.out.println(history);
                            }
                            case 6 -> {
                                break;
                            }
                            default -> System.out.println("Invalid choice.Try again");
                        }

                        if (choice2 == 6) break;
                    }
                    break;
                case 2:
                    while (true) {
                        System.out.println("Choose an option: ");
                        System.out.println("1.View Pending Approval Request\n2.Deployment History\n3.Go Back ");

                        int choice3 = sc.nextInt();
                        switch (choice3) {

                            case 1 -> {
                                FileHandler f = new FileHandler("Deployment Log.txt");
                                System.out.println("*** PENDING APPROVAL REQUESTS ***");
                                f.showPendingApprovals();

                                System.out.println("Enter AP to approve, DC to decline, or B to go back:");
                                String option = sc.next();

                                f.modifyFile(new User("JHON","DBA"), option);
                                break;
                            }

                            case 2 -> {
                                FileHandler f = new FileHandler("Deployment Log.txt");
                                String history = f.readFile();
                                System.out.println("                        *** DEPLOYMENT HISTORY ***\n");
                                System.out.println(history);
                                break;
                            }

                            case 3 -> {
                                break;
                            }

                            default -> System.out.println("Invalid choice. Try again");
                        }

                        if (choice3 == 3) break;
                    }
                    break;

                case 3:
                    System.out.println("Exiting program...");
                    return;
            }
        }
    }
}
