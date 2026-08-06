package c213.dosaoopproject.fahmida.session;

import c213.dosaoopproject.fahmida.model.ArrangeClubEvent;
import c213.dosaoopproject.fahmida.model.ClubInfo;
import c213.dosaoopproject.fahmida.model.CommunityServiceProgram;
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
    private static ArrangeClubEvent selectedEvent;
    private static ClubInfo selectedClub;
    private static CommunityServiceProgram selectedProgram;

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

    public static void setSelectedEvent(ArrangeClubEvent event) {
        selectedEvent = event;
    }

    public static ArrangeClubEvent getSelectedEvent() {
        return selectedEvent;
    }

    public static void setSelectedClub(ClubInfo club) {
        selectedClub = club;
    }

    public static ClubInfo getSelectedClub() {
        return selectedClub;
    }

    public static void setSelectedProgram(CommunityServiceProgram program) {
        selectedProgram = program;
    }

    public static CommunityServiceProgram getSelectedProgram() {
        return selectedProgram;
    }

    /** Ends the session (used by the Logout process). */
    public static void clear() {
        currentUser = null;
    }
}
