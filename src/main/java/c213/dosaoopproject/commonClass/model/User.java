package c213.dosaoopproject.commonClass.model;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userId;
    private String password;
    private String fxmlPath; // Target dashboard for this specific user account

    private boolean isLocked;
    private int failedAttempts;

    public User() {}

    public User(String userId, String password, String fxmlPath) {
        this.userId = userId;
        this.password = password;
        this.fxmlPath = fxmlPath;
        this.isLocked = false;
        this.failedAttempts = 0;
    }

    // Getters & Setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFxmlPath() { return fxmlPath; }
    public void setFxmlPath(String fxmlPath) { this.fxmlPath = fxmlPath; }

    public boolean isLocked() { return isLocked; }
    public void setLocked(boolean locked) { isLocked = locked; }

    public int getFailedAttempts() { return failedAttempts; }
    public void setFailedAttempts(int failedAttempts) { this.failedAttempts = failedAttempts; }
}