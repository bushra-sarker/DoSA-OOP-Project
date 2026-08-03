package c213.dosaoopproject.commonClass.data;

import java.io.*;

public class BinaryFileUtil {

    public static boolean writeObject(String filePath, Object object) {
        File file = new File(filePath);
        ensureFileExists(file);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file, false))) {
            oos.writeObject(object);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static <T> T readObject(String filePath) {
        File file = new File(filePath);
        if (!file.exists() || file.length() == 0) return null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (T) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
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