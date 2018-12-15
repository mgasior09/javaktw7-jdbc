package pl.sdacademy.dbConnecton.service.repository;

import pl.sdacademy.dbConnecton.model.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {
    Optional<User> findByUsernameAndPassword(String username, String password);
}
