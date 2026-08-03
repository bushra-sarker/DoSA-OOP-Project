package c213.dosaoopproject.commonClass.data;

import java.io.*;
import java.util.List;

public class CSVFileUtil {

    public static List<String> readAllLines(String filePath) {
        return TextFileUtil.readAllLines(filePath);
    }

    public static boolean overwriteCSV(String filePath, String header, List<String> rows) {
        File file = new File(filePath);
        ensureFileExists(file);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) {
            if (header != null && !header.trim().isEmpty()) {
                bw.write(header);
                bw.newLine();
            }
            for (String row : rows) {
                bw.write(row);
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void ensureFileExists(File file) {
        try {
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}