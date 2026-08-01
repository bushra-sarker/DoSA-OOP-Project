package c213.dosaoopproject.Nahin.utility;

public class IdGenerator {
    public static int generateRegistrationId(){
        Integer lastID =(Integer) FileManager.readFile("registrationID.bin");
                if(lastID==null){
                    lastID=101288330;
                }
                lastID++;

                FileManager.writeFile("registrationID.bin",lastID);
                return lastID;
    }
}
