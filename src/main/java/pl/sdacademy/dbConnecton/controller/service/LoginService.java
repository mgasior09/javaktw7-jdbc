package pl.sdacademy.dbConnecton.controller.service;

import pl.sdacademy.dbConnecton.model.User;

import java.util.Optional;

public interface LoginService {
    Optional<User> login(String username, String password);
}
