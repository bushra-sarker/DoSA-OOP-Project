package c213.dosaoopproject.fahmida.model;

import commonClass.User;

/**
 * User-2: a Club Advisor. Inherits identity fields from {@link User} and adds
 * {@code email} plus the id of the club this advisor manages.
 */
public class ClubAdvisor extends User {

    private static final long serialVersionUID = 1L;

    private String email;
    private int clubId;

    public ClubAdvisor(String name, String passwordHash, int advisorId,
                       String loginId, String email, int clubId) {
        super(name, passwordHash, advisorId);
        setLoginId(loginId);
        this.email = email;
        this.clubId = clubId;
    }

    public int getAdvisorId() {
        return getUserId();
    }

    public String getEmail() {
        return email;
    }

    public int getClubId() {
        return clubId;
    }

    @Override
    public String getRole() {
        return "Club Advisor";
    }

    @Override
    public String getDashboardFxml() {
        return "/c213/dosaoopproject/fahmida/U2_Dashboard.fxml";
    }

    @Override
    public void loadDashboard() {
        // Navigation is handled by SceneManager using getDashboardFxml().
    }
}
