package Junit_test.chap08.exec_moment;

import java.time.LocalDate;

public class Subscription {
    public boolean isFinished(LocalDate now) {
        return true;
    }

    public Grade getGrade() {
        return Grade.GOLD;
    }

    public Long getProductId() {
        return 1L;
    }
}
