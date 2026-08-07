package c213.dosaoopproject.Nahin.utility;

import static c213.dosaoopproject.Nahin.utility.FileManager.readFile;

public class IdGenerator {
    public static int generateRegistrationId(){
        Integer lastID =(Integer) readFile("registrationID.bin");
                if(lastID==null){
                    lastID=100330;
                }
                lastID++;

                FileManager.writeFile("registrationID.bin",lastID);
                return lastID;
    }
}
