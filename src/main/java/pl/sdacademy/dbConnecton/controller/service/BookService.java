package pl.sdacademy.dbConnecton.controller.service;

import pl.sdacademy.dbConnecton.model.LibraryUser;
import pl.sdacademy.dbConnecton.model.Writer;
import pl.sdacademy.dbConnecton.model.Book;
import pl.sdacademy.dbConnecton.model.Location;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getBorrowedBooks(LibraryUser libraryUser);
    Optional<Book> borrowBook(LibraryUser libraryUser, String title, String author);
    boolean returnBook(LibraryUser libraryUser, Long bookId);
    Optional<String> addNewBook(Book book, List<Writer> writers, Location location);
    Optional<String> removeBook(Long bookNumber);
}
