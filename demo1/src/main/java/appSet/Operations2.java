package appSet;

import java.util.*;

public class Operations2 {
    Map<String,String> WordMap;

    public Operations2(Map<String, String> wordMap) {
        WordMap = wordMap;
    }

    public String searchByKey(String searchTerm) {

        String searchTermLowerCase = searchTerm.toLowerCase();


        for (Map.Entry<String, String> entry : WordMap.entrySet()) {
            if (entry.getKey().toLowerCase().equals(searchTermLowerCase)) {
                return entry.getValue();
            }
        }

        return "Word not found.";
    }

    public List<String> suggestWords(String input) {

        String inputLowerCase = input.toLowerCase();

        List<String> suggestedWords = new ArrayList<>();


        for (Map.Entry<String, String> entry : WordMap.entrySet()) {
            if (entry.getKey().toLowerCase().contains(inputLowerCase)) {
                suggestedWords.add(entry.getKey());
            }
        }

        return suggestedWords;
    }
}


