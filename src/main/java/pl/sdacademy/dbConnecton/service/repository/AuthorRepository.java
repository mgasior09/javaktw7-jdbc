package pl.sdacademy.dbConnecton.service.repository;

import pl.sdacademy.dbConnecton.model.Writer;

import java.util.Optional;

public interface AuthorRepository extends BaseRepository<Writer> {
    Optional<Writer> findByFirstAndLastName(String firstName, String lastName);
}
