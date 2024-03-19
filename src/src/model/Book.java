package model;

import java.util.ArrayList;
import java.util.List;

import static java.lang.StringTemplate.STR;

public class Book {
    private String isbn;
    private String author;
    private String title;
    private Integer maxCheckoutLength;
    private List<BookCopy> copies;

    public Book(String isbn, String title, String author, Integer maxCheckoutLenght) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.maxCheckoutLength = maxCheckoutLenght;
    }
    public List<BookCopy> copies() {
        return copies;
    }
    public void addCopy(BookCopy copy) {
        if (copies == null) {
            copies = new ArrayList<>();
        }
        copy.setBook(this);
        copies.add(copy);
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
