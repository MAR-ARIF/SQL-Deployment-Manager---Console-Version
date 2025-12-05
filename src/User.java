import java.util.Scanner;

public class User {
    private String userName;
    private String userId;
    private String password;
    private String userRole;

    public User(String userName, String userRole) {
        this.userName = userName;
        this.userRole = userRole;

    }

    public String getUserName() {
        return userName;
    }
    public String getUserRole() {
        return userRole;
    }
    public void setUserName(String userName) {}
    // to take multiline input from user for custom Script
    public static String customScriptInput(){
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        System.out.println("Enter the custom script: ");
        while (true) {
            String line = sc.nextLine();
            if (line.isEmpty()) break;
            sb.append(line).append("\n");
        }
        return sb.toString();
    }
    //to take multiline input from user for custom schema
    public static String customSchemaInput(){
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        System.out.println("Enter the custom target Schema: ");
        while (true) {
            String line = sc.nextLine();
            if (line.isEmpty()) break;
            sb.append(line).append("\n");
        }
        return sb.toString();
    }

}
