package Junit_test.chap08.exec_moment.test;

import Junit_test.chap08.exec_moment.Grade;
import Junit_test.chap08.exec_moment.PointRule;
import Junit_test.chap08.exec_moment.Product;
import Junit_test.chap08.exec_moment.Subscription;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class UserPointCalculatorTest {


    @Test
    void 만료전_GOLD등급은_130포인트() {
        PointRule rule = new PointRule();
        Subscription s= new Subscription(
                LocalDate.of(2019, 5, 5),
                Grade.GOLD);

        Product p = new Product();
        p.setDefaultPoint(20);

        int point = rule.calculate(s, p, LocalDate.of(2019, 5, 1));

        Assertions.assertEquals(130, point);
    }
}
