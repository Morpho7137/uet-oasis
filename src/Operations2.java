import java.util.*;

public class Operations2 {
    public void searchWord(Map<String, String> wordMap) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Choose search option:");
        System.out.println("1. Search by target");
        System.out.println("2. Search by explaination");
        int option = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter the search term:");
        String searchTerm = scanner.nextLine().trim();

        switch (option) {
            case 1:
                searchByKey(wordMap, searchTerm);
                break;
            case 2:
                searchByValue(wordMap, searchTerm);
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private void searchByKey(Map<String, String> wordMap, String searchTerm) {
        if (wordMap.containsKey(searchTerm)) {
            System.out.println("Word found! Explanation: " + wordMap.get(searchTerm));
        } else {
            System.out.println("Word not found.");
        }
    }

    private void searchByValue(Map<String, String> wordMap, String searchTerm) {
        boolean found = false;
        for (Map.Entry<String, String> entry : wordMap.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(searchTerm)) {
                System.out.println("Word found! Target: " + entry.getKey());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Word not found.");
        }
    }
    public void searchWord2(Map<String, String> wordMap) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the search term:");
        String searchKey = scanner.nextLine().trim();
        // Tạo một danh sách để lưu trữ các key liên quan
        List<String> relatedKeys = new ArrayList<>();

        // Duyệt qua tất cả các key trong TreeMap
        for (String key : wordMap.keySet()) {
            // Kiểm tra nếu key chứa phần searchKey
            if (key.contains(searchKey)) {
                // Nếu có, thêm key này vào danh sách relatedKeys
                relatedKeys.add(key);
            }
        }


        if (relatedKeys.isEmpty()) {
            System.out.println("No words found related to '" + searchKey + "'.");
        } else {
            System.out.println("Words related to '" + searchKey + "':");
            for (String key : relatedKeys) {
                System.out.println(key + ": " + wordMap.get(key));
            }
        }
    }
}

