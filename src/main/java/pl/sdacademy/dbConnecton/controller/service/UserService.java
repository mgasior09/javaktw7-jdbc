package pl.sdacademy.dbConnecton.controller.service;

import pl.sdacademy.dbConnecton.model.User;

import java.util.Optional;

public interface UserService {
    User addNewReader(User user);
    Optional<String> removeReader(Long readerNumber);
}
