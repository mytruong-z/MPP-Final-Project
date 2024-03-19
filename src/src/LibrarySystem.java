import handler.DataAccess;
import model.*;

import java.time.LocalDate;
import java.util.Optional;

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

        DataAccess.addMember(libraryMember);
    }

    public void checkOutBook(String memberId, String isbn) {
        Optional<Book> book = DataAccess.searchBook(isbn);
        Optional<LibraryMember> member = DataAccess.searchMember(memberId);

        if (member.isPresent() && book.isPresent()) {
            BookCopy availableBookCopy = DataAccess.nextAvailableBookCopy(isbn);
            LocalDate todaysDate = LocalDate.now();
            int checkOutLength = book.get().maxCheckoutLength();
            LocalDate dueDate = todaysDate.plusDays(checkOutLength);

            CheckoutRecordEntry checkoutRecordEntry = new CheckoutRecordEntry(todaysDate, dueDate, availableBookCopy);
            availableBookCopy.setAvailability(false);

            DataAccess.saveMemberCheckoutRecord(memberId, checkoutRecordEntry);
        } else if (member.isEmpty()) {
           //Show error message: Member not found

        } else if (book.isEmpty()) {
            //Show error message: Book not found
        }

    }
    private Address initAddress(String state, String city, String street, String zip) {
        return new Address(state, city, street, zip);
    }

    public void addBook(Book book) {
        DataAccess.addBook(book);
    }
}
