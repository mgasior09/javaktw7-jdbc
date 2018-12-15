package pl.sdacademy.dbConnecton.service;

import pl.sdacademy.dbConnecton.controller.service.LoginService;
import pl.sdacademy.dbConnecton.model.User;
import pl.sdacademy.dbConnecton.service.repository.UserRepository;

import java.util.Optional;

public class DefaultLoginService implements LoginService {

    private final UserRepository userRepository;

    public DefaultLoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Optional<User> login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
}
