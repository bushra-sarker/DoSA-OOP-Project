package c213.dosaoopproject.Nahin.utility;

public class Validation {

    // Email validation
    public static boolean isValidEmail(String email){
        return email !=null && email.endsWith("@iub.edu.bd");
    }


    // Phone Number Validation
    public static boolean isValidPhoneNumber(String phoneNumber){
        return phoneNumber!=null && phoneNumber.length()== 11 && phoneNumber.startsWith("01") && phoneNumber.chars().allMatch(Character::isDigit);
    }

    // Id validation
    public static boolean isValidId(String id){
        return id!=null && id.length()==7;
    }


    // maximum character validation
    public static boolean characterLimit(String text, int maxLimit){
        return  text!=null && text.length()<=maxLimit;
    }
}
