
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class FileHandler {

    private String fileName;


    //constructor lets you choose which file this handler works with
    public  FileHandler(String fileName){
        this.fileName = fileName;
    }
    //create file
    public void createFile(){
        try{
            FileWriter fw = new FileWriter(fileName);
            fw.write("-----" + fileName + "-----\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
   //write file
   public void writeFile(String content){
       try (FileWriter fw = new FileWriter(fileName)) {
           fw.write(content);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }


    //append to file
    public void appendFile(String content){
        try (FileWriter fw = new FileWriter(fileName, true)) {
            fw.write(content + System.lineSeparator()); // optional: add newline
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //read file
    public String readFile(){
        try{
            return Files.readString(Path.of(fileName));

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
    //modify file after approving deployment
    public void modifyFile(User user, String input) {
        try {
            Path path = Path.of(fileName);
            List<String> lines = Files.readAllLines(path);

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);

                if (line.contains("AWAITING APPROVAL")) {

                    if (input.equalsIgnoreCase("ap")) {
                        line = line.replace(
                                "⏳AWAITING APPROVAL",
                                "✅APPROVED BY DBA " + user.getUserName()
                        );
                        System.out.println("All Requests Approved");
                        lines.set(i, line);

                    } else if (input.equalsIgnoreCase("dc")) {
                        line = line.replace(
                                "⏳AWAITING APPROVAL",
                                "❌DECLINED BY DBA " + user.getUserName()
                        );
                        System.out.println("All Requests Declined");
                        lines.set(i, line);

                    } else if (input.equalsIgnoreCase("b")) {
                        return; // exits immediately, no file update
                    } else {
                        System.out.println("Invalid choice.");
                    }
                }
            }

            // Write updates back to file
            Files.write(path, lines);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //shows pending approvals
    public void showPendingApprovals() {
       try {
           Path path = Path.of(fileName);
           List<String> lines = Files.readAllLines(path);
           for(String line : lines){
               if (line.contains("AWAITING APPROVAL")) {
                   System.out.println(line.trim());
               }
           }
       } catch (IOException e) {
           e.printStackTrace();
       }

    }


}
