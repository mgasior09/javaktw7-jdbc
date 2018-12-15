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
    public List<Book> getBorrowedBooks(User user) {
        return bookRepository.findByBorrowingUserId(user.getId());
    }

    @Override
    public Optional<Book> borrowBook(User user, String title, String author) {
        Optional<Book> foundBook = bookRepository.findByTitleAndAuthorLastName(title, author).stream().findAny();
        Boolean bookBorrowed = foundBook.map(Book::getId).map(bookBorrowRepository::isBookBorrowed).orElse(false);
        if (!bookBorrowed) {
            BookBorrow newBookBorrow = new BookBorrow();
            newBookBorrow.setUserId(user.getId());
            newBookBorrow.setBookId(foundBook.get().getId());
            newBookBorrow.setBorrowDate(LocalDateTime.now());
            bookBorrowRepository.save(newBookBorrow);
            return foundBook;
        }
        return Optional.empty();
    }

    @Override
    public boolean returnBook(User user, Long bookId) {
        Optional<BookBorrow> borrowedBookRecord = bookBorrowRepository.findByUserIdAndBookId(user.getId(), bookId);
        if (borrowedBookRecord.isPresent()) {
            BookBorrow bookBorrow = borrowedBookRecord.get();
            bookBorrow.setReturnDate(LocalDateTime.now());
            bookBorrowRepository.update(bookBorrow);
            return true;
        }
        return false;
    }

    @Override
    public Optional<String> addNewBook(Book book, List<Author> authors, Location location) {
        Optional<Location> foundLocation = locationRepository.find(location.getRackSymbol(), location.getShelfSymbol(), location.getPosition());
        if (foundLocation.isPresent()) {
            return Optional.of("Location already occupied");
        }
        Long locationId = locationRepository.save(location).getId();
        List<Long> authorIds = getAuthorIds(authors);
        book.setAuthors(authorIds);
        book.setLocation(locationId);
        bookRepository.save(book);
        return Optional.empty();
    }

    private List<Long> getAuthorIds(List<Author> authors) {
        List<Long> authorIds = new ArrayList<>();
        for (Author author : authors) {
            Optional<Author> foundAuthor = authorRepository.findByFirstAndLastName(author.getFirstName(), author.getLastName());
            if (foundAuthor.isPresent()) {
                authorIds.add(foundAuthor.get().getId());
            } else {
                authorIds.add(authorRepository.save(author).getId());
            }
        }
        return authorIds;
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
