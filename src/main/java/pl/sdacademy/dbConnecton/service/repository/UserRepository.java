package pl.sdacademy.dbConnecton.service.repository;

import pl.sdacademy.dbConnecton.model.LibraryUser;

import java.util.Optional;

public interface UserRepository extends BaseRepository<LibraryUser> {
    Optional<LibraryUser> findByUsernameAndPassword(String username, String password);
}
