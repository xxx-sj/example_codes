package java_ex.file_ex.splt_file;

import java.io.*;

public class FileMerge {

    static String path = "D:\\study_folder\\example_codes\\example_codes\\BASIC_JAVA\\src\\java_ex\\file_ex\\";

    static final String mergeFilename = "72자1506(20231017).csv";

    static final String newFilename = "temp.csv";
    public static void main(String[] args) {

        try {
            File tempFile = File.createTempFile("~mergetemp", ".tmp", new File(path));
//            tempFile.deleteOnExit();

            FileOutputStream fos = new FileOutputStream(tempFile);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            BufferedInputStream bis = null;

            int number = 1;
            File f= new File(path + mergeFilename + "_." + number);

            while(f.exists()) {
                f.setReadOnly();
                bis = new BufferedInputStream(new FileInputStream(f));

                int data = 0;

                while((data = bis.read()) != -1) {
                    bos.write(data);
                }

                bis.close();

                f = new File(path  + mergeFilename + "_." + ++number);
            }

            bos.close();

            File oldFile = new File(path + mergeFilename);
            if(oldFile.exists()) {
                oldFile.deleteOnExit();
            }
            tempFile.renameTo(oldFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
