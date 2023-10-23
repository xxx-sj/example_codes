package java_ex.io_ex.data_io_ex;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * dataInputStream은 DataOutputStream으로 쓰여진 순서대로 읽어야 한다.
 */
public class DataInputStreamEx1 {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("sample.dat");
            DataInputStream dis = new DataInputStream(fis);

            System.out.println(dis.readInt());
            System.out.println(dis.readFloat());
            System.out.println(dis.readBoolean());
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
