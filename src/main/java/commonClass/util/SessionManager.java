package commonClass.util;

import commonClass.model.User;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SessionManager {

    private static SessionManager instance;
    private final Map<String, User> userDatabase = new HashMap<>();
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

    public void loadUserDatabase() {
        File file = new File("users.dat");
        System.out.println("Looking for users.dat at: " + file.getAbsolutePath());

        if (!file.exists()) {
            System.err.println("CRITICAL ERROR: users.dat file NOT FOUND at " + file.getAbsolutePath());
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            ArrayList<User> users = (ArrayList<User>) ois.readObject();
            userDatabase.clear();
            for (User user : users) {
                userDatabase.put(user.getUserId(), user);
            }
            System.out.println("SUCCESS: Loaded " + userDatabase.size() + " users into SessionManager.");
        } catch (Exception e) {
            System.err.println("FAILED TO LOAD users.dat:");
            e.printStackTrace();
        }
    }

    public void saveUserDatabase() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.dat"))) {
            ArrayList<User> users = new ArrayList<>(userDatabase.values());
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isValidUserId(String userId) {
        return userDatabase.containsKey(userId);
    }

    public User getUser(String userId) {
        return userDatabase.get(userId);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}