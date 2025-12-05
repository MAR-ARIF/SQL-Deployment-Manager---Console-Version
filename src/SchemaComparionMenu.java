import java.util.Scanner;

public class SchemaComparionMenu {

    public static void menu() {
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("\nSelect an option: ");
            System.out.println("1.View Schema Comparison for Predefined Target Schema\n2. View Schema " +
                    "Comparison for Custom Target Schema\n3.Go Back");
            SchemaDiff sd = new SchemaDiff();
            int choice = sc.nextInt();
            switch(choice) {
                case 1:

                    System.out.println("***Original Schema***");
                    System.out.println(sd.getCurrentSchema());
                    System.out.println("***Target Schema***");
                    System.out.println(sd.getTargetSchema());
                    sd.compareSchemas(sd.getTarSchemaMap()); //compare with predefined schema
                    break;
                case 2:
                    System.out.println("***Original Schema***");
                    System.out.println("""
                         CREATE TABLE users (
                            id INT PRIMARY KEY,
                            username VARCHAR(50),
                            email VARCHAR(100),
                            created_at DATETIME,
                            is_active BOOLEAN"""

                    );
                    CustomSchemaDiff cd = new CustomSchemaDiff(User.customSchemaInput()); //here user.customSchemaInput() give custom schema taken from user
                    cd.compareSchemas(cd.getCustomTargetSchemaMap());
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Try again");
            }
        }
    }
}
