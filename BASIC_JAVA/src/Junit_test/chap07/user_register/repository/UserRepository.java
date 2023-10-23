package Junit_test.chap07.user_register.repository;

import Junit_test.chap07.user_register.User;

public interface UserRepository {

    void save(User user);

    User findById(String id);
}
