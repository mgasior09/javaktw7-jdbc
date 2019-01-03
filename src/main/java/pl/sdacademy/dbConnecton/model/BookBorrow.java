package pl.sdacademy.dbConnecton.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class BookBorrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn (name = "userId", referencedColumnName = "id")
    private LibraryUser user;
    @ManyToOne
    @JoinColumn (name = "bookId", referencedColumnName = "id")
    private Book book;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;

    public BookBorrow() {
    }

    public BookBorrow(Long id, LibraryUser user, Book book, LocalDateTime borrowDate, LocalDateTime returnDate) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LibraryUser getUser() {
        return user;
    }

    public void setUser(LibraryUser user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDateTime borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }
}
