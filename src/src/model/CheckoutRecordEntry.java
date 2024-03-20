package model;

import java.time.LocalDate;

public class CheckoutRecordEntry {
    private LocalDate checkoutDate;
    private LocalDate dueDate;

    private String isbn;
    private String memberId;
    private CheckoutRecord checkOutRecord;

    public CheckoutRecordEntry(LocalDate checkoutDate, LocalDate dueDate, String memberId, String isbn) {
        this.isbn = isbn;
        this.memberId = memberId;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CheckoutRecordEntry{");
        sb.append("checkoutDate=").append(checkoutDate);
        sb.append(", dueDate=").append(dueDate);
        sb.append('}');
        return sb.toString();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public CheckoutRecord checkOutRecord() {
        return checkOutRecord;
    }


    public void setCheckOutRecord(CheckoutRecord checkOutRecord) {
        this.checkOutRecord = checkOutRecord;
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
