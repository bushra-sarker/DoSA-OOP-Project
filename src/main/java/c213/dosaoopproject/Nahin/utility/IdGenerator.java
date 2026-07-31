package c213.dosaoopproject.Nahin.utility;

public class IdGenerator {
    private static int id = 1000;
    public static int generateRegistrationId(){
        Integer lastID =(Integer) FileManager.readFile("registrationID.bin");
                if(lastID==null){
                    lastID=1000;
                }
                lastID++;

                FileManager.writeFile("registrationID.bin",lastID);
                return lastID;
    }
}
