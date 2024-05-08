package appSet;

import java.io.*;
import java.util.Map;
import java.util.Scanner;

public class Operations1 {
    public void insertFile(String fileName, Map<String, String> wordMap) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Scanner scanner = new Scanner(line).useDelimiter("\t");

                if (scanner.hasNext()) {
                    String target = scanner.next();
                    String explanation = scanner.hasNext() ? scanner.next().replace("\\n", "\n") : "";
                    Word.addWord(wordMap, target, explanation);
                }

                scanner.close();
            }
            System.out.println("Successfully inserted words from file.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            throw e;
        }
    }

    public void overrideFile(Map<String, String> wordMap, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, String> entry : wordMap.entrySet()) {
                String target = entry.getKey();
                String explanation = entry.getValue().replace("\n", "\\n"); // Thay thế ký tự xuống dòng bằng chuỗi "\\n"
                writer.write(target + "\t" + explanation); // Viết cặp key-value vào file, phân tách bởi dấu tab
                writer.newLine();
            }
            System.out.println("Successfully overridden file.");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

}
