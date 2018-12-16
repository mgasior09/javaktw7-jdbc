package pl.sdacademy.dbConnecton.service;

import pl.sdacademy.dbConnecton.controller.service.BookService;
import pl.sdacademy.dbConnecton.model.*;
import pl.sdacademy.dbConnecton.service.repository.AuthorRepository;
import pl.sdacademy.dbConnecton.service.repository.BookBorrowRepository;
import pl.sdacademy.dbConnecton.service.repository.BookRepository;
import pl.sdacademy.dbConnecton.service.repository.LocationRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DefaultBookService implements BookService {

    private final LocationRepository locationRepository;
    private final AuthorRepository authorRepository;
    private final BookBorrowRepository bookBorrowRepository;
    private final BookRepository bookRepository;

    public DefaultBookService(LocationRepository locationRepository, AuthorRepository authorRepository, BookBorrowRepository bookBorrowRepository, BookRepository bookRepository) {
        this.locationRepository = locationRepository;
        this.authorRepository = authorRepository;
        this.bookBorrowRepository = bookBorrowRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getBorrowedBooks(LibraryUser libraryUser) {
        return bookRepository.findByBorrowingUserId(libraryUser.getId());
    }

    @Override
    public Optional<Book> borrowBook(LibraryUser libraryUser, String title, String author) {
        Optional<Book> foundBook = bookRepository.findByTitleAndAuthorLastName(title, author).stream().findAny();
        Boolean bookBorrowed = foundBook.map(Book::getId).map(bookBorrowRepository::isBookBorrowed).orElse(false);
        if (!bookBorrowed) {
            BookBorrow newBookBorrow = new BookBorrow();
            newBookBorrow.setUserId(libraryUser.getId());
            newBookBorrow.setBookId(foundBook.get().getId());
            newBookBorrow.setBorrowDate(LocalDateTime.now());
            bookBorrowRepository.save(newBookBorrow);
            return foundBook;
        }
        return Optional.empty();
    }

    @Override
    public boolean returnBook(LibraryUser libraryUser, Long bookId) {
        Optional<BookBorrow> borrowedBookRecord = bookBorrowRepository.findByUserIdAndBookId(libraryUser.getId(), bookId);
        if (borrowedBookRecord.isPresent()) {
            BookBorrow bookBorrow = borrowedBookRecord.get();
            bookBorrow.setReturnDate(LocalDateTime.now());
            bookBorrowRepository.update(bookBorrow);
            return true;
        }
        return false;
    }

    @Override
    public Optional<String> addNewBook(Book book, List<Writer> writers, Location location) {
        Optional<Location> foundLocation = locationRepository.find(location.getRackSymbol(), location.getShelfSymbol(), location.getPosition());
        if (foundLocation.isPresent()) {
            return Optional.of("Location already occupied");
        }
       location = locationRepository.save(location);
        List<Writer> foundWriters = getAuthors(writers);
        book.setAuthors(foundWriters);
        book.setLocation(location);
        bookRepository.save(book);
        return Optional.empty();
    }

    private List<Writer> getAuthors(List<Writer> writers) {
        List<Writer> authors = new ArrayList<>();
        for (Writer writer : writers) {
            Optional<Writer> foundAuthor = authorRepository.findByFirstAndLastName(writer.getFirstName(), writer.getLastName());
            if (foundAuthor.isPresent()) {
                authors.add(foundAuthor.get());
            } else {
                authors.add(authorRepository.save(writer));
            }
        }
        return authors;
    }

    @Override
    public Optional<String> removeBook(Long bookId) {
        if (bookBorrowRepository.isBookBorrowed(bookId)) {
            return Optional.of("Cannot remove book that is borrowed.");
        }
        Optional<Book> foundBook = bookRepository.findById(bookId);
        if (foundBook.isPresent()) {
            Book book = foundBook.get();
            book.setRemoved(true);
            bookRepository.update(book);
            return Optional.empty();
        }
        return Optional.of("Book does not exist.");
    }
}
