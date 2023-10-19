package java_class.lambda_ex.stream_ex.flat_map_ex;

import java.util.Arrays;
import java.util.stream.Stream;

public class Flat_map_ex {
    public static void main(String[] args) {
        Stream<String[]> strStream = Stream.of(
                new String[] {"abc", "def", "asw"},
                new String[] {"wqe", "asx", "zsd"}
        );
        /**
         *       stream              stream
         * [abc, def, asw],       [wqe, asx, zsd]
         */
//        Stream<Stream<String>> streamStream = strStream.map((arr) -> Arrays.stream(arr));
        /**  stream
         * [abc, def, asw, wqe, asx, zsd]
         */
        Stream<String> stringStream = strStream.flatMap(Arrays::stream);
    }
}
