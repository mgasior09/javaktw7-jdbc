package pl.sdacademy.dbConnecton.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String isbn;
    private String format;
    @ManyToMany
    @JoinTable(name = "BookAuthor", joinColumns = @JoinColumn (name = "bookId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn (name = "authorId", referencedColumnName = "id"))
    private List<Writer> authors;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Location location;
    private boolean removed;

    public Book() {
        authors = new ArrayList<>();
        removed = false;
    }

    public Book(Long id, String name, String isbn, String format, List<Writer> authors, Category category, Location location) {
        this();
        this.id = id;
        this.name = name;
        this.isbn = isbn;
        this.format = format;
        this.authors = authors;
        this.category = category;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public List<Writer> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Writer> authors) {
        this.authors = authors;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }
}
