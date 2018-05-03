package com.haka.stepup.auth.service;

public interface
SecurityService {
    String findLoggedInUsername();
    void autologin(String username, String password);
}
