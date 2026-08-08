package c213.dosaoopproject.Nahin.utility;

import javafx.scene.control.Alert;

public class VIA {

    // Email validation
    public static boolean isValidEmail(String email){
        return email !=null && email.endsWith("@iub.edu.bd");
    }

    // Phone Number Validation
    public static boolean isValidPhoneNumber(String phoneNumber){
        return phoneNumber!=null && phoneNumber.length()== 11 && phoneNumber.startsWith("01") && phoneNumber.chars().allMatch(Character::isDigit);}

    // Id validation
    public static boolean isValidId(String id){
        return id!=null && id.length()==7;
    }

    // maximum character validation
    public static boolean characterLimit(String text, int maxLimit){
        return  text!=null && text.length()<=maxLimit;
    }



//unique ID generate
    public static int generateRegistrationId(){
        Integer lastID = FileManager.readFile("registrationID.bin");
        if(lastID==null){
            lastID=101288330;
        }
        lastID++;

        FileManager.writeFile("registrationID.bin",lastID);
        return lastID;
    }




    //to show alert
    public static void showWaitAlert(Alert.AlertType type, String msg){
        Alert a = new Alert(type);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }
    public static void showAlert(Alert.AlertType type, String msg){
        Alert a = new Alert(type);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.show();
    }
}
