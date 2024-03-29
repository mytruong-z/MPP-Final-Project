package model;

import java.util.List;

public class LibraryMember {
    private String memberId;
    private String firstName;
    private String lastName;
    private String phone;

    private Address address;
    private CheckoutRecord checkoutRecord;

    public LibraryMember(String memberId, String firstName, String lastName, String phone, Address address) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        checkoutRecord = new CheckoutRecord();
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String memberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String firstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String lastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String phone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void addCheckOutRecordEntry(CheckoutRecordEntry entry) {
        if(checkoutRecord == null) {
            checkoutRecord = new CheckoutRecord();
        }
        checkoutRecord.addCheckOutRecordEntry(entry);
    }

    public CheckoutRecord getCheckoutRecord() {
        return checkoutRecord;
    }

    @Override
    public String toString() {
        return
                "memberId='" + memberId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", address=" + address.toString();
    }

}


