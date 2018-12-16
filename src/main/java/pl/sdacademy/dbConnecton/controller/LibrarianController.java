package pl.sdacademy.dbConnecton.controller;

import pl.sdacademy.dbConnecton.controller.service.BookService;
import pl.sdacademy.dbConnecton.controller.service.UserService;
import pl.sdacademy.dbConnecton.model.*;

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
        List<Writer> writers = new ArrayList<>();
        Book book = new Book();
        Location location = new Location();
        Optional<String> errorMessage = bookService.addNewBook(book, writers, location);
        errorMessage.ifPresent(System.out::println);
    }

    public void removeBook() {
        Long bookNumber = console.askUserForNumber("Book number");
        Optional<String> errorMessage = bookService.removeBook(bookNumber);
        errorMessage.ifPresent(System.out::println);
    }

    public void registerUser() {
        LibraryUser libraryUser = new LibraryUser();
        libraryUser.setUsername(console.askUserForText("Username"));
        libraryUser.setPassword(console.askUserForText("Password"));
        libraryUser.setFirstName(console.askUserForText("First name"));
        libraryUser.setLastName(console.askUserForText("Last name"));
        libraryUser.setHomeAddress(console.askUserForText("Address"));
        libraryUser.setPhoneNumber(console.askUserForText("Phone number"));
        libraryUser.getPrivileges().add(new UserRole(null,"READER"));

        userService.addNewReader(libraryUser);
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
