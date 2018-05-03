package com.haka.stepup.auth.service;

import com.haka.stepup.db.User;

public interface UserService {
    void save(User user);
    User findByUsername(String username);
}
