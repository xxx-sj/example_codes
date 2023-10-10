package Junit_test.chap01.calculator.test;

import Junit_test.chap01.calculator.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    void plus() {
        int result = Calculator.plus(1, 2);
        Assertions.assertEquals(3, result);
        Assertions.assertEquals(5, Calculator.plus(4, 1));
    }
}
