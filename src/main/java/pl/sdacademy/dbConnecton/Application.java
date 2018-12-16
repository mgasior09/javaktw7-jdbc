package pl.sdacademy.dbConnecton;

import pl.sdacademy.dbConnecton.controller.ConsoleDelegate;
import pl.sdacademy.dbConnecton.controller.LibrarianController;
import pl.sdacademy.dbConnecton.controller.LoginController;
import pl.sdacademy.dbConnecton.controller.ReaderController;
import pl.sdacademy.dbConnecton.model.LibraryUser;
import pl.sdacademy.dbConnecton.repository.JpaLibraryUserRepository;
import pl.sdacademy.dbConnecton.repository.JpaLocationRepository;
import pl.sdacademy.dbConnecton.repository.JpaWriterRepository;
import pl.sdacademy.dbConnecton.service.DefaultBookService;
import pl.sdacademy.dbConnecton.service.DefaultLoginService;
import pl.sdacademy.dbConnecton.service.DefaultUserService;
import pl.sdacademy.dbConnecton.service.repository.*;

public class Application {

    private final ConsoleDelegate console;
    private final LoginController loginController;
    private final ReaderController readerController;
    private final LibrarianController librarianController;

    public Application() {
        console = new ConsoleDelegate();

        AuthorRepository authorRepository = new JpaWriterRepository();
        BookBorrowRepository bookBorrowRepository = null;
        BookRepository bookRepository = null;
        LocationRepository locationRepository = new JpaLocationRepository();
        UserRepository userRepository = new JpaLibraryUserRepository();

        DefaultBookService defaultBookService = new DefaultBookService(locationRepository, authorRepository, bookBorrowRepository, bookRepository);
        DefaultLoginService defaultLoginService = new DefaultLoginService(userRepository);
        DefaultUserService defaultUserService = new DefaultUserService(userRepository, bookBorrowRepository);

        loginController = new LoginController(console, defaultLoginService);
        readerController = new ReaderController(console, defaultBookService);
        librarianController = new LibrarianController(console, defaultBookService, defaultUserService);
    }

    public void start() {
        handleLoginMenu();
    }

    private void handleLoginMenu() {
        while (true) {
            console.printMessage("### Welcome in library ###");
            printLoginMenu();
            try {
                Integer userSelection = getUserSelection();
                switch (userSelection) {
                    case 1:
                        loginController.login().ifPresent(this::handleLoggedUserMenu);
                        break;
                    case 9:
                        console.printMessage("Enjoy your books");
                        return;
                    default:
                        System.out.println("Invalid selection");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void printLoginMenu() {
        console.printMessage("1. Login");
        console.printMessage("9. Exit");
    }

    private void handleLoggedUserMenu(LibraryUser libraryUser) {
        while (true) {
            console.printMessage("# Hello " + libraryUser.getFirstName());
            printLoggedUserMenu(libraryUser);
            try {
                Integer userSelection = getUserSelection();
                switch (userSelection) {
                    case 1:
                        readerController.listBooks(libraryUser);
                        break;
                    case 2:
                        readerController.borrowNewBook(libraryUser);
                        break;
                    case 3:
                        readerController.returnBook(libraryUser);
                        break;
                    case 9:
                        console.printMessage("Logged out");
                        return;
                    case 0:
                        if (libraryUser.isAdmin()) {
                            handleAdministrativeMenu(libraryUser);
                        }
                        break;
                    default:
                        System.out.println("Invalid selection");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void printLoggedUserMenu(LibraryUser libraryUser) {
        console.printMessage("1. List borrowed books");
        console.printMessage("2. Borrow a book");
        console.printMessage("3. Return a book");
        console.printMessage("9. Log out");
        if (libraryUser.isAdmin()) {
            console.printMessage("0. Manage library");
        }
    }

    private void handleAdministrativeMenu(LibraryUser libraryUser) {
        while(true){
            printLibrarianMenu();
            Integer choose = getUserSelection();
            switch (choose) {
                case 1:
                    librarianController.addBook();
                    break;
                case 2:
                    librarianController.removeBook();
                    break;
                case 3:
                    librarianController.registerUser();
                    break;
                case 4:
                    librarianController.removeUser();
                    break;
                case 5:
                    librarianController.drinkCoffee();
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Invalid selection");
            }
        }
    }

    private void printLibrarianMenu() {
        console.printMessage("1. Add book");
        console.printMessage("2. Remove book");
        console.printMessage("3. Register new user");
        console.printMessage("4. Remove user");
        console.printMessage("5. Drink coffee");
        console.printMessage("9. Exit");
    }

    private Integer getUserSelection() {
        return console.askUserForNumber("Select").intValue();
    }
}
