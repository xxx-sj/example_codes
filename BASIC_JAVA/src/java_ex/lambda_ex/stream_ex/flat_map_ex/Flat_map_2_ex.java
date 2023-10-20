package java_ex.lambda_ex.stream_ex.flat_map_ex;

import java.util.Arrays;
import java.util.stream.Stream;

public class Flat_map_2_ex {

    public static void main(String[] args) {
        Stream<String[]> strArrStrm = Stream.of(
          new String[] {"abc", "dfe", "jkl"},
          new String[] {"ABC", "SDA", "QSD"}
        );

//        Stream<Stream<String>> streamStream = strArrStrm.map(Arrays::stream);
//        Stream<String> stringStream = strArrStrm.flatMap(Arrays::stream);
        Stream<String> stringStream = strArrStrm.flatMap((arr) -> Arrays.stream(arr));
        stringStream.map(String::toLowerCase)
                .distinct()
                .sorted()
                .forEach(System.out::println);
        System.out.println();

        String[] lineArr = {
                "Believe or not It is true",
                "Do or do not There is no try"
        };

        Stream<String> lineStream = Arrays.stream(lineArr);
        lineStream.flatMap(line -> Stream.of(line.split(" +")))
                .map(String::toLowerCase)
                .distinct()
                .sorted()
                .forEach(System.out::println);
        System.out.println();

        Stream<String> strStrm1 = Stream.of("AAA", "BBB", "bBb", "Dd");
        Stream<String> strStrm2 = Stream.of("bbb", "aaa", "ccc", "dd");

        Stream<Stream<String>> strStrmStrm = Stream.of(strStrm1, strStrm2);

        Stream<String> strStream = strStrmStrm
                .map(s -> s.toArray(String[]::new))
                .flatMap(Arrays::stream);

        strStream.map(String::toLowerCase)
                .distinct()
                .forEach(System.out::println);
    }
}
