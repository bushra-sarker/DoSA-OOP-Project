package c213.dosaoopproject.commonClass.data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextFileUtil {

    public static ArrayList<String> readLines(String fileName) {

        ArrayList<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            while((line=br.readLine())!=null){
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static void writeLines(String fileName, ArrayList<String> lines){

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){

            for(String line:lines){
                bw.write(line);
                bw.newLine();
            }
        }catch(IOException e){
            e.printStackTrace();

        }

    }

}