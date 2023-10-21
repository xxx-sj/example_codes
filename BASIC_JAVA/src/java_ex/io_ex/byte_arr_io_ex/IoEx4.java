package java_ex.io_ex.byte_arr_io_ex;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class IoEx4 {
    public static void main(String[] args) {
        byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
        byte[] outSrc = null;
        byte[] temp = new byte[4];

        ByteArrayInputStream input = null;
        ByteArrayOutputStream output = null;

        input = new ByteArrayInputStream(inSrc);
        output = new ByteArrayOutputStream();

//        System.out.println("Arrays.toString(inSrc) = " + Arrays.toString(inSrc));

        try {
            while(input.available() > 0) {
//                System.out.println("Arrays.toString(temp) = " + Arrays.toString(temp));
                //읽어온만큼만 output에 출력
                int len = input.read(temp);
                output.write(temp, 0 , len);

            }
        } catch (IOException e) {}

        outSrc = output.toByteArray();

        System.out.println("Arrays.toString(inSrc) = " + Arrays.toString(inSrc));
        System.out.println("Arrays.toString(temp) = " + Arrays.toString(temp));
        System.out.println("Arrays.toString(outSrc) = " + Arrays.toString(outSrc));
    }
}
