package handler;

import model.*;

import java.lang.reflect.Member;
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
        String bookDirectory = getFilePath("book.csv");
        List<HashMap<String, String>> dataMap = readCSVIntoMap(bookDirectory);
        for (HashMap<String, String> map: dataMap) {
            var book = map.values().toArray();
            Integer maxCheckoutLen = Integer.valueOf(book[2].toString());
            books.add(
                new Book(
                    book[0].toString(),
                    book[1].toString(),
                    book[3].toString(),
                    maxCheckoutLen
                )
            );
        }
        return books;
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
        String title = "memberId, firstName, lastName, phone, address";
        dataWriter.add(title);
        for (LibraryMember member: allMembers) {
            String data = STR."\{member.memberId()},\{member.firstName()},\{member.lastName()},\{member.phone()},\{member.getAddress()}";
            dataWriter.add(data);
        }
        String memDirectory = getFilePath("member.csv");
        CSVWriter.writeCSV(memDirectory, dataWriter);
    }

    private static List<LibraryMember> getMembers() {
        List<LibraryMember> members = new ArrayList<>();
        String memDirectory = getFilePath("member.csv");
        List<HashMap<String, String>> dataMap = readCSVIntoMap(memDirectory);
        for (HashMap<String, String> map: dataMap) {
            var member = map.values().toArray();
            members.add(
                new LibraryMember(
                    member[0].toString(),
                    member[1].toString(),
                    member[2].toString(),
                    member[3].toString(),
                    new Address(member[4].toString(), member[5].toString(), member[6].toString(), member[7].toString())
                )
            );
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

    public static void saveMemberCheckoutRecord(String memberId, CheckoutRecordEntry entry) {
        List<LibraryMember> members = getMembers();
        for (LibraryMember member: members) {
            if (member.memberId().equals(memberId)) {
                member.addCheckOutRecordEntry(entry);
            }
        }
    }
}
