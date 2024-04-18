import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Operations3 {

    public void editWord(Map<String, String> wordMap) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the word to edit:");
        String wordToEdit = scanner.nextLine().trim();

        if (!wordMap.containsKey(wordToEdit)) {
            System.out.println("Word not found.");
            return;
        }

        System.out.println("Enter the new target:");
        String newTarget = scanner.nextLine().trim();
        System.out.println("Enter the new explanation:");
        String newExplanation = scanner.nextLine().trim();

        // Xóa từ cũ
        wordMap.remove(wordToEdit);
        // Thêm từ mới
        wordMap.put(newTarget, newExplanation);

        // Ghi lại từ điển vào file
        Operations1 o1 = new Operations1();
        o1.overrideFile(wordMap, "src/dictionaryAdvanced.txt");

        System.out.println("Word edited and dictionary updated successfully.");
    }

    public void deleteWord(Map<String, String> wordMap) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the word to delete:");
        String wordToDelete = scanner.nextLine().trim();

        if (!wordMap.containsKey(wordToDelete)) {
            System.out.println("Word not found.");
            return;
        }

        // Xóa từ
        wordMap.remove(wordToDelete);

        // Ghi lại từ điển vào file
        Operations1 o1 = new Operations1();
        o1.overrideFile(wordMap, "src/dictionaryAdvanced.txt");

        System.out.println("Word deleted and dictionary updated successfully.");
    }

    public void addWord(Map<String, String> wordMap) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the new target:");
        String newTarget = scanner.nextLine().trim();
        System.out.println("Enter the new explanation:");
        String newExplanation = scanner.nextLine().trim();

        // Thêm từ mới
        wordMap.put(newTarget, newExplanation);

        // Ghi lại từ điển vào file
        Operations1 o1 = new Operations1();
        o1.overrideFile(wordMap, "src/dictionaryAdvanced.txt");

        System.out.println("Word added and dictionary updated successfully.");
    }
}
