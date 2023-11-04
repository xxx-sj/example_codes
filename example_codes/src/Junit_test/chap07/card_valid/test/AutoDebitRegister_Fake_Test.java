package Junit_test.chap07.card_valid.test;

import Junit_test.chap07.card_valid.AutoDebitInfo;
import Junit_test.chap07.card_valid.AutoDebitRegister;
import Junit_test.chap07.card_valid.RegisterResult;
import Junit_test.chap07.card_valid.repository.MemoryAutoDebitInfoRepository;
import Junit_test.chap07.card_valid.req.AutoDebitReq;
import Junit_test.chap07.card_valid.validator.stub.StubCardNumberValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class AutoDebitRegister_Fake_Test {

    private AutoDebitRegister register;
    private StubCardNumberValidator cardNumberValidator;
    private MemoryAutoDebitInfoRepository repository;

    @BeforeEach
    void setUp() {
        cardNumberValidator = new StubCardNumberValidator();
        repository = new MemoryAutoDebitInfoRepository();
        register = new AutoDebitRegister(cardNumberValidator, repository);
    }

    @Test
    @DisplayName("이미 등록된 사용자를 등록하면 정보가 업데이트된다.")
    void alreadyRegistered_InfoUpdated() {
        //given
        repository.save(
                new AutoDebitInfo("user1", "111222333444", LocalDateTime.now()));

        //when
        AutoDebitReq req = new AutoDebitReq("user1", "123456789012");
        RegisterResult result = this.register.register(req);

        //then
        AutoDebitInfo saved = repository.findOne("user1");
        Assertions.assertEquals("123456789012", saved.getCardNumber());
    }

    @Test
    @DisplayName("새로 등록된 사용자 정보는 저장된다.")
    void notYetRegistered_newInfoRegistered() {
        AutoDebitReq req = new AutoDebitReq("user1", "1234123412341234");
        RegisterResult result = this.register.register(req);

        AutoDebitInfo saved = repository.findOne("user1");
        Assertions.assertEquals("1234123412341234", saved.getCardNumber());
    }
}
