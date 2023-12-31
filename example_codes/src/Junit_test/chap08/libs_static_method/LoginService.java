package Junit_test.chap08.libs_static_method;

public class LoginService {
    private CustomerRepository customerRepository;
    private AuthService authService;

//    public void setAuthService(AuthService authService) {
//        this.authService = authService;
//    }

    public LoginService (CustomerRepository customerRepository, AuthService authService) {
        this.customerRepository = customerRepository;
        this.authService = authService;
    }

    public LoginResult login(String id, String pw) {
        int resp = authService.authenticate(id, pw);

        if(resp == - 1) return LoginResult.badAuthKey();

        if(resp == 1) {
            Customer c = customerRepository.findOne(id);
            return LoginResult.authenticated(c);
        } else {
            return LoginResult.fail(resp);
        }
    }
}
