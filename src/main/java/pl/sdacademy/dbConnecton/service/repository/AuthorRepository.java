package pl.sdacademy.dbConnecton.service.repository;

import pl.sdacademy.dbConnecton.model.Author;

import java.util.Optional;

public interface AuthorRepository extends BaseRepository<Author> {
    Optional<Author> findByFirstAndLastName(String firstName, String lastName);
}
