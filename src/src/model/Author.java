package model;

public class Author {
    private String firstName;
    private String lastName;
    private String credentials;
    private String phone;

    public Author(String firstName, String lastName, String phone, String credentials) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.credentials = credentials;
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

    public String credentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public String phone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
