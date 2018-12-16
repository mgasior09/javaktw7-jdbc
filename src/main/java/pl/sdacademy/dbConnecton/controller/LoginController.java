package pl.sdacademy.dbConnecton.controller;

import pl.sdacademy.dbConnecton.controller.service.LoginService;
import pl.sdacademy.dbConnecton.model.LibraryUser;

import java.util.Optional;

public class LoginController {

    private final ConsoleDelegate console;
    private final LoginService loginService;

    public LoginController(ConsoleDelegate console, LoginService loginService) {
        this.console = console;
        this.loginService = loginService;
    }

    public Optional<LibraryUser> login() {
        String username = console.askUserForText("Username");
        String password = console.askUserForText("Password");
        Optional<LibraryUser> loggedUser = loginService.login(username, password);
        if (!loggedUser.isPresent()) {
            console.printMessage("Invalid user or password");
        }
        return loggedUser;
    }

}
