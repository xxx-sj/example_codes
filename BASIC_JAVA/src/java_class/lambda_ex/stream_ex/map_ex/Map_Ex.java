package java_class.lambda_ex.stream_ex.map_ex;

import java.io.File;
import java.util.function.Function;
import java.util.stream.Stream;

public class Map_Ex {
    public static void main(String[] args) {


        Stream<File> fileStream = Map_Ex.makeFile();
//        Stream<String> stringStream = fileStream.map(File::getName);
        Function<File, String> function = (f) -> {
            return f.getName();
        };
        Stream<String> stringStream = fileStream.map(function);
        stringStream.forEach(System.out::println);

        System.out.println("========================================");
        Stream<File> fileStream1 = Map_Ex.makeFile();

        fileStream1.map(File::getName)
                .filter((s) -> s.contains("."))
                .map(s -> s.substring(s.indexOf(".") + 1))
                .map(String::toUpperCase)
                .distinct()
                .forEach(System.out::println);
    }

    public static Stream<File> makeFile() {
        return  Stream.of(
                new File("ex1.txt"),
                new File("ex2.java"),
                new File("ex3.java"),
                new File("ex4.txt"),
                new File("ex5"),
                new File("ex6.txt")
        );
    }
}
