package pl.sdacademy.dbConnecton.controller;

import pl.sdacademy.dbConnecton.controller.service.BookService;
import pl.sdacademy.dbConnecton.model.Book;
import pl.sdacademy.dbConnecton.model.User;

import java.util.List;
import java.util.Optional;

public class ReaderController {

    private final ConsoleDelegate consoleDelegate;
    private final BookService bookService;

    public ReaderController(ConsoleDelegate consoleDelegate, BookService bookService) {
        this.consoleDelegate = consoleDelegate;
        this.bookService = bookService;
    }

    public void listBooks(User user) {
        List<Book> borrowedBooks = bookService.getBorrowedBooks(user);
        borrowedBooks.forEach(book -> {
            System.out.println(String.format("%s. %s", book.getId(), book.getName()));
        });
    }

    public void borrowNewBook(User user) {
        String title = consoleDelegate.askUserForText("Book title");
        String author = consoleDelegate.askUserForText("Author last name");
        Optional<Book> borrowedBook = bookService.borrowBook(user, title, author);
        if (borrowedBook.isPresent()) {
            consoleDelegate.printMessage("Book borrowed!");
        } else {
            consoleDelegate.printMessage("Sorry, this book is unavailable");
        }
    }

    public void returnBook(User user) {
        Long bookId = consoleDelegate.askUserForNumber("Book number");
        boolean successful = bookService.returnBook(user, bookId);
        if (successful) {
            consoleDelegate.printMessage("Book was returned!");
        } else {
            consoleDelegate.printMessage("You did not borrowed this book");
        }
    }
}
