package commonClass.model;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userId;
    private String password;
    private String role;
    private String fxmlPath;
    private int failedAttempts;
    private boolean isLocked;

    public User(String userId, String password, String role, String fxmlPath) {
        this.userId = userId;
        this.password = password;
        this.role = role;
        this.fxmlPath = fxmlPath;
        this.failedAttempts = 0;
        this.isLocked = false;
    }

    // Getters & Setters
    public String getUserId() { return userId; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public String getFxmlPath() { return fxmlPath; }
    public int getFailedAttempts() { return failedAttempts; }
    public void setFailedAttempts(int failedAttempts) { this.failedAttempts = failedAttempts; }
    public boolean isLocked() { return isLocked; }
    public void setLocked(boolean locked) { isLocked = locked; }
}