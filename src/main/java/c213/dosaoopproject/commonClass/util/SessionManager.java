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

    // User Database Operations

    public void loadUserDatabase() {
        // Use readList instead of readObjects!
        List<User> loadedUsers = BinaryFileUtil.readList(DATA_FILE);

        if (loadedUsers != null && !loadedUsers.isEmpty()) {
            this.userList = loadedUsers;
        } else {
            this.userList = new ArrayList<>();

            userList.add(new User("HOD07", "1234", "/c213/dosaoopproject/Bushra/U07/U07_HeadOfDoSADashboardView.fxml"));
            userList.add(new User("CLUB201", "1234", "/c213/dosaoopproject/commonFXML/ClubPresidentDashboard.fxml"));
            userList.add(new User("STU301", "1234", "/c213/dosaoopproject/commonFXML/StudentDashboard.fxml"));
            userList.add(new User("OFC401","1234","/Nahin/fxmlView/u4_dashBoard.fxml"));
            userList.add(new User("VOL101","1234","/Nahin/fxmlView/u3_dashBoard_view.fxml"));
            saveUserDatabase();
        }
    }

    public void saveUserDatabase() {
        BinaryFileUtil.saveList(DATA_FILE, new ArrayList<>(this.userList));
    }

    // Session State Management

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

    // User Lookups & Helper Methods

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

}