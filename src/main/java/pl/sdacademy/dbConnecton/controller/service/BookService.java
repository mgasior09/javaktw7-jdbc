package pl.sdacademy.dbConnecton.controller.service;

import pl.sdacademy.dbConnecton.model.Author;
import pl.sdacademy.dbConnecton.model.Book;
import pl.sdacademy.dbConnecton.model.Location;
import pl.sdacademy.dbConnecton.model.User;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getBorrowedBooks(User user);
    Optional<Book> borrowBook(User user, String title, String author);
    boolean returnBook(User user, Long bookId);
    Optional<String> addNewBook(Book book, List<Author> authors, Location location);
    Optional<String> removeBook(Long bookNumber);
}
