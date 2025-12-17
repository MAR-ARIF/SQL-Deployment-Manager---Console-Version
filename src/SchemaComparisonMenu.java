import java.util.Scanner;

// This class provides a menu for comparing database schemas
// It allows users to compare predefined or custom target schemas
public class SchemaComparisonMenu {

    // This method displays the schema comparison menu
    // and handles user interaction
    public static void menu() {

        Scanner sc = new Scanner(System.in);

        // Loop keeps the menu active until the user chooses to go back
        while (true) {

            // Displaying schema comparison options
            System.out.println("\nSelect an option: ");
            System.out.println(
                    "1.View Schema Comparison for Predefined Target Schema\n" +
                            "2. View Schema Comparison for Custom Target Schema\n" +
                            "3.Go Back \n Note: if you enter custom schema press enter twice after entering to proceed"
            );

            // Creating SchemaDiff object for predefined schema comparison
            SchemaDiff sd = new SchemaDiff();

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    // Displaying original schema
                    System.out.println("***Original Schema***");
                    System.out.println(sd.getCurrentSchema());

                    // Displaying predefined target schema
                    System.out.println("***Target Schema***");
                    System.out.println(sd.getTargetSchema());

                    // Comparing original schema with predefined target schema
                    sd.compareSchemas(sd.getTarSchemaMap());
                    break;

                case 2:
                    // Displaying hard-coded original schema for reference
                    System.out.println("***Original Schema***");
                    System.out.println("""
                         CREATE TABLE users (
                            id INT PRIMARY KEY,
                            username VARCHAR(50),
                            email VARCHAR(100),
                            created_at DATETIME,
                            is_active BOOLEAN"""
                    );

                    // Taking custom target schema input from the user
                    CustomSchemaDiff cd =
                            new CustomSchemaDiff(User.customSchemaInput());

                    // Comparing original schema with user-defined target schema
                    cd.compareSchemas(cd.getCustomTargetSchemaMap());
                    break;

                case 3:
                    // Returning to previous menu
                    return;

                default:
                    System.out.println("Invalid choice. Try again");
            }
        }
    }
}

