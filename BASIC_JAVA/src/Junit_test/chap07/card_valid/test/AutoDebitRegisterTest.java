package Junit_test.chap07.card_valid.test;

import Junit_test.chap07.card_valid.AutoDebitRegister;
import Junit_test.chap07.card_valid.CardValidity;
import Junit_test.chap07.card_valid.RegisterResult;
import Junit_test.chap07.card_valid.repository.AutoDebitInfoRepository;
import Junit_test.chap07.card_valid.req.AutoDebitReq;
import Junit_test.chap07.card_valid.validator.CardNumberValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 실제 연동을 통한 테스트
 * 테스트용 카드번호를 받았고, 만약 1달 뒤 만료가 되는 카드번호라면
 * 해당 테스트는 실패하게 된다.
 */
public class AutoDebitRegisterTest {
    private AutoDebitRegister register;

    @BeforeEach
    void setUp() {
        CardNumberValidator validator = new CardNumberValidator();
        AutoDebitInfoRepository repository = new JpaAutoDebitInfoRepository();
        register = new AutoDebitRegister(validator, repository);
    }

    @Test
    void validCard() {
        //업체에서 받은 테스트용 유효한 카드번호 사용
        AutoDebitReq req = new AutoDebitReq("user1", "1234123412341234");
        RegisterResult result = this.register.register(req);
        Assertions.assertEquals(CardValidity.VALID, result.getValidity());
    }

    @Test
    void theftCard() {
        //업체에서 받은 도난 테스트용 카드번호 사용
        AutoDebitReq req = new AutoDebitReq("user1", "1234567890123456");
        RegisterResult result = this.register.register(req);
        Assertions.assertEquals(CardValidity.THEFT, result.getValidity());
    }
}
