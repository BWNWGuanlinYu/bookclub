package com.bookclub.model;

import java.util.List;

public class Book {
    private String isbn;
    private String title;
    private String description;
    private int numOfPages;
    private String infoUrl;

    //default constructor
    public Book() {
    }

    /**
     * Constructor
     * @param isbn
     * @param title
     * @param description
     * @param numOfPages
     * @param infoUrl
     */
    public Book(String isbn, String title, String description, String infoUrl, int numOfPages) {
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.numOfPages = numOfPages;
        this.infoUrl = infoUrl;
    }

    public Book(String isbn, String title, String infoUrl) {
        this.isbn = isbn;
        this.title = title;
        this.infoUrl = infoUrl;
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

    public int getNumOfPages() {
        return numOfPages;
    }

    public void setNumOfPages(int numberOfPages) {
        this.numOfPages = numOfPages;
    }

    public String getInfoUrl() {
        return infoUrl;
    }

    public void setInfoUrl(String infoUrl) {
        this.infoUrl = infoUrl;
    }

    //toString method
    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + "', title='" + title + "', description='" + description +
                "', infoUrl='" + infoUrl + "', numOfPages=" + numOfPages +
                '}';
    }
}
