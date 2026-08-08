package c213.dosaoopproject.fahmida.utility;

import java.io.*;

/**
 * Generic binary read/write helper — saves any {@link Serializable} object to
 * a file and reads it back, one file per call. Shared file format with
 * Nahin's {@code c213.dosaoopproject.Nahin.utility.FileManager}, so files she
 * writes (e.g. {@code campaigns.bin}) can be read here too.
 */
public class FileManager {

    public static boolean writeFile(String fileName, Object data) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(data);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T readFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            return null;
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            return (T) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
