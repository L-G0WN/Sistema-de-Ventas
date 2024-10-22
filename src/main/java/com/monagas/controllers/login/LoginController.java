package com.monagas.controllers.login;

import com.monagas.services.login.UserService;
import java.util.Optional;

public class LoginController {

    private final UserService userService = new UserService();

    public boolean login(String username, String password) {
        return userService.login(username, password);
    }

    public boolean findUserByUsername(String username) {
        return Optional.ofNullable(userService.findUserByUsername(username)).isPresent();
    }

    public boolean verify(String username, String question, String answer) {
        return userService.verify(username, question, answer);
    }

    public boolean changePassword(String username, String password) {
        return userService.changePassword(username, password);
    }
}
