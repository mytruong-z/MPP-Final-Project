package handler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter {
    public static void writeCSV(String csvFile, List<String> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            for (String row : data) {
                StringBuilder sb = new StringBuilder(row);
                sb.append("\n");
                writer.write(sb.toString());
            }
            System.out.println("CSV file written successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
