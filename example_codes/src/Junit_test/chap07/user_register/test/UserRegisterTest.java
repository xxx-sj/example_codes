package Junit_test.chap07.user_register.test;

import Junit_test.chap07.user_register.SpyEmailNotifier;
import Junit_test.chap07.user_register.StubWeakPasswordChecker;
import Junit_test.chap07.user_register.User;
import Junit_test.chap07.user_register.UserRegister;
import Junit_test.chap07.user_register.exception.DupIdException;
import Junit_test.chap07.user_register.exception.WeakPasswordException;
import Junit_test.chap07.user_register.repository.MemoryUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserRegisterTest {
    private UserRegister userRegister;
    private StubWeakPasswordChecker stubPasswordChecker = new StubWeakPasswordChecker();
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    private SpyEmailNotifier spyEmailNotifier = new SpyEmailNotifier();

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(stubPasswordChecker, fakeRepository, spyEmailNotifier);
    }

    @DisplayName("약한 암호면 가입 실패")
    @Test
    void weakPassword() {
        stubPasswordChecker.setWeak(true); //암호가 약하다고 응답하도록 설정

        Assertions.assertThrows(WeakPasswordException.class, () -> {
            userRegister.register("id", "pw", "email");
        });
    }

    @DisplayName("이미 같은 ID가 존재하면 가입 실패")
    @Test
    void dupIdExists() {
        //이미 같은 ID 존재하는 상황 만들기
        //given
        fakeRepository.save(new User("id", "pw1", "email@email.com"));

        Assertions.assertThrows(DupIdException.class, () -> {
            userRegister.register("id", "pwd2", "email");
        });
    }

    @DisplayName("중복이 없으면 가입 성공")
    @Test
    void noDupId_RegisterSuccess() {
        //given
        userRegister.register("id", "pwd", "email");
        //when
        User savedUser = fakeRepository.findById("id"); //가입결과 확인

        //then
        Assertions.assertEquals("id", savedUser.getId());
        Assertions.assertEquals("email", savedUser.getEmail());
    }

    @DisplayName("가입하면 메일을 전송함")
    @Test
    void whenRegisterThenSendMail() {
        userRegister.register("id", "pw", "email@email.com");

        Assertions.assertTrue(spyEmailNotifier.isCalled());
        Assertions.assertEquals("email@email.com", spyEmailNotifier.getEmail());
    }
}
