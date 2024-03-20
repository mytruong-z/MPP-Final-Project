package handler;

import model.*;

import java.lang.reflect.Member;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static handler.CSVReader.readCSVIntoMap;

public class DataAccess {
//    public static void main(String[] args) {
//        Book book = new Book("yyyyyyyy", "this is Toan test", "Toan Nguyen", 123);
//        addBook(book);
//    }
    public static List<Book> getBooks() {
        List<Book> books = new ArrayList<Book>();
        try {
            String bookDirectory = getFilePath("book.csv");
            List<HashMap<String, String>> dataMap = readCSVIntoMap(bookDirectory);

            String bookCopyDirectory = getFilePath("bookCopy.csv");
            List<HashMap<String, String>> bookCopyDataMap = readCSVIntoMap(bookCopyDirectory);

            for (HashMap<String, String> map: dataMap) {
                var book = map.values().toArray();
                Integer maxCheckoutLen = Integer.valueOf(book[2].toString().trim());
                Book newBook = new Book(
                        book[0].toString(),
                        book[3].toString(),
                        book[1].toString(),
                        maxCheckoutLen
                );
                for (HashMap<String, String> bookCopyMap: bookCopyDataMap) {
                    var bookCopy = bookCopyMap.values().toArray();
                    if (bookCopy[0].toString().equals(newBook.isbn())) {
                        newBook.addCopy(
                                new BookCopy(
                                        bookCopy[0].toString(),
                                        bookCopy[2].toString(),
                                        Integer.parseInt(bookCopy[1].toString())
                                )
                        );
                    }
                }
                books.add(newBook);
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        finally {
            return books;
        }
    }

    public static void addBook(Book newBook) {
        List<String> dataWriter = new ArrayList<>();
        List<Book> allBooks = getBooks();
        allBooks.forEach(System.out::println);
        allBooks.add(newBook);
        String title = "isbn, author, title, maxCheckoutLength";
        dataWriter.add(title);
        for (Book book: allBooks) {
            String data = STR."\{book.isbn()},\{book.author()},\{book.title()},\{book.maxCheckoutLength()}";
            System.out.println(data);
            dataWriter.add(data);
        }
        String authDirectory = getFilePath("book.csv");
        CSVWriter.writeCSV(authDirectory, dataWriter);
    }

    public static List<Author> getAuthors() {
        List<Author> authors = new ArrayList<>();
        String authDirectory = getFilePath("author.csv");
        List<HashMap<String, String>> dataMap = readCSVIntoMap(authDirectory);
        for (HashMap<String, String> map: dataMap) {
            var author = map.values().toArray();
            authors.add(
                new Author(author[0].toString(), author[1].toString(), author[2].toString(), author[3].toString())
            );
        }
        return authors;
    }

    public static String getFilePath(String filename) {
        String property = System.getProperty("file.separator");
        String directoryPath = CSVReader.getClassDirectoryPath(CSVReader.class);
        return STR."\{directoryPath}\{property}src\{property}dummy\{property}\{filename}";
    }

    public static void addMember(LibraryMember newMember) {
        List<String> dataWriter = new ArrayList<>();
        List<LibraryMember> allMembers = getMembers();
        allMembers.add(newMember);
        String title = "memberId,fname,lname,phone,state,city,street,zip";
        dataWriter.add(title);
        for (LibraryMember member: allMembers) {
            String data = STR."\{member.memberId()},\{member.firstName()},\{member.lastName()},\{member.phone()},\{member.getAddress().state()},\{member.getAddress().city()},\{member.getAddress().street()},\{member.getAddress().zipCode()}";
            dataWriter.add(data);
        }
        String memDirectory = getFilePath("member.csv");
        CSVWriter.writeCSV(memDirectory, dataWriter);
    }

    public static List<LibraryMember> getMembers() {
        List<CheckoutRecordEntry> entries = getCheckOutRecordEntries();
        List<LibraryMember> members = new ArrayList<>();
        String memDirectory = getFilePath("member.csv");
        List<HashMap<String, String>> dataMap = readCSVIntoMap(memDirectory);
        for (HashMap<String, String> map: dataMap) {
            LibraryMember member = new LibraryMember(
                    map.get("memberId"),
                    map.get("fname"),
                    map.get("lname"),
                    map.get("phone"),
                    new Address(map.get("state"), map.get("city"), map.get("street"), map.get("zip"))
            );
            for (CheckoutRecordEntry entry: entries) {
                if (entry.getMemberId().equals(member.memberId())) {
                    member.addCheckOutRecordEntry(entry);
                }
            }
            members.add(member);
        }
        return members;
    }

    public static Optional<Book> searchBook(String isbn) {
        List<Book> books = getBooks();
        for (Book book: books) {
            if (book.isbn().equals(isbn)) {
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }

    public static Optional<LibraryMember> searchMember(String memberId) {
        List<LibraryMember> members = getMembers();
        for (LibraryMember member: members) {
            if (member.memberId().equals(memberId)) {
                return Optional.of(member);
            }
        }
        return Optional.empty();
    }

    public static BookCopy nextAvailableBookCopy(String isbn) {
        List<Book> books = getBooks();
        for (Book book: books) {
            if (book.isbn().equals(isbn)) {
                List<BookCopy> copies = book.copies();
                for (BookCopy copy: copies) {
                    if (copy.getAvailability()) {
                        return copy;
                    }
                }
            }
        }
        return null;
    }
    public static void addBookCopy(String isbnNumber, String publisher, int copyNumber) {
        List<String> dataWriter = new ArrayList<>();
        List<BookCopy> allBookCopies = getBookCopies();
        allBookCopies.add(new BookCopy(isbnNumber, publisher, copyNumber));
        String title = "isbn,publisher,copyNumber,availability";
        dataWriter.add(title);
        for (BookCopy copy: allBookCopies) {
            String data = STR."\{copy.getIsbn()},\{copy.getPublisher()},\{copy.getCopyNumber()},\{copy.getAvailability()}";
            dataWriter.add(data);
        }
        String bookCopyDirectory = getFilePath("bookCopy.csv");
        CSVWriter.writeCSV(bookCopyDirectory, dataWriter);

    }

    private static List<BookCopy> getBookCopies() {
        List<BookCopy> bookCopies = new ArrayList<>();
        String bookCopyDirectory = getFilePath("bookCopy.csv");
        List<HashMap<String, String>> dataMap = readCSVIntoMap(bookCopyDirectory);
        for (HashMap<String, String> map: dataMap) {
            var bookCopy = map.values().toArray();
            bookCopies.add(
                    new BookCopy(
                            map.get("isbn"),
                            map.get("publisher"),
                            Integer.parseInt(map.get("copyNumber"))                    )
            );
        }
        return bookCopies;
    }

    public static void saveMemberCheckoutRecord(CheckoutRecordEntry entry) {
        List<String> dataWriter = new ArrayList<>();
        List<CheckoutRecordEntry> entries = getCheckOutRecordEntries();
        entries.add(entry);
        String title = "checkoutDate,dueDate,memberId,isbn";
        dataWriter.add(title);
        for (CheckoutRecordEntry recordEntry: entries) {
            String data = STR."\{recordEntry.checkoutDate()},\{recordEntry.dueDate()},\{recordEntry.getMemberId()},\{recordEntry.getIsbn()}";
            dataWriter.add(data);
        }
        String entryDirectory = getFilePath("checkoutRecordEntry.csv");
        CSVWriter.writeCSV(entryDirectory, dataWriter);

    }

    private static List<CheckoutRecordEntry> getCheckOutRecordEntries() {
        List<CheckoutRecordEntry> entries = new ArrayList<>();
        String entryDirectory = getFilePath("checkoutRecordEntry.csv");
        List<HashMap<String, String>> dataMap = readCSVIntoMap(entryDirectory);
        for (HashMap<String, String> map: dataMap) {
            var entry = map.values().toArray();
            entries.add(
                    new CheckoutRecordEntry(
                            LocalDate.parse(map.get("checkoutDate")),
                            LocalDate.parse(map.get("dueDate")),
                            map.get("memberId"),
                            map.get("isbn")
                    )
            );
        }
        return entries;
    }
}
