package pl.sdacademy.dbConnecton.controller.service;

import pl.sdacademy.dbConnecton.model.LibraryUser;

import java.util.Optional;

public interface LoginService {
    Optional<LibraryUser> login(String username, String password);
}
