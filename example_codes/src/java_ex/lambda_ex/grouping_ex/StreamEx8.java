package java_ex.lambda_ex.grouping_ex;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamEx8 {

    public static void main(String[] args) {
        Student[] stuArr = {
                new Student("나자바", true, 1, 1, 300),
                new Student("김자바", false, 1, 1, 150),
                new Student("이자바", false, 1, 1, 50),
                new Student("박자바", true, 1, 2, 300),
                new Student("정자바", true, 1, 2, 150),
                new Student("비자바", true, 1, 2, 50),
                new Student("최자바", false, 1, 3, 300),
                new Student("베자바", true, 1, 3, 150),
                new Student("보자바", false, 1, 3, 50),

                new Student("나자바", true, 2, 1, 300),
                new Student("김자바", false, 2, 1, 150),
                new Student("이자바", false, 2, 1, 50),
                new Student("박자바", true, 2, 2, 300),
                new Student("정자바", true, 2, 2, 150),
                new Student("비자바", true, 2, 2, 50),
                new Student("최자바", false, 2, 3, 300),
                new Student("베자바", true, 2, 3, 150),
                new Student("보자바", false, 2, 3, 50),
        };

        System.out.println("1. 단순그룹화(반별로 그룹화");

        Map<Integer, List<Student>> stuByBan = Stream.of(stuArr)
                .collect(Collectors.groupingBy(Student::getBan));

        for (List<Student> ban : stuByBan.values()) {
            for (Student student : ban) {
                System.out.println("student = " + student);
            }
        }

        System.out.println("2. 단순 그룹화(성적별로 그룹화");
        Map<Student.Level, List<Student>> stuByLevel = Stream.of(stuArr).collect(Collectors.groupingBy(
                (s) -> {
                    if (s.getScore() >= 200) return Student.Level.HIGH;
                    else if (s.getScore() >= 100) return Student.Level.MID;
                    else return Student.Level.LOW;
                }
        ));

        TreeSet<Student.Level> keySet = new TreeSet<>(stuByLevel.keySet());
        for (Student.Level key : keySet) {
            System.out.println("[" + key + "]");
            for (Student s : stuByLevel.get(key)) {
                System.out.println(s);
            }
            System.out.println();
        }

        System.out.println("3. 단순 그룹화 + 통계(성적별 학생수)");
        Map<Student.Level, Long> stuCntByLevel = Stream.of(stuArr)
                .collect(Collectors.groupingBy(
                        (s) -> {
                            if (s.getScore() >= 200) return Student.Level.HIGH;
                            else if (s.getScore() >= 100) return Student.Level.MID;
                            else return Student.Level.LOW;
                        }, Collectors.counting()));
        for (Student.Level key : stuCntByLevel.keySet()) {
            System.out.printf("[%s] - %d명, ", key, stuCntByLevel.get(key));
        }

        System.out.println("4. 다중 그룹화(학년별, 반별)");
        Map<Integer, Map<Integer, List<Student>>> stuByHakAndBan = Stream.of(stuArr)
                .collect(Collectors.groupingBy(Student::getHak,
                        Collectors.groupingBy(Student::getBan))
                );
        for (Map<Integer, List<Student>> hak : stuByHakAndBan.values()) {
            for (List<Student> ban : hak.values()) {
                System.out.println();
                for (Student student : ban) {
                    System.out.println("student = " + student);
                }
            }
        }

        System.out.println("5. 다중 그룹화 + 통계(학년별, 반별 1등)");
        Map<Integer, Map<Integer, Student>> topStuByHakAndBan = Stream.of(stuArr)
                .collect(Collectors.groupingBy(Student::getHak,
                        Collectors.groupingBy(Student::getBan,
                                Collectors.collectingAndThen(
                                        Collectors.maxBy(Comparator.comparingInt(Student::getScore))
                                        , Optional::get)
                        )
                ));
        for (Map<Integer, Student> ban : topStuByHakAndBan.values()) {
            for (Student s: ban.values()) {
                System.out.println("s = " + s);
            }
        }

        System.out.println("6. 다중 그룹화 + 통계 (학년별, 반별 성적 그룹)");
        Map<Integer, Map<Integer, Set<Student.Level>>> stuByScoreGroup = Stream.of(stuArr)
                .collect(Collectors.groupingBy(Student::getHak,
                        Collectors.groupingBy(Student::getBan,
                                Collectors.mapping(
                                        s -> {
                                            if (s.getScore() >= 200) return Student.Level.HIGH;
                                            else if (s.getScore() >= 100) return Student.Level.MID;
                                            else return Student.Level.LOW;
                                        }, Collectors.toSet()
                                ))));

        Set<Integer> groupByHak = stuByScoreGroup.keySet();
        for (Integer hak : groupByHak) {
            Map<Integer, Set<Student.Level>> groupByBan = stuByScoreGroup.get(hak);
            Set<Integer> bans = groupByBan.keySet();
            for (Integer ban : bans) {
                System.out.println("[ " + hak + " - " + ban + " ]");
                Set<Student.Level> levels = groupByBan.get(ban);
                for (Student.Level level : levels) {
                    System.out.println("level = " + level);
                }
            }
        }



        Map<String, Set<Student.Level>> collect = Stream.of(stuArr)
                .collect(Collectors.groupingBy(
                        (s) -> s.getHak() + "-" + s.getBan(),
                        Collectors.mapping(
                                s -> {
                                    if (s.getScore() >= 200) return Student.Level.HIGH;
                                    else if (s.getScore() >= 100) return Student.Level.MID;
                                    else return Student.Level.LOW;
                                }, Collectors.toSet()
                        )));


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
