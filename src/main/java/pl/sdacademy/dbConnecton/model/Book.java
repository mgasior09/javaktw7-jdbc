package pl.sdacademy.dbConnecton.model;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private Long id;
    private String name;
    private String isbn;
    private String format;
    private List<Long> authors;
    private Long category;
    private Long location;
    private boolean removed;

    public Book() {
        authors = new ArrayList<Long>();
        removed = false;
    }

    public Book(Long id, String name, String isbn, String format, List<Long> authors, Long category, Long location) {
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

    public List<Long> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Long> authors) {
        this.authors = authors;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public Long getLocation() {
        return location;
    }

    public void setLocation(Long location) {
        this.location = location;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }
}
