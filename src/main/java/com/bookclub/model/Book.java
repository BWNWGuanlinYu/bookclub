package com.bookclub.model;

import java.util.List;

public class Book {
    private String isbn;
    private String title;
    private String description;
    private int numberOfPages;
    private List<String> authors;

    //default constructor
    public Book() {
    }

    /**
     * Constructor
     * @param isbn
     * @param title
     * @param description
     * @param numberOfPages
     * @param authors
     */
    public Book(String isbn, String title, String description, int numberOfPages, List<String> authors) {
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.numberOfPages = numberOfPages;
        this.authors = authors;
    }

    //getters and setters
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    //toString method
    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + ", title='" + title + ", description='" + description + ", numberOfPages=" + numberOfPages + ", authors=" + authors +
                '}';
    }
}
