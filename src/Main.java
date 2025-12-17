//SID - 2427580
//Team name- Team Quint
import java.util.Scanner;

// This is the main entry point of the SQL Deployment Manager application
// It controls user roles, menus, and overall program flow
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);



        // Outer loop keeps the program running until user chooses to exit
        while (true) {

            // Displaying main menu
            System.out.println("Welcome to SQL Deployment Manager");
            System.out.println("Select the user: ");
            System.out.println("1.Developer\n2.DBA.\n3.Exit program");

            int choice = sc.nextInt();

            switch (choice) {

                // Developer menu
                case 1:

                    // Inner loop for developer submenu
                    while (true) {

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

                            // Navigates to schema comparison menu
                            case 1 -> SchemaComparisonMenu.menu();

                            // Navigates to SQL script analysis menu
                            case 2 -> ScriptAnalysisMenu.menu();

                            // Navigates to AI-based analysis menu
                            case 3 -> AIAnalysisMenu.menu();

                            // Starts deployment workflow as a Developer
                            case 4 ->
                                    DeploymentWorkflow.DeploymentProcess(
                                            new User("Arif", "DEV")
                                    );

                            // Displays deployment history from log file
                            case 5 -> {
                                FileHandler f =
                                        new FileHandler("Deployment Log.txt");
                                String history = f.readFile();
                                System.out.println(
                                        "                        *** DEPLOYMENT HISTORY ***\n"
                                );
                                System.out.println(history);
                            }

                            // Go back to main menu
                            case 6 -> {
                                break;
                            }

                            default ->
                                    System.out.println("Invalid choice.Try again");
                        }

                        // Breaks developer submenu loop
                        if (choice2 == 6) break;
                    }
                    break;

                // DBA menu
                case 2:

                    // Inner loop for DBA submenu
                    while (true) {

                        System.out.println("Choose an option: ");
                        System.out.println(
                                "1.View Pending Approval Request\n" +
                                        "2.Deployment History\n" +
                                        "3.Go Back "
                        );

                        int choice3 = sc.nextInt();

                        switch (choice3) {

                            // Viewing and approving/declining pending deployment requests
                            case 1 -> {
                                FileHandler f =
                                        new FileHandler("Deployment Log.txt");

                                System.out.println("*** PENDING APPROVAL REQUESTS ***");
                                f.showPendingApprovals();

                                System.out.println(
                                        "Enter AP to approve, DC to decline, or B to go back:"
                                );
                                String option = sc.next();

                                // DBA modifies deployment status
                                f.modifyFile(
                                        new User("JHON", "DBA"),
                                        option
                                );
                                break;
                            }

                            // Viewing full deployment history
                            case 2 -> {
                                FileHandler f =
                                        new FileHandler("Deployment Log.txt");
                                String history = f.readFile();
                                System.out.println(
                                        "                        *** DEPLOYMENT HISTORY ***\n"
                                );
                                System.out.println(history);
                                break;
                            }

                            // Go back to main menu
                            case 3 -> {
                                break;
                            }

                            default ->
                                    System.out.println("Invalid choice. Try again");
                        }

                        // Breaks DBA submenu loop
                        if (choice3 == 3) break;
                    }
                    break;

                // Exit application
                case 3:
                    System.out.println("Exiting program...");
                    return;
            }
        }
    }
}
