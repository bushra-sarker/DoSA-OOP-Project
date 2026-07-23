package c213.dosaoopproject.commonClass;

public class SessionManager {
    private static User currentUser;
    public static User getCurrentUser(){
        return currentUser;
    }
    public static void setCurrentUser(User user){
        currentUser = user;
    }
}
