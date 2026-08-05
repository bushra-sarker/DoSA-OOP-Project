package c213.dosaoopproject.fahmida.session;

import commonClass.User;

/**
 * Holds the currently logged-in user for the lifetime of the running app.
 *
 * <p>A deliberately tiny replacement for the "session token / sessionsManager"
 * described in the specification — enough for a desktop app where only one user
 * is logged in at a time.</p>
 */
public final class Session {

    private static User currentUser;

    private Session() {
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    /** Ends the session (used by the Logout process). */
    public static void clear() {
        currentUser = null;
    }
}
