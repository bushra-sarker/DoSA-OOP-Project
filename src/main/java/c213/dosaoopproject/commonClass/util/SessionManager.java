package c213.dosaoopproject.commonClass.util;

import c213.dosaoopproject.commonClass.data.BinaryFileUtil;
import c213.dosaoopproject.commonClass.model.User;

import java.util.ArrayList;
import java.util.List;

public class SessionManager {

    private static final String DATA_FILE = "data/users.dat";
    private static SessionManager instance;

    private List<User> userList;
    private User currentUser;

    private SessionManager() {
        loadUserDatabase();
    }

    // Singleton Instance
    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    // ==========================
    // User Database Operations
    // ==========================

    public void loadUserDatabase() {
        List<User> loadedUsers = BinaryFileUtil.readObject(DATA_FILE);
        if (loadedUsers != null) {
            this.userList = loadedUsers;
        } else {
            this.userList = new ArrayList<>();
        }
    }

    public void saveUserDatabase() {
        BinaryFileUtil.writeObject(DATA_FILE, userList);
    }

    // ==========================
    // Session State Management
    // ==========================

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public void logout() {
        this.currentUser = null;
    }

    // ==========================
    // User Lookups & Helper Methods
    // ==========================

    public List<User> getAllUsers() {
        return userList;
    }

    public void setAllUsers(List<User> userList) {
        this.userList = userList;
    }

    public User getUser(String userId) {
        if (userId == null || userList == null) return null;

        for (User user : userList) {
            if (user.getUserId() != null && user.getUserId().equalsIgnoreCase(userId)) {
                return user;
            }
        }
        return null;
    }

    public void addUser(User user) {
        if (userList == null) {
            userList = new ArrayList<>();
        }
        userList.add(user);
        saveUserDatabase();
    }
}