package commonClass.util;

import commonClass.data.BinaryFileUtil;
import commonClass.model.User;

import java.util.ArrayList;

public class SessionManager {
    private static SessionManager instance;
    private User currentUser;
    private final String DATA_FILE = "users.dat";
    private ArrayList<User> userList;

    private SessionManager() {
        loadUserDatabase();
    }

    public static synchronized SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void loadUserDatabase() {
        // Uses readList method
        userList = BinaryFileUtil.readList(DATA_FILE);
    }

    public void saveUserDatabase() {
        // Uses saveList method
        BinaryFileUtil.saveList(DATA_FILE, userList);
    }

    public User getUser(String userId) {
        for (User user : userList) {
            if (user.getUserId().equalsIgnoreCase(userId)) {
                return user;
            }
        }
        return null;
    }

    public boolean isValidUserId(String userId) {
        return getUser(userId) != null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}