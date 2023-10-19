package java_class.lambda_ex.stream_ex.peek_ex;

import java_class.lambda_ex.stream_ex.map_ex.Map_Ex;

import java.io.File;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Peek_ex {

    public static void main(String[] args) {
        Stream<File> fileStream = Map_Ex.makeFile();

//        fileStream.map(File::getName)
//                .filter(s -> s.contains("."))
//                .peek(s -> System.out.printf("filename = %s%n", s))
//                .map(s -> s.substring(s.indexOf(".") + 1))
//                .peek(s -> System.out.printf("extension = %s%n", s))
//                .forEach(System.out::println);

        Peek_ex.printMultipleAAndBUntilN(2, 3, 15);
    }

    public static void printMultipleAAndBUntilN(int a, int b, int n) {
        IntStream.rangeClosed(1, n)
                .filter(i -> i % a == 0)
                .peek(i -> System.out.println(a + " 의 배수: " + i))
                .filter(i -> i % b == 0)
                .peek(i -> System.out.println(a + " 와 " + b + "의 공배수: " + i))
                .forEach(System.out::println);
    }
}
