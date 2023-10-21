package Junit_test.chap07.user_register.repository;

import Junit_test.chap07.user_register.User;

import java.util.HashMap;
import java.util.Map;

public class MemoryUserRepository implements UserRepository {

    private final Map<String, User> map = new HashMap<>();
    @Override
    public void save(User user) {
        this.map.put(user.getId(), user);
    }
}
