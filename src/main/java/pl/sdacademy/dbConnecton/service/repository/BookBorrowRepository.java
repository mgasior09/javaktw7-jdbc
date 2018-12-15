package pl.sdacademy.dbConnecton.service.repository;

import pl.sdacademy.dbConnecton.model.BookBorrow;

import java.util.List;
import java.util.Optional;

public interface BookBorrowRepository extends BaseRepository<BookBorrow> {
    Optional<BookBorrow> findByUserIdAndBookId(Long id, Long bookId);
    boolean isBookBorrowed(Long bookId);
    List<BookBorrow> findByUserId(Long readerNumber);
}
