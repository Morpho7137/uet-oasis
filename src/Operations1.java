import java.io.*;
import java.util.Map;

public class Operations1 {
    public void insertFile(String fileName, Word wordMap) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length >= 2) {
                    String target = parts[0];
                    String explanation = parts[1];
                    wordMap.addWord(target, explanation);
                }
            }
            System.out.println("Successfully inserted words from file.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            throw e;
        }
    }
    public void overrideFile(Map<String, String> wordMap, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/dictionaryAdvanced.txt"))) {
            for (Map.Entry<String, String> entry : wordMap.entrySet()) {
                String target = entry.getKey();
                String explanation = entry.getValue();
                writer.write(target + "\t" + explanation); // Viết cặp key-value vào file, phân tách bởi dấu tab
                writer.newLine();
            }
            System.out.println("Successfully overridden file.");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}
