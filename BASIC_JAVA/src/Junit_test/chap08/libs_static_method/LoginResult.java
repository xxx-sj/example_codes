package Junit_test.chap08.libs_static_method;

public class LoginResult {
    public static LoginResult badAuthKey() {

        return new LoginResult();
    }

    public static LoginResult authenticated(Customer c) {

        return new LoginResult();
    }

    public static LoginResult fail(int resp) {

        return new LoginResult();
    }
}
