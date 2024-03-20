import handler.DataAccess;
import model.*;

import javax.xml.crypto.Data;
import java.lang.reflect.Member;
import java.time.LocalDate;
import java.util.List;
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

    public Boolean checkOutBook(String memberId, String isbn) {
        //Check if member exists
        Optional<LibraryMember> member = DataAccess.searchMember(memberId);
        if (member.isEmpty()) {
            //Show error message: Member not found
            System.out.println("Member not found");
            return false;
        }
        //Check if book exists
        Optional<Book> book = DataAccess.searchBook(isbn);
        if (book.isEmpty()) {
            //Show error message: Book not found
            System.out.println("Book not found");
            return false;
        }
       //Check if book is available
        BookCopy availableBookCopy = DataAccess.nextAvailableBookCopy(isbn);
        if (availableBookCopy == null) {
            //Show error message: Book not available
            System.out.println("Book not available");
            return false;
        }
        //Check out book
        CheckoutRecordEntry checkoutRecordEntry = new CheckoutRecordEntry(
                LocalDate.now(),
                LocalDate.now().plusDays(book.get().maxCheckoutLength()),
                isbn,
                memberId
        );
        member.get().addCheckOutRecordEntry(checkoutRecordEntry);

        //Save changes
        DataAccess.saveMemberCheckoutRecord(checkoutRecordEntry);
        return true;
    }
    private Address initAddress(String state, String city, String street, String zip) {
        return new Address(state, city, street, zip);
    }

    public void addBook(Book book) {
        DataAccess.addBook(book);
    }

    public List<Book> getAllBooks() {
        return DataAccess.getBooks();
    }

    public List<LibraryMember> getAllMembers(){
        return DataAccess.getMembers();
    }

    public void addBookCopy(String isbnNumber, String publisher, int copyNumber) {
        DataAccess.addBookCopy(isbnNumber, publisher, copyNumber);
    }
}
