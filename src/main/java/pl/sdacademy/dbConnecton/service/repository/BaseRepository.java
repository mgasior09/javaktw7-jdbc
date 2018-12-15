package pl.sdacademy.dbConnecton.service.repository;

import java.util.Optional;

public interface BaseRepository<T> {
    Optional<T> findById(Long id);
    T save(T entity);
    T update(T entity);
}
