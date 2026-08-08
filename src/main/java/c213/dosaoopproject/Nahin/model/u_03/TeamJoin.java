package c213.dosaoopproject.Nahin.model.u_03;

import c213.dosaoopproject.Nahin.nonUser.Registration;

import java.time.LocalDate;

import static c213.dosaoopproject.Nahin.utility.VIA.*;

public class TeamJoin extends Registration {
    private final String selectTeam;
    private final String experience;
    private final String reason;

    public TeamJoin(int registrationId, String userId, String phonNumbr, String userName, String email, LocalDate registrationDate, String selectTeam, String experience, String reason) {
        super(registrationId, userId, phonNumbr, userName, email, registrationDate);
        this.selectTeam = selectTeam;
        this.experience = experience;
        this.reason = reason;
    }

    @Override
    public boolean validateRegistration() {
        return isValidId(getUserId()) && isValidEmail(getEmail()) &&
                isValidPhoneNumber(getPhonNumbr()) &&
                characterLimit(reason, 200);
    }

    public String getSelectTeam() {
        return selectTeam;
    }

    public String getExperience() {
        return experience;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "TeamJoin{" +
                "selectTeam='" + selectTeam + '\'' +
                ", experience='" + experience + '\'' +
                ", reason='" + reason + '\'' +
                ", registrationId=" + registrationId +
                ", userId='" + userId + '\'' +
                ", phonNumbr='" + phonNumbr + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
