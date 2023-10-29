package Junit_test.chap08.exec_moment.test;

import Junit_test.chap08.exec_moment.*;
import Junit_test.chap08.exec_moment.repository.ProductDao;
import Junit_test.chap08.exec_moment.repository.SubscriptionDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.time.LocalDate;

public class UserPointCalculatorTest {

    private UserPointCalculator userPointCalculator;

    @BeforeEach
    void setup() {
        this.userPointCalculator = new UserPointCalculator(new SubscriptionDao(), new ProductDao());
    }


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

    @Test
    void Mock_time을_이용한_테스트() {
        Times times = Mockito.mock(Times.class);
        BDDMockito.given(times.today()).willReturn(LocalDate.of(2019, 5, 5));

        userPointCalculator.setTimes(times);

        userPointCalculator.calculatePoint(new User());
    }
}
