import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

// This class handles all file-related operations for the system
// It is mainly used for logging deployment requests and approvals
public class FileHandler {

    // Stores the file name this handler operates on
    private String fileName;

    // Constructor allows choosing which file to work with
    public FileHandler(String fileName) {
        this.fileName = fileName;
    }

    // This method creates a new file and writes a header into it
    public void createFile() {
        try {
            FileWriter fw = new FileWriter(fileName);
            fw.write("-----" + fileName + "-----\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This method overwrites the file with new content
    public void writeFile(String content) {
        try (FileWriter fw = new FileWriter(fileName)) {
            fw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This method appends new content to the existing file
    // Used to log new deployment requests
    public void appendFile(String content) {
        try (FileWriter fw = new FileWriter(fileName, true)) {
            fw.write(content + System.lineSeparator()); // adds a new line after each entry
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This method reads the entire file content and returns it as a String
    public String readFile() {
        try {
            return Files.readString(Path.of(fileName));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    // This method modifies deployment log entries after approval or rejection
    // It updates all entries marked as "AWAITING APPROVAL"
    public void modifyFile(User user, String input) {
        try {
            Path path = Path.of(fileName);
            List<String> lines = Files.readAllLines(path);

            // Iterating through each log entry
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);

                // Checking for pending approval status
                if (line.contains("AWAITING APPROVAL")) {

                    // Approve all pending requests
                    if (input.equalsIgnoreCase("ap")) {
                        line = line.replace(
                                "⏳AWAITING APPROVAL",
                                "✅APPROVED BY DBA " + user.getUserName()
                        );
                        System.out.println("All Requests Approved");
                        lines.set(i, line);

                        // Decline all pending requests
                    } else if (input.equalsIgnoreCase("dc")) {
                        line = line.replace(
                                "⏳AWAITING APPROVAL",
                                "❌DECLINED BY DBA " + user.getUserName()
                        );
                        System.out.println("All Requests Declined");
                        lines.set(i, line);

                        // Exit without making any changes
                    } else if (input.equalsIgnoreCase("b")) {
                        return;

                    } else {
                        System.out.println("Invalid choice.");
                    }
                }
            }

            // Writing updated log entries back to the file
            Files.write(path, lines);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This method displays all deployment requests that are still awaiting approval
    public void showPendingApprovals() {
        try {
            Path path = Path.of(fileName);
            List<String> lines = Files.readAllLines(path);

            // Printing only pending approval entries
            for (String line : lines) {
                if (line.contains("AWAITING APPROVAL")) {
                    System.out.println(line.trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
