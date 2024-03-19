package handler;

import model.Author;
import model.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

}
