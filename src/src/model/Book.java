package model;

public class Book {
    private String isbn;
    private String author;
    private String title;
    private Integer maxCheckoutLength;

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
}
