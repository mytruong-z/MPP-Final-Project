package handler;

import model.Author;
import model.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static handler.CSVReader.readCSVIntoMap;

public class DataAccess {
    public static List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        String bookDirectory = getFilePath("book.csv");
        List<HashMap<String, String>> dataMap = readCSVIntoMap(bookDirectory);
        for (HashMap<String, String> map: dataMap) {
            Integer maxCheckoutLen = Integer.valueOf(map.get("maxCheckoutLength") != null ? map.get("maxCheckoutLength") : "0");
            books.add(
                    new Book(map.get("ibsn"), map.get("title"), map.get("author"), maxCheckoutLen)
            );
        }
        return books;
    }

    public static List<Author> getAuthors() {
        List<Author> authors = new ArrayList<>();
        String authDirectory = getFilePath("author.csv");
        List<HashMap<String, String>> dataMap = readCSVIntoMap(authDirectory);
        for (HashMap<String, String> map: dataMap) {
            authors.add(
                    new Author(map.get("firstName"), map.get("lastName"), map.get("phone"), map.get("credentials"))
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
