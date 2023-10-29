package Junit_test.chap08.libs_static_method;

public class LoginService {
    private String authKey = "someKey";
    private CustomerRepository customerRepository;

    public LoginService (CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public LoginResult login(String id, String pw) {
        int resp = 0;
        boolean authorized = AuthUtils.authorize(authKey);

        if(authorized) {
            resp = AuthUtils.authenticate(id, pw);
        } else {
            resp = -1;
        }

        if(resp == - 1) return LoginResult.badAuthKey();

        if(resp == 1) {
            Customer c = customerRepository.findOne(id);
            return LoginResult.authenticated(c);
        } else {
            return LoginResult.fail(resp);
        }
    }
}
