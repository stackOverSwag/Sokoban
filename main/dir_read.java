import java.io.File;

public class DirectoryReader {
    public static void main(String[] args) {
        // Specify the directory path
        String directoryPath = "/home/rayou/univ/l2/S4/projet/Java-game-main/main";

        // Create a File object for the directory
        File directory = new File(directoryPath);

        // Get all files in the directory
        File[] files = directory.listFiles();

        // Check if the directory exists
        if (directory.exists()) {
            // Check if it's a directory
            if (directory.isDirectory()) {
                // Check if any files exist in the directory
                if (files != null && files.length > 0 ) {
                    // Print the filenames and store them in an array
                    String[] fileNames = new String[files.length];
                    for (int i = 0; i < files.length; i++) {
                        File file = files[i];
                
                        String fileName = file.getName();
                        if(fileName.substring(fileName.lastIndexOf(".") + 1).equalsIgnoreCase("txt")){
                            fileNames[i] = fileName;
                            System.out.println(fileName);
                        }
                    }

                    // Use the fileNames array as needed
                } else {
                    System.out.println("No files found in the directory.");
                }
            } else {
                System.out.println(directoryPath + " is not a directory.");
            }
        } else {
            System.out.println(directoryPath + " does not exist.");
        }
    }
}
