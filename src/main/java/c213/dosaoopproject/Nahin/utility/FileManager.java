package c213.dosaoopproject.Nahin.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileManager {
    public static void writeFile(String fileName, Object data){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))){
            out.writeObject(data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Object readFile(String fileName){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))){
            return in.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
