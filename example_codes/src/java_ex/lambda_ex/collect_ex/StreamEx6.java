package java_ex.lambda_ex.collect_ex;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamEx6 {
    public static void main(String[] args) {
        Student[] stuArr = {
                new Student("e java", 3, 300),
                new Student("kim java", 1, 200),
                new Student("an java", 2, 100),
                new Student("park java", 2, 150),
                new Student("so java", 1, 20),
                new Student("na java", 3, 290),
                new Student("gam java", 3, 180)
        };

        List<String> names = Stream.of(stuArr).map(Student::getName)
                .collect(Collectors.toList());
        System.out.println(names);

        Student[] array = Stream.of(stuArr).toArray(Student[]::new);
        for (Student student : array) {
            System.out.println("student = " + student);
        }

        Map<String, Student> stuMap = Stream.of(stuArr).collect(Collectors.toMap((s) -> s.getName(), p -> p));
        for (String name : stuMap.keySet()) {
            System.out.println(name + "-" + stuMap.get(name));
        }

        Long count = Stream.of(stuArr).collect(Collectors.counting());
        Integer totalScore = Stream.of(stuArr)
                .collect(Collectors.summingInt(Student::getTotalScore));
        System.out.println("count = " + count);
        System.out.println("totalScore = " + totalScore);


        totalScore = Stream.of(stuArr)
                .collect(Collectors.reducing(0, Student::getTotalScore, Integer::sum));
        System.out.println("totalScore = " + totalScore);

        Optional<Student> topStudent = Stream.of(stuArr).collect(Collectors.maxBy(Comparator.comparing(Student::getTotalScore)));
        System.out.println("topStudent = " + topStudent.get());

        IntSummaryStatistics collect = Stream.of(stuArr)
                .collect(Collectors.summarizingInt(Student::getTotalScore));
        System.out.println("collect = " + collect);

        String stuName = Stream.of(stuArr).map(Student::getName)
                .collect(Collectors.joining(",", "{", "}"));
        System.out.println("stuName = " + stuName);


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
