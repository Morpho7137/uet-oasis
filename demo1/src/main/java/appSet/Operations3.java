package appSet;

import java.util.Map;

public class Operations3 {
    String fileName;

    public Operations3(String fileName) {
        this.fileName = fileName;
    }

    public String editWord(Map<String, String> wordMap, String wordToEdit, String newTarget, String newExplanation) {



        wordMap.remove(wordToEdit);

        wordMap.put(newTarget, newExplanation);


        Operations1 o1 = new Operations1();
        o1.overrideFile(wordMap, fileName);

        return ("Cập nhật thành công.");
    }

    public String deleteWord(Map<String, String> wordMap, String wordToDelete) {


        // Xóa từ
        wordMap.remove(wordToDelete);

        // Ghi lại từ điển vào file (hoặc database, hoặc bất kỳ cơ sở dữ liệu nào khác)
        Operations1 o1 = new Operations1();
        o1.overrideFile(wordMap, fileName);

        return "Xóa từ thành công.";
    }


}
