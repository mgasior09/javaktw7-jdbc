package pl.sdacademy.dbConnecton.controller;

import pl.sdacademy.dbConnecton.controller.service.BookService;
import pl.sdacademy.dbConnecton.model.Book;
import pl.sdacademy.dbConnecton.model.LibraryUser;

import java.util.List;
import java.util.Optional;

public class ReaderController {

    private final ConsoleDelegate consoleDelegate;
    private final BookService bookService;

    public ReaderController(ConsoleDelegate consoleDelegate, BookService bookService) {
        this.consoleDelegate = consoleDelegate;
        this.bookService = bookService;
    }

    public void listBooks(LibraryUser libraryUser) {
        List<Book> borrowedBooks = bookService.getBorrowedBooks(libraryUser);
        borrowedBooks.forEach(book -> {
            System.out.println(String.format("%s. %s", book.getId(), book.getName()));
        });
    }

    public void borrowNewBook(LibraryUser libraryUser) {
        String title = consoleDelegate.askUserForText("Book title");
        String author = consoleDelegate.askUserForText("Writer last name");
        Optional<Book> borrowedBook = bookService.borrowBook(libraryUser, title, author);
        if (borrowedBook.isPresent()) {
            consoleDelegate.printMessage("Book borrowed!");
        } else {
            consoleDelegate.printMessage("Sorry, this book is unavailable");
        }
    }

    public void returnBook(LibraryUser libraryUser) {
        Long bookId = consoleDelegate.askUserForNumber("Book number");
        boolean successful = bookService.returnBook(libraryUser, bookId);
        if (successful) {
            consoleDelegate.printMessage("Book was returned!");
        } else {
            consoleDelegate.printMessage("You did not borrowed this book");
        }
    }
}
