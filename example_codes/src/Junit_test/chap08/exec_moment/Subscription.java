package Junit_test.chap08.exec_moment;

import java.time.LocalDate;

public class Subscription {

    private Long productId;
    private LocalDate expiredDate;
    private Grade grade;
    public Subscription(LocalDate expiredDate, Grade grade) {
        this.productId = 1L;
        this.expiredDate = expiredDate;
        this.grade = grade;
    }

    public boolean isFinished(LocalDate now) {
        return expiredDate.EPOCH.isAfter(now);
    }

    public Grade getGrade() {
        return this.grade;
    }

    public Long getProductId() {
        return productId;
    }
}
