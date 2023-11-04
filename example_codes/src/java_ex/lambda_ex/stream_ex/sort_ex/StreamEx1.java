package java_ex.lambda_ex.stream_ex.sort_ex;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

public class StreamEx1 {

    public static void main(String[] args) {
        Stream<Student> studentStream = Stream.of(
                new Student("e java", 3, 300),
                new Student("kim java", 1, 200),
                new Student("an java", 2, 100),
                new Student("park java", 2, 150),
                new Student("so java", 1, 20),
                new Student("na java", 3, 290),
                new Student("gam java", 3, 180)
        );

        Function<Student, Integer> f = (s) -> {
            return s.getBan();
        };

        studentStream.sorted(Comparator.comparing(f)
                .thenComparing(Comparator.naturalOrder()))
                .forEach(System.out::println);

        studentStream.sorted(Comparator.comparing(Student::getBan)
                .thenComparing(Comparator.naturalOrder()))
                        .forEach(System.out::println);

//        studentStream.sorted(Comparator.comparing((s) -> ((Student)s).getBan())
//                        .thenComparing(Comparator.naturalOrder()))
//                .forEach(System.out::println);

        Function<String, Integer> f1 = String::length;

        BiFunction<Student, Student, Boolean> f2 = Student::equals;
        BiFunction<String, String, Boolean> f3 = String::equals;


        studentStream.sorted(Comparator.comparing((Student s) -> s.getBan())
                        .thenComparing(Comparator.naturalOrder()))
                .forEach(System.out::println);
    }

    public static class Student implements Comparable<Student> {
        String name;
        int ban;
        int totalScore;
        Student(String name, int ban, int totalScore) {
            this.name = name;
            this.ban = ban;
            this.totalScore = totalScore;
        }

        public String toString() {
            return String.format("[%s, %d, %d]", name, ban, totalScore);
        }

        public String getName() {
            return name;
        }

        public int getBan() {
            return ban;
        }

        public int getTotalScore() {
            return totalScore;
        }

        @Override
        public int compareTo(Student o) {
            return o.totalScore - this.totalScore;
        }
    }
}
