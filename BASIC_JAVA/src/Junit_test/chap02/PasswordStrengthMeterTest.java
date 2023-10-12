package Junit_test.chap02;

import Junit_test.chap02.password.PasswordStrength;
import Junit_test.chap02.password.PasswordStrengthMeter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 길이가 8글자 이상
 * 0부터 9 사이의 숫자를 포함
 * 대문자포함
 */
public class PasswordStrengthMeterTest {

    private PasswordStrengthMeter meter = new PasswordStrengthMeter();

    private void assertStrength(String password, PasswordStrength expStr) {
        PasswordStrength result = meter.meter(password);
        Assertions.assertEquals(expStr, result);
    }
    @Test
    void name() {}

    // TODO ======[3] ======모두 충족할 때
    @Test
    void meetsAllCriteria_Then_Strong() {
//        PasswordStrength result = meter.meter("ab12!@AB");
//        Assertions.assertEquals(PasswordStrength.STRONG, result);
//        PasswordStrength result2 = meter.meter("abc1!Add");
//        Assertions.assertEquals(PasswordStrength.STRONG, result2);
        assertStrength("ab12!@AB", PasswordStrength.STRONG);
        assertStrength("abc1!Add", PasswordStrength.STRONG);

    }
    //TODO====== [3] ======END

    //TODO ======[2]======
    //2개만 충족할 때 8자 미만
    @Test
    void meetsOtherCriteria_except_for_Length_Then_Normal() {
//        PasswordStrength result = meter.meter("ab12!@A");
//        Assertions.assertEquals(PasswordStrength.NORMAL, result);
//        PasswordStrength result2 = meter.meter("Ab12!c");
//        Assertions.assertEquals(PasswordStrength.NORMAL, result2);

        assertStrength("ab12!@A", PasswordStrength.NORMAL);
        assertStrength("Ab12!c", PasswordStrength.NORMAL);
    }

    //숫자를 포함하지 않고 나머지 조건은 충족하는 경우
    @Test
    void meetsOtherCriteria_except_for_number_Then_Normal() {
//        PasswordStrength result = meter.meter("ab!@ABqwer");
//        Assertions.assertEquals(PasswordStrength.NORMAL, result);

        assertStrength("ab!@ABqwer", PasswordStrength.NORMAL);
    }

    //TODO ======[EXCEPT]======
    //null 입력 시
    @Test
    void nullInput_Then_Invalid() {
        assertStrength(null, PasswordStrength.INVALID);
    }



    //빈 문자열
    @Test
    void emptyInput_Then_Invalid() {
        assertStrength("", PasswordStrength.INVALID);
    }
    //TODO ======[EXCEPT]====== end
    
    //대문자를 포함하지 않고 나머지 조건을 충족하는 경우
    @Test
    void meetsOtherCriteria_expect_for_Uppercase_Then_Normal() {
        assertStrength("ab12!@df", PasswordStrength.NORMAL);
    }

    //TODO [2] end
    
    //TODO ======1개 또는 모두 불 충족[1 or 0]======

    @Test
    void meetsOnlyLengthCriteria_Then_Weak() {
        assertStrength("abdefghi", PasswordStrength.WEAK);
    }

    @Test
    void meetsOnlyNumCriteria_Then_Weak() {
        assertStrength("12345", PasswordStrength.WEAK);
    }

    @Test
    void meetsOnlyUpperCriteria_Then_Weak() {
        assertStrength("ABZEF", PasswordStrength.WEAK);
    }

    @Test
    void meetsNoCriteria_Then_Weak() {
        assertStrength("abc", PasswordStrength.WEAK);
    }
    //TODO ======1개 또는 모두 불 충족[1 or 0]====== end
}
