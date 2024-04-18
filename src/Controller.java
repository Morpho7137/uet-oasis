import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Controller {

    public static void main(String[] args) {
        // Tạo đối tượng Word và đọc dữ liệu từ file dictionaryAdvanced.txt
        Word word = new Word();
        Operations1 operations1 = new Operations1();
        try {
            operations1.insertFile("src/dictionaryAdvanced.txt", word);
            System.out.println("Data loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
            return;
        }

        // Hiển thị menu và xử lý lựa chọn của người dùng
        Scanner scanner = new Scanner(System.in);
        Operations3 operations3 = new Operations3();
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Search word (method 1)");
            System.out.println("2. Search word (method 2)");
            System.out.println("3. Edit word");
            System.out.println("4. Delete word");
            System.out.println("5. Add word");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    Operations2 operations2 = new Operations2();
                    operations2.searchWord(word.getWordMap());
                    break;
                case 2:
                    Operations2 operations2_2 = new Operations2();
                    operations2_2.searchWord2(word.getWordMap());
                    break;
                case 3:
                    operations3.editWord(word.getWordMap());
                    break;
                case 4:
                    operations3.deleteWord(word.getWordMap());
                    break;
                case 5:
                    operations3.addWord(word.getWordMap());
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }
}
