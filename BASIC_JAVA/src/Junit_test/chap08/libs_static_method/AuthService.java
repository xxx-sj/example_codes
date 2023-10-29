package Junit_test.chap08.libs_static_method;

public class AuthService {
    private String authKey = "someKey";

    public int authenticate(String id, String pw) {
        boolean authorized = AuthUtils.authorize(authKey);
        if(authorized) {
            return AuthUtils.authenticate(id, pw);
        } else {
            return -1;
        }
    }
}
