package Junit_test.chap07.user_register;

import Junit_test.chap07.user_register.exception.DupIdException;
import Junit_test.chap07.user_register.exception.WeakPasswordException;
import Junit_test.chap07.user_register.repository.MemoryUserRepository;
import Junit_test.chap07.user_register.repository.UserRepository;

public class UserRegister {
    private WeakPasswordChecker passwordChecker;
    private UserRepository userRepository;

    public UserRegister(WeakPasswordChecker passwordChecker) {
        this.passwordChecker = passwordChecker;
    }

    public UserRegister(StubWeakPasswordChecker passwordChecker, MemoryUserRepository userRepository) {
        this.passwordChecker = passwordChecker;
        this.userRepository = userRepository;
    }

    public void register(String id, String pw, String email) {
        if (passwordChecker.checkPasswordWeak(pw)) {
            throw new WeakPasswordException();
        }

        User user = userRepository.findById(id);
        if(user != null) {
            throw new DupIdException();
        }

        userRepository.save(new User(id, pw, email));


    }
}
