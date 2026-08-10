package commonClass;

import java.io.Serializable;


public abstract class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Account is locked after this many consecutive failed logins. */
    public static final int MAX_FAILED_ATTEMPTS = 3;

    protected int userId;
    /** Short code the user types to log in, e.g. {@code "STU01"} or {@code "ADV01"}. */
    protected String loginId;
    protected String passwordHash;
    protected String fullName;
    protected int failedAttempts;
    protected boolean locked;

    public User(String fullName, String passwordHash, int userId) {
        this.fullName = fullName;
        this.passwordHash = passwordHash;
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public boolean isLocked() {
        return locked;
    }

    /** Records one wrong-password attempt; locks the account at the limit. */
    public void recordFailedAttempt() {
        failedAttempts++;
        if (failedAttempts >= MAX_FAILED_ATTEMPTS) {
            locked = true;
        }
    }

    /** Number of attempts left before lockout (0 once locked). */
    public int attemptsRemaining() {
        return Math.max(0, MAX_FAILED_ATTEMPTS - failedAttempts);
    }

    /** Clears the failed-attempt counter after a successful login. */
    public void resetFailedAttempts() {
        failedAttempts = 0;
    }

    public String getFullName() {
        return fullName;
    }

    /**
     * Loads the role-specific dashboard. Kept for compatibility with the class
     * diagram; navigation is actually driven by {@link #getDashboardFxml()}.
     */
    public abstract void loadDashboard();

    /** Human-readable role name shown in the UI, e.g. {@code "Student"}. */
    public String getRole() {
        return "User";
    }

    /**
     * FXML file (relative to {@code c213/dosaoopproject/fahmida/}) that this
     * user's dashboard should load after a successful login. Subclasses override.
     */
    public String getDashboardFxml() {
        return null;
    }

    public final boolean login() {
        // placeholder
        return true;
    }

    public final void logout() {
        // placeholder
    }
}
