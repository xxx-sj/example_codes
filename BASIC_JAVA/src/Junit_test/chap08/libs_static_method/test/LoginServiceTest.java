package Junit_test.chap08.libs_static_method.test;

import Junit_test.chap08.libs_static_method.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

public class LoginServiceTest {

    LoginService loginService;
    AuthService authService = Mockito.mock(AuthService.class);

    @BeforeEach
    void setup() {
        CustomerRepository customerRepository = new MemoryCustomerRepository();
        this.loginService = new LoginService(customerRepository, authService);
    }

    @Test
    @DisplayName("외부 라이브러리 정적 메서드 테스트")
    void libs_static_method_test() {
        BDDMockito.given(authService.authenticate("id","pwd")).willReturn(-1);

        LoginResult result = loginService.login("id", "pwd");
        Assertions.assertInstanceOf(LoginResult.class, result);
    }
}
