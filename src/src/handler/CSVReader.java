package handler;

import model.Book;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class CSVReader {

    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        String property = System.getProperty("file.separator");
        String directoryPath = CSVReader.getClassDirectoryPath(CSVReader.class);

        String bookDirectory = STR."\{directoryPath}\{property}src\{property}dummy\{property}book.csv";
        System.out.println("Directory Path: " + bookDirectory);

        List<HashMap<String, String>> dataMap = readCSVIntoMap(bookDirectory);
        for (HashMap<String, String> map: dataMap) {
            Integer maxCheckoutLen = Integer.valueOf(map.get("maxCheckoutLength") != null ? map.get("maxCheckoutLength") : "0");
            books.add(
                    new Book(map.get("ibsn"), map.get("title"), map.get("author"), maxCheckoutLen)
            );
        }
    }



    public static String getClassDirectoryPath(Class<?> clazz) {
        URL url = clazz.getProtectionDomain().getCodeSource().getLocation();
        String filePath = url.getFile();
        try {
            filePath = java.net.URLDecoder.decode(filePath, "UTF-8");
        } catch (java.io.UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new File(filePath).getParent();
    }

    public static List<HashMap<String, String>> readCSVIntoMap(String csvFile) {
        List<HashMap<String, String>> result = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String[] headers = br.readLine().split(",");
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                HashMap<String, String> data = new HashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    data.put(headers[i], row[i]);
                }
                result.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result.size());
        return result;
    }
}
