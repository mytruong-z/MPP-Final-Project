package model;

public class Address {
    private String street;
    private String city;
    private String state;
    private String zipCode;

    public Address(String state, String city, String street, String zipCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
    public String street() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String city() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String state() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String zipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
