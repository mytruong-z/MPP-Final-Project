package model;

import static java.lang.StringTemplate.STR;

public class Book {
    private String isbn;
    private String author;
    private String title;
    private Integer maxCheckoutLength;

    public Book(String isbn, String title, String author, Integer maxCheckoutLenght) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.maxCheckoutLength = maxCheckoutLenght;
    }

    public String isbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String author() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String title() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer maxCheckoutLength() {
        return maxCheckoutLength;
    }

    public void setMaxCheckoutLength(Integer maxCheckoutLength) {
        this.maxCheckoutLength = maxCheckoutLength;
    }

    @Override
    public String toString() {
        return STR."\{isbn},\{author},\{title},\{maxCheckoutLength}";
    }
}
