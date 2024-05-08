package appSet;

import java.util.TreeMap;
import java.util.Map;

public class Word {
    private static Map<String, String> wordMap = new TreeMap<>();
    private static Map<String, String> wordMap2 = new TreeMap<>();
    private static Map<String, String> wordMap3 = new TreeMap<>();
    public static Map<String, String> getWordMap3()  {return Word.wordMap3;}
    public static Map<String, String> getWordMap() {
        return Word.wordMap;
    }
    public static Map<String,String> getWordMap2() {return Word.wordMap2;}




    public static void addWord(Map<String, String> wordMap,String target, String explanation) {
        wordMap.put(target, explanation);
    }

}
