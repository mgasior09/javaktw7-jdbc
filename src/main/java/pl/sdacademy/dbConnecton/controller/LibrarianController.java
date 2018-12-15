package pl.sdacademy.dbConnecton.controller;

import pl.sdacademy.dbConnecton.controller.service.BookService;
import pl.sdacademy.dbConnecton.controller.service.UserService;
import pl.sdacademy.dbConnecton.model.Author;
import pl.sdacademy.dbConnecton.model.Book;
import pl.sdacademy.dbConnecton.model.Location;
import pl.sdacademy.dbConnecton.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibrarianController {

    private final ConsoleDelegate console;
    private final BookService bookService;
    private final UserService userService;

    public LibrarianController(ConsoleDelegate console, BookService bookService, UserService userService) {
        this.console = console;
        this.bookService = bookService;
        this.userService = userService;
    }

    public void addBook() {
        List<Author> authors = new ArrayList<>();
        Book book = new Book();
        Location location = new Location();
        Optional<String> errorMessage = bookService.addNewBook(book, authors, location);
        errorMessage.ifPresent(System.out::println);
    }

    public void removeBook() {
        Long bookNumber = console.askUserForNumber("Book number");
        Optional<String> errorMessage = bookService.removeBook(bookNumber);
        errorMessage.ifPresent(System.out::println);
    }

    public void registerUser() {
        User user = new User();
        user.setUsername(console.askUserForText("Username"));
        user.setPassword(console.askUserForText("Password"));
        user.setFirstName(console.askUserForText("First name"));
        user.setLastName(console.askUserForText("Last name"));
        user.setAddress(console.askUserForText("Address"));
        user.setPhoneNumber(console.askUserForText("Phone number"));
        user.getPrivileges().add("READER");

        userService.addNewReader(user);
    }

    public void removeUser() {
        Long readerNumber = console.askUserForNumber("Reader number");
        Optional<String> errorMessage = userService.removeReader(readerNumber);
        errorMessage.ifPresent(System.out::println);
    }

    public void drinkCoffee() {
        console.printMessage("Just chill");
    }
}
