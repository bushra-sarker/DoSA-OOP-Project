package c213.dosaoopproject.commonClass.data;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import java.io.*;

public class BinaryFileUtil {

    // Save a single object (overwrites the file)
    public static <T extends Serializable> void saveObject(String fileName, T object) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Append a single object to an existing file
    public static <T extends Serializable> void appendObject(String fileName, T object) {
        File file = new File(fileName);

        try (ObjectOutputStream out = file.exists() && file.length() > 0
                             ? new AppendableObjectOutputStream(new FileOutputStream(file, true))
                             : new ObjectOutputStream(new FileOutputStream(file, true))) {

            out.writeObject(object);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read all objects stored one after another
    public static <T> ArrayList<T> readObjects(String fileName) {
        File file = new File(fileName);

        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {

            return (ArrayList<T>) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Save an entire ArrayList
    public static <T extends Serializable> void saveList(String fileName, ArrayList<T> list) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read an entire ArrayList
    public static <T> ArrayList<T> readList(String fileName) {

        File file = new File(fileName);

        if (!file.exists() || file.length() == 0)
            return new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (ArrayList<T>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    // write object
    public static <T> void writeObjects(String fileName, List<Object> list) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Custom ObjectOutputStream to avoid writing header while appending
    private static class AppendableObjectOutputStream extends ObjectOutputStream {

        public AppendableObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            reset();
        }
    }
}