package c213.dosaoopproject.Nahin.model.u_03;

import c213.dosaoopproject.Nahin.commonClass.Registration;
import c213.dosaoopproject.Nahin.utility.Validation;
import java.time.LocalDate;

public class TeamJoin extends Registration{
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
        return Validation.isValidId(getUserId()) && Validation.isValidEmail(getEmail()) &&
                Validation.isValidPhoneNumber(getPhonNumbr()) &&
                Validation.characterLimit(reason,200);
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
