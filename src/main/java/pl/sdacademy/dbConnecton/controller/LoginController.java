package pl.sdacademy.dbConnecton.controller;

import pl.sdacademy.dbConnecton.controller.service.LoginService;
import pl.sdacademy.dbConnecton.model.User;

import java.util.Optional;

public class LoginController {

    private final ConsoleDelegate console;
    private final LoginService loginService;

    public LoginController(ConsoleDelegate console, LoginService loginService) {
        this.console = console;
        this.loginService = loginService;
    }

    public Optional<User> login() {
        String username = console.askUserForText("Username");
        String password = console.askUserForText("Password");
        Optional<User> loggedUser = loginService.login(username, password);
        if (!loggedUser.isPresent()) {
            console.printMessage("Invalid user or password");
        }
        return loggedUser;
    }

}
