package c213.dosaoopproject.commonClass.model;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L; // Ensures compatibility

    private String userId;
    private String password;
    private String role;
    private String fxmlPath;
    private int failedAttempts = 0;
    private boolean locked = false;

    // Constructors, getters, setters...
    public User(String userId, String password, String role, String fxmlPath) {
        this.userId = userId;
        this.password = password;
        this.role = role;
        this.fxmlPath = fxmlPath;
    }

    public String getUserId() { return userId; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public String getFxmlPath() { return fxmlPath; }
    public int getFailedAttempts() { return failedAttempts; }
    public void setFailedAttempts(int failedAttempts) { this.failedAttempts = failedAttempts; }
    public boolean isLocked() { return locked; }
    public void setLocked(boolean locked) { this.locked = locked; }
}