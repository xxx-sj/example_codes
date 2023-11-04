package java_ex.file_ex.splt_file;

import java.io.*;

public class FileSplit {
    static String path = "D:\\study_folder\\example_codes\\example_codes\\BASIC_JAVA\\src\\java_ex\\file_ex\\";

    static final String fileName = "72자1506(20231017).csv";
    public static void main(String[] args) throws IOException {
        final int VOLUME = 1000 * 1000;

        FileInputStream fis = new FileInputStream(path + fileName);
        BufferedInputStream bis = new BufferedInputStream(fis);

        FileOutputStream fos= null;
        BufferedOutputStream bos = null;

        int data = 0;
        int i = 0;
        int number = 0;

        try {
            while((data = bis.read()) != -1) {
                if(i % VOLUME == 0) {
                    if (i != 0) {
                        bos.close();

                    }
                    fos = new FileOutputStream(path + fileName + "_." + ++number);
                    bos = new BufferedOutputStream(fos);
                }

                bos.write(data);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bis.close();
            bos.close();
        }
    }
}
