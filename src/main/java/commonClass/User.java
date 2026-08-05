package commonClass;

import java.io.Serializable;

/**
 * Abstract base class for every kind of DoSA user.
 *
 * <p>This is the root of the role hierarchy ({@code Student}, {@code ClubAdvisor},
 * {@code HeadOfDoSA}, ...). It holds the fields every user shares and defines the
 * behaviour that differs per role via {@link #getRole()} and
 * {@link #getDashboardFxml()} (polymorphism).</p>
 *
 * <p>Implements {@link Serializable} so users can be written to / read from the
 * binary data file by {@code DataStore}.</p>
 */
public abstract class User implements Serializable {

    private static final long serialVersionUID = 1L;

    protected int userId;
    /** Short code the user types to log in, e.g. {@code "STU01"} or {@code "ADV01"}. */
    protected String loginId;
    protected String passwordHash;
    protected String fullName;

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
