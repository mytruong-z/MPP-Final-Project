package model;

public class BookCopy {
    private int copyNumber;
    private String  isbn;
    private String publisher;
    private boolean availability = true;

    public BookCopy(String isbn, String publisher, int copyNumber) {
        this.copyNumber = copyNumber;
        this.isbn = isbn;
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getCopyNumber() {
        return copyNumber;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public boolean getAvailability() {
        return availability;
    }


}
