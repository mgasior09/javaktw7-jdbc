package pl.sdacademy.dbConnecton.controller.service;

import pl.sdacademy.dbConnecton.model.LibraryUser;

import java.util.Optional;

public interface UserService {
    LibraryUser addNewReader(LibraryUser libraryUser);
    Optional<String> removeReader(Long readerNumber);
}
