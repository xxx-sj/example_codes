package java_ex.file_ex.basic_ex;

import java.io.File;
import java.io.IOException;

public class FileEx2 {
    static String path = "D:\\study_folder\\example_codes\\example_codes\\BASIC_JAVA\\src\\java_ex\\file_ex\\test.txt";
    public static void main(String[] args) {
        String curDir = System.getProperty("user.dir");
        System.out.println("curDir = " + curDir);
        File file = new File(path);
        System.out.println("file.getPath() = " + file.getAbsolutePath());
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File tempFile = null;
        try  {
            tempFile = File.createTempFile("testFile", ".txt");
            System.out.println("tempFile.getAbsolutePath() = " + tempFile.getAbsolutePath());
            tempFile.deleteOnExit();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
