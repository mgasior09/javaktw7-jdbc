package pl.sdacademy.dbConnecton.service;

import pl.sdacademy.dbConnecton.controller.service.UserService;
import pl.sdacademy.dbConnecton.model.BookBorrow;
import pl.sdacademy.dbConnecton.model.User;
import pl.sdacademy.dbConnecton.service.repository.BookBorrowRepository;
import pl.sdacademy.dbConnecton.service.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class DefaultUserService implements UserService {
    private final UserRepository userRepository;
    private final BookBorrowRepository bookBorrowRepository;

    public DefaultUserService(UserRepository userRepository, BookBorrowRepository bookBorrowRepository) {
        this.userRepository = userRepository;
        this.bookBorrowRepository = bookBorrowRepository;
    }

    @Override
    public User addNewReader(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<String> removeReader(Long readerNumber) {
        List<BookBorrow> borrowList = bookBorrowRepository.findByUserId(readerNumber);
        if (!borrowList.isEmpty()) {
            return Optional.of("Reader with borrowed books cannot be removed");
        }
        Optional<User> foundUser = userRepository.findById(readerNumber);
        if (!foundUser.isPresent()) {
            return Optional.of("User does not exits");
        }
        User user = foundUser.get();
        user.setRemoved(true);
        userRepository.update(user);
        return Optional.empty();
    }
}
