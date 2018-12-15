package pl.sdacademy.dbConnecton.repository;

import pl.sdacademy.dbConnecton.model.Author;
import pl.sdacademy.dbConnecton.service.repository.AuthorRepository;

import java.sql.*;
import java.util.Optional;

public class JdbcAuthorRepository implements AuthorRepository {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/library?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "!DzikiSQL";

    @Override
    public Optional<Author> findByFirstAndLastName(String firstName, String lastName) {
        Author foundAuthor = null;
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT id AS mojeID, firstName, lastName FROM author WHERE firstName = '" + firstName + "' and lastName = '" + lastName + "'");
            if (rs.next()) {
                long authorID = rs.getLong("mojeID");
                String authorName = rs.getString("firstName");
                String authorLastName = rs.getString(3);
                foundAuthor = new Author(authorID, authorName, authorLastName);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(foundAuthor);
    }

    @Override
    public Optional<Author> findById(Long id) {
        Author foundAuthor = null;
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(String.format("SELECT id, firstName, lastName FROM author WHERE id =%d", id));
            if (rs.next()) {
                long authorID = rs.getLong("id");
                String authorName = rs.getString("firstName");
                String authorLastName = rs.getString("lastName");
                foundAuthor = new Author(authorID, authorName, authorLastName);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(foundAuthor);
    }

    @Override
    public Author save(Author entity) {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            Statement statement = con.createStatement();
            statement.execute("INSERT INTO Author (firstName, lastName) " + "VALUES ('" + entity.getFirstName() + "','" + entity.getLastName() + "')");
            ResultSet rs = statement.executeQuery("SELECT MAX(id) AS maxID FROM Author");
            if (rs.next()) {
                long authorID = rs.getLong("maxID");
                entity.setId(authorID);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public Author update(Author entity) {
        return null;
    }
}
