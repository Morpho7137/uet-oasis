package dictionary.server;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class Dictionary {

    /**
     * Khởi tạo từ điển khi khởi động ứng dụng. (Chỉ bị ghi đè bởi
     * DatabaseDictionary để tạo kết nối MYSQL)
     */
    public void initialize() throws SQLException {}

    /**
     * Đóng từ điển khi thoát ứng dụng. (Chỉ bị ghi đè bởi DatabaseDictionary đối với
     * đóng kết nối MYSQL)
     */
    public void close() {}

    /**
     * Nhận tất cả các từ trong từ điển.
     *
     * @return ArrayList của Word
     */
    public abstract ArrayList<Word> getAllWords();

    /**
     * Lấy tất cả các từ tiếng Anh trong từ điển vào một ArrayList
     *
     * @return ArrayList của tất cả các từ
     */
    public abstract ArrayList<String> getAllWordTargets();

    /**
     * Tra từ `target` đưa ra ý nghĩa tương ứng.
     *
     * @param target từ tra cứu
     * @return định nghĩa, nếu không tìm thấy trả về"404".
     */
    public abstract String lookUpWord(final String target);

    /**
     * Thêm từ mới.
     *
     * @param target từ cần thêm
     * @param definition ý nghĩa
     * @return true nếu `target` chưa được thêm vào, false nếu ngược lại
     */
    public abstract boolean insertWord(final String target, final String definition);

    /**
     * Xóa từ `target`.
     *
     * @param target từ muốn xóa
     * @return true nếu thành công, false nếu ngược lại
     */
    public abstract boolean deleteWord(final String target);

    /**
     * Thay đổi định nghĩa `target` thành `definition`.
     *
     * @param target từ cần đổi
     * @param definition định nghĩa mới
     * @return true nếu thành công, false nếu ngược lại
     */
    public abstract boolean updateWordDefinition(final String target, final String definition);

    /**
     * Xuất tất cả các từ với mỗi từ và định nghĩa nằm trên 1 dòng cách nhau bằng ký tự tab.
     *
     * @return một chuỗi các từ đã xuất
     */
    public String exportAllWords() {
        ArrayList<Word> allWords = getAllWords();
        StringBuilder result = new StringBuilder();
        for (Word word : allWords) {
            result.append(word.getWordTarget())
                    .append('\t')
                    .append(word.getWordDefinition())
                    .append('\n');
        }
        return result.toString();
    }

    /**
     *Xuất tất cả các từ và ý nghĩa của chúng sang tệp `exportPath`.
     *
     * @param exportPath đường dẫn tệp xuất
     * @throws IOException nếu không thấy
     */
    public void exportToFile(String exportPath) throws IOException {
        Writer out =
                new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(exportPath), StandardCharsets.UTF_8));
        String export = exportAllWords();
        out.write(export);
        out.close();
    }
}
