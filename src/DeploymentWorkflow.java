import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

// This class handles the deployment workflow process
// It guides the user through deployment choices and logs deployment requests
public class DeploymentWorkflow {

    // This method manages the full deployment process
    // It accepts a User object to track who initiated the deployment
    public static void DeploymentProcess(User user) {

        Scanner sc = new Scanner(System.in);

        // Loop keeps the deployment menu active until the user exits
        while (true) {

            // Displaying deployment options
            System.out.println("Please select your choice: ");
            System.out.println("1.Deploy Predefined Schema\n2.Deploy your latest custom schema\n3.GO back");

            int choice = sc.nextInt();

            // Checking schema deployment choice
            if (choice == 1 || choice == 2) {

                // Asking user to select the target environment
                System.out.println("Select the environment: ");
                System.out.println("1.Development\n2.Staging\n3.Production.");

                int choice2 = sc.nextInt();

                // Validating environment selection
                if (choice2 == 1 || choice2 == 2 || choice2 == 3) {

                    // Simulating deployment process steps
                    System.out.println("=== Deployment Request Processing ===\n" +
                            "Applying schema updates...\n" +
                            "Checking for conflicts...\n" +
                            "Deployment request successful!\n" +
                            "Logged deployment request to history.");

                    // Capturing current date and time
                    LocalDateTime ld = LocalDateTime.now();

                    // Formatting date and time for logging
                    DateTimeFormatter dtf =
                            DateTimeFormatter.ofPattern("dd/MM/yyyy--HH:mm:ss");

                    String date = dtf.format(ld);

                    // Mapping environment choice to environment name
                    String envName = (choice2 == 1) ? "Development" :
                            (choice2 == 2) ? "Staging" :
                                    "Production";

                    // Identifying schema type (predefined or custom)
                    String scType = (choice == 1) ? "Predefined Script" : "Custom Script";

                    // Logging deployment request details to file
                    FileHandler f = new FileHandler("Deployment Log.txt");
                    f.appendFile(
                            envName + " | " + scType +
                                    " |⏳AWAITING APPROVAL | " +
                                    date + " | BY " +
                                    user.getUserName() +
                                    " | " +
                                    user.getUserRole()
                    );

                    // Exit after successful logging
                    break;

                } else {
                    System.out.println("Invalid choice");
                }

            } else if (choice == 3) {
                // Return to previous menu
                return;
            } else {
                System.out.println("Invalid choice. Try again");
            }
        }
    }
}
