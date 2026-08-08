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

            userList.add(new User("HOD07", "1234567", "/c213/dosaoopproject/Bushra/U07/U07_HeadOfDoSADashboardView.fxml"));
            userList.add(new User("SWO08", "1234567", "/c213/dosaoopproject/Bushra/U08/U08_StudentWelfareOfficerDashboardView.fxml"));

            userList.add(new User("VOL02", "1234567", "/c213/dosaoopproject/Nahin/fxmlView/u3_dashBoard_view.fxml"));
            userList.add(new User("DO03", "1234567", "/c213/dosaoopproject/Nahin/fxmlView/u4_dashBoard.fxml"));

            userList.add(new User("STU01", "1234567", "/c213/dosaoopproject/fahmida/U1_Dashboard.fxml"));
            userList.add(new User("CA02", "1234567", "c213/dosaoopproject/fahmida/U2_Dashboard.fxml"));

            userList.add(new User("CE05", "1234567", "/c213/dosaoopproject/esha/ClubExecutiveDashboard.fxml"));
            userList.add(new User("DCSC06", "1234567", "/c213/dosaoopproject/esha/DoSACoordinatorDashboard.fxml"));

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