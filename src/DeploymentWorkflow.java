import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DeploymentWorkflow {



   public static void DeploymentProcess(User user){
       Scanner sc = new Scanner(System.in);
       while(true){
           System.out.println("Please select your choice: ");
           System.out.println("1.Deploy Predefined Schema\n2.Deploy your latest custom schema\n3.GO back");
           int choice = sc.nextInt();
           if (choice == 1 ||  choice == 2){
               System.out.println("Select the environment: ");
               System.out.println("1.Development\n2.Staging\n3.Production.");
               int choice2 = sc.nextInt();
               if (choice2 == 1 || choice2 == 2 || choice2 == 3){
                   System.out.println("=== Deployment Request Processing ===\n" +
                           "Applying schema updates...\n" +
                           "Checking for conflicts...\n" +
                           "Deployment request successful!\n" +
                           "Logged deployment request to history.");

                   LocalDateTime ld = LocalDateTime.now();
                   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy--HH:mm:ss");
                   String date = dtf.format(ld); //formates data and time
                   String envName = (choice2 == 1) ? "Development" :
                           (choice2 == 2) ? "Staging" :
                                   "Production";

                   String scType = (choice == 1) ? "Predefined Script" : "Custom Script";
                   //finally logging deployment History
                   FileHandler f = new FileHandler("Deployment Log.txt");
                   f.appendFile(envName + " | " + scType + " |⏳AWAITING APPROVAL | " +date+ " | BY "+user.getUserName()+" | "+user.getUserRole());
                   break;
               } else {
                   System.out.println("Invalid choice");
               }
           } else if  (choice == 3){
               return;
           } else {
               System.out.println("Invalid choice. Try again");
           }
       }

   }
}
