package model;

import java.time.LocalDate;

public class CheckoutRecordEntry {
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private BookCopy bookCopy;
    private CheckoutRecord checkOutRecord;

    public CheckoutRecordEntry(LocalDate checkoutDate, LocalDate dueDate, BookCopy bookCopy) {
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.bookCopy = bookCopy;
    }

    public CheckoutRecord checkOutRecord() {
        return checkOutRecord;
    }


    public void setCheckOutRecord(CheckoutRecord checkOutRecord) {
        this.checkOutRecord = checkOutRecord;
    }
    public BookCopy bookCopy() {
        return bookCopy;
    }
    public LocalDate checkoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public LocalDate dueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
