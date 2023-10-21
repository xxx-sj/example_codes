package Junit_test.chap07.card_valid.test;

import Junit_test.chap07.card_valid.AutoDebitRegister;
import Junit_test.chap07.card_valid.req.AutoDebitReq;
import Junit_test.chap07.card_valid.CardValidity;
import Junit_test.chap07.card_valid.RegisterResult;
import Junit_test.chap07.card_valid.validator.stub.StubCardNumberValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AutoDebitRegister_Stub_Test {
    private AutoDebitRegister register;
    private StubCardNumberValidator stubValidator;
    private StubAutoDebitInfoRepository stubRepository;

    @BeforeEach
    void setUp() {
        stubValidator = new StubCardNumberValidator();
        stubRepository = new StubAutoDebitInfoRepository();
        register = new AutoDebitRegister(stubValidator, stubRepository);
    }

    @Test
    void invalidCard() {
        stubValidator.setInvalidNo("111122223333");

        AutoDebitReq req = new AutoDebitReq("user1", "111122223333");
        RegisterResult result = register.register(req);

        Assertions.assertEquals(CardValidity.INVALID, result.getValidity());
    }

    @Test
    void theftCard() {
        stubValidator.setTheftNo("1234567890123456");

        AutoDebitReq req = new AutoDebitReq("user1", "1234567890123456");
        RegisterResult result = this.register.register(req);

        Assertions.assertEquals(CardValidity.THEFT, result.getValidity());
    }

}
