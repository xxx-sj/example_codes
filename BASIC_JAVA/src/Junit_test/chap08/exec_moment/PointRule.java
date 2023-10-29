package Junit_test.chap08.exec_moment;

import java.time.LocalDate;

public class PointRule {

    public int calculate(Subscription s, Product p, LocalDate now) {
        int point = 0;
        if (s.isFinished(now)) {
            point += p.getDefaultPoint();
        } else {
            point += p.getDefaultPoint() + 10;
        }

        if (s.getGrade() == Grade.GOLD) {
            point += 100;
        }

        return point;
    }
}
