package java_ex.lambda_ex.partitioning_by;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamEx7 {

    public static void main(String[] args) {
        Student[] stuArr = {
                new Student("나자바", true, 1, 1, 300),
                new Student("김자바", false, 1, 1, 200),
                new Student("이자바", false, 1, 1, 150),
                new Student("박자바", true, 1, 2, 100),
                new Student("정자바", true, 1, 2, 150),
                new Student("비자바", true, 1, 2, 50),
                new Student("최자바", false, 1, 1, 150),
                new Student("베자바", true, 1, 3, 100),
                new Student("보자바", false, 1, 1, 200),
                new Student("마자바", true, 1, 3, 100),
                new Student("미자바", false, 1, 1, 150),
                new Student("킨자바", true, 1, 3, 250),

                new Student("나자바", true, 2, 1, 300),
                new Student("김자바", false, 2, 1, 200),
                new Student("이자바", false, 2, 1, 150),
                new Student("박자바", true, 2, 2, 100),
                new Student("정자바", true, 2, 2, 150),
                new Student("비자바", true, 2, 2, 50),
                new Student("최자바", false, 2, 1, 150),
                new Student("베자바", true, 2, 3, 100),
                new Student("보자바", false, 2, 1, 200),
                new Student("마자바", true, 2, 3, 100),
                new Student("미자바", false, 2, 1, 150),
                new Student("킨자바", true, 2, 3, 250),
        };

        System.out.println("1. 단순 (성별로 분할)");
        Map<Boolean, List<Student>> stuBySex = Stream.of(stuArr)
                .collect(Collectors.partitioningBy(Student::isMale));
        List<Student> maleStudents = stuBySex.get(true);
        List<Student> femaleStudents = stuBySex.get(false);
        for (Student maleStudent : maleStudents) {
            System.out.println("maleStudent = " + maleStudent);
        }
        for (Student femaleStudent : femaleStudents) {
            System.out.println("femaleStudent = " + femaleStudent);
        }

        System.out.println("2. 단순 분할 + 통계(성별 학생수)");
        Map<Boolean, Long> stuNumBySex = Stream.of(stuArr)
                .collect(Collectors.partitioningBy(Student::isMale, Collectors.counting()));
        System.out.println("\"남 학생 수 : \" + stuNumBySex.get(true) = " + "남 학생 수 : " + stuNumBySex.get(true));
        System.out.println("여학 생 수 : " + stuNumBySex.get(false));

        System.out.println("단순 분할 + 통계(성별 1등)");
        Map<Boolean, Optional<Student>> topScoreBySex = Stream.of(stuArr)
                .collect(Collectors.partitioningBy(Student::isMale, Collectors.maxBy(Comparator.comparingInt(Student::getScore))));
        System.out.println("남학생 1등 : " + topScoreBySex.get(true));
        System.out.println("여학생 1등: " + topScoreBySex.get(false));

        Map<Boolean, Student> topScoreBySex2 = Stream.of(stuArr)
                .collect(Collectors.partitioningBy(Student::isMale,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(
                                        Comparator.comparingInt(Student::getScore)
                                ), Optional::get
                        )));

        System.out.println("남학생 1등 : " + topScoreBySex2.get(true));
        System.out.println("여학생 1등 : " + topScoreBySex2.get(false));

        System.out.println("4. 다중 분할(성별 불합격자(100점이하))");

        Map<Boolean, Map<Boolean, List<Student>>> failedStuBySex = Stream.of(stuArr).collect(Collectors.partitioningBy(Student::isMale,
                Collectors.partitioningBy((s) -> s.getScore() <= 100)
        ));
        List<Student> failedMaleStu = failedStuBySex.get(true).get(true);
        List<Student> failedFemaleStu = failedStuBySex.get(false).get(true);
        for (Student student : failedMaleStu) {
            System.out.println("student = " + student);
        }
        for (Student student : failedFemaleStu) {
            System.out.println("student = " + student);
        }

    }

    static class Student {
        String name;
        boolean isMale;
        int hak;
        int ban;
        int score;

        public Student(String name, boolean isMale, int hak, int ban, int score) {
            this.name = name;
            this.isMale = isMale;
            this.hak = hak;
            this.ban = ban;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public boolean isMale() {
            return isMale;
        }

        public int getHak() {
            return hak;
        }

        public int getBan() {
            return ban;
        }

        public int getScore() {
            return score;
        }

        public String toString() {
            return String.format("[%s, %s, %d학녀 %d반, %3d점",
                    name, isMale ? "남": "여", hak, ban, score);
        }

        enum Level {
            HIGH, MID, LOW
        }
    }
}
