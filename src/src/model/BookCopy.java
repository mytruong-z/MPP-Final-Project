package model;

public class BookCopy {
    private int copyNumber;
    private boolean availability;
    private Book book;


    public BookCopy(int copyNumber) {
        this.copyNumber = copyNumber;
        this.availability = true;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book =  book;
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
