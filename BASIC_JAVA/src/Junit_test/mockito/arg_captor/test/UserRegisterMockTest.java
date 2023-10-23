package Junit_test.mockito.arg_captor.test;

import Junit_test.chap07.user_register.EmailNotifier;
import Junit_test.chap07.user_register.StubWeakPasswordChecker;
import Junit_test.chap07.user_register.UserRegister;
import Junit_test.chap07.user_register.repository.MemoryUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

public class UserRegisterMockTest {
    private UserRegister userRegister;
    private EmailNotifier mockEmailNotifier = Mockito.mock(EmailNotifier.class);

    private StubWeakPasswordChecker stubPasswordChecker = new StubWeakPasswordChecker();
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(stubPasswordChecker, fakeRepository, mockEmailNotifier);
    }


    @DisplayName("가입하면 메일을 전송함")
    @Test
    void whenRegisterThenSendMail() {
        //when 회원가입시 EmailNotifier를 이용해 이메일을 전송한다.
        userRegister.register("id", "pw", "email@email.com");

        //인자[String type]을 캡처하기위해 ArgumentCaptor의 객체를 만든다.
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        //then
        /**
         * userRegister.register로 인해
         * mockEmailNotifier#sendRegisterEmail메서드가 불려야 하며,
         * 메서드가 호출 될 때 인자로 전달받는 것을 capter.capture()로 캡처한다.
         */
        BDDMockito.then(mockEmailNotifier)
                .should().sendRegisterEmail(captor.capture());

        //이 후 capture() 한 인자는 getValue()로 꺼낼 수 있다.
        String realEmail = captor.getValue();
        Assertions.assertEquals("email@email.com", realEmail);
    }
}
