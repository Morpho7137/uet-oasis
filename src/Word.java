import java.util.TreeMap;
import java.util.Map;

public class Word {
    private Map<String, String> wordMap;
    public Map<String, String> getWordMap() {
        return wordMap;
    }

    public Word() {
        wordMap = new TreeMap<>();
    }

    public String getTarget() {
        return wordMap.get("target");
    }

    public void setTarget(String target) {
        wordMap.put("target", target);
    }

    public String getExplanation() {
        return wordMap.get("explanation");
    }

    public  void setExplanation(String explanation) {
        wordMap.put("explanation", explanation);
    }
    public void addWord(String target, String explanation) {
        wordMap.put(target, explanation);
    }
}
