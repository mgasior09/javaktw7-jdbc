package pl.sdacademy.dbConnecton.service.repository;

import pl.sdacademy.dbConnecton.model.Book;

import java.util.List;

public interface BookRepository extends BaseRepository<Book> {
    List<Book> findByBorrowingUserId(Long id);
    List<Book> findByTitleAndAuthorLastName(String title, String author);
}
