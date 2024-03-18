import model.*;
public class LibrarySystem {
    private static LibrarySystem instance;
    private LibrarySystem() {
    }

    public static LibrarySystem getInstance() {
        if (instance == null) instance = new LibrarySystem();
        return instance;
    }

    public void addMember(String memberNo, String firstName, String lastName, String phoneNumber,
                          String state, String city, String street, String zip) {

        Address address = initAddress(state, city, street, zip);
        LibraryMember libraryMember = new LibraryMember(memberNo, firstName, lastName, phoneNumber, address);

        //dataAccess.saveNewMember(libraryMember);


    }
    private Address initAddress(String state, String city, String street, String zip) {
        return new Address(state, city, street, zip);
    }
}
