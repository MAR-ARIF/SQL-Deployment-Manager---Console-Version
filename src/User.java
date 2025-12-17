import java.util.Scanner;

// This class represents a system user who interacts with the deployment manager
// It stores basic user information and provides input utilities
public class User {

    // User-related fields
    private String userName;
    private String userRole;

    // Constructor to initialize user name and role
    public User(String userName, String userRole) {
        this.userName = userName;
        this.userRole = userRole;
    }

    // Getter method to retrieve user name
    public String getUserName() {
        return userName;
    }

    // Getter method to retrieve user role
    public String getUserRole() {
        return userRole;
    }

    // Setter method (intentionally left empty in this implementation)
    public void setUserName(String userName) {}

    // This method accepts multi-line SQL script input from the user
    // Used for entering a custom SQL deployment script
    public static String customScriptInput() {

        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        System.out.println("Enter the custom script: ");

        // Reading multiple lines until an empty line is entered
        while (true) {
            String line = sc.nextLine();
            if (line.isEmpty()) break;
            sb.append(line).append("\n");
        }

        return sb.toString();
    }

    // This method accepts multi-line schema input from the user
    // Used for entering a custom target database schema
    public static String customSchemaInput() {

        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        System.out.println("Enter the custom target Schema: ");

        // Reading multiple lines until an empty line is entered
        while (true) {
            String line = sc.nextLine();
            if (line.isEmpty()) break;
            sb.append(line).append("\n");
        }

        return sb.toString();
    }
}
