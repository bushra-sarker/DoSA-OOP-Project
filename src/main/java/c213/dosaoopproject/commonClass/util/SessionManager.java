package c213.dosaoopproject.commonClass.util;

import c213.dosaoopproject.commonClass.model.User;
import java.io.*;
import java.util.ArrayList;

public class SessionManager {

    private static SessionManager instance;
    private ArrayList<User> userList = new ArrayList<>();
    private User currentUser;

    private SessionManager() {
        loadUserDatabase();
    }

    public static synchronized SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    // Returns the ArrayList directly
    public ArrayList<User> getAllUsers() {
        return userList;
    }

    @SuppressWarnings("unchecked")
    public void loadUserDatabase() {
        File file = new File("data/users.dat");
        System.out.println("Looking for users.dat at: " + file.getAbsolutePath());

        if (!file.exists()) {
            System.err.println("CRITICAL ERROR: users.dat file NOT FOUND at " + file.getAbsolutePath());
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            userList = (ArrayList<User>) ois.readObject();

            // Reset lockouts when loading from disk
            for (User user : userList) {
                user.setLocked(false);
                user.setFailedAttempts(0);
            }

            System.out.println("SUCCESS: Loaded " + userList.size() + " users into SessionManager.");
        } catch (Exception e) {
            System.err.println("FAILED TO LOAD users.dat:");
            e.printStackTrace();
        }
    }

    public void saveUserDatabase() {
        // Ensure data directory exists before saving
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/users.dat"))) {
            oos.writeObject(userList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isValidUserId(String userId) {
        for (User user : userList) {
            if (user.getUserId().equalsIgnoreCase(userId)) {
                return true;
            }
        }
        return false;
    }

    public User getUser(String userId) {
        for (User user : userList) {
            if (user.getUserId().equalsIgnoreCase(userId)) {
                return user;
            }
        }
        return null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}