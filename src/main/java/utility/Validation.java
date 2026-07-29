package utility;

public class Validation {

    public static boolean isValidEmail(String email){
        return email !=null && email.endsWith("@iub.edu.bd");
    }

    public static boolean isValidPhoneNumber(int phoneNumber){
        String phone = String.valueOf(phoneNumber);
        return phone.length()!= 11 && phone.startsWith("01");
    }

    public static boolean isValidId(String id){
        return id!=null && id.length()==7;
    }

    public static boolean characterLimit(String text, int maxLimit){
        return  text!=null && text.length()<=maxLimit;
    }
}
