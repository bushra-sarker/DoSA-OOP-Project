package c213.dosaoopproject.Nahin.model.u_03;

import c213.dosaoopproject.Nahin.nonUser.Registration;

import java.time.LocalDate;

import static c213.dosaoopproject.Nahin.utility.VIA.*;

public class ClubEventRegister extends Registration {
    private final String clubName, eventName;
    private final String experience;

    public ClubEventRegister(int registrationId, String userId, String phonNumbr, String userName, String email, LocalDate registrationDate, String clubName, String eventName, String experience) {
        super(registrationId, userId, phonNumbr, userName, email, registrationDate);
        this.clubName = clubName;
        this.eventName = eventName;
        this.experience = experience;
    }

    public String getClubName() {
        return clubName;
    }

    public String getEventName() {
        return eventName;
    }

    public String getExperience() {
        return experience;
    }

    @Override
    public boolean validateRegistration() {
        return isValidId(getUserId()) && isValidEmail(getEmail()) &&
                isValidPhoneNumber(getPhonNumbr()) &&
                (experience.isEmpty() || characterLimit(getExperience(), 500));
    }

    @Override
    public String toString() {
        return "ClubEventRegister{" +
                "clubName='" + clubName + '\'' +
                ", eventName='" + eventName + '\'' +
                ", experience='" + experience + '\'' +
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