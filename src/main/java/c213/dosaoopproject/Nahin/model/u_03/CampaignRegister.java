package c213.dosaoopproject.Nahin.model.u_03;

import c213.dosaoopproject.Nahin.commonClass.Registration;
import c213.dosaoopproject.Nahin.utility.Validation;
import java.time.LocalDate;

public class CampaignRegister extends Registration {
    private final String campaignName;
    private final LocalDate date;
    private final String notes;
    private final String location;

    public CampaignRegister(int registrationId, String userId, String phonNumbr, String userName, String email, LocalDate registrationDate, String campaignName, LocalDate date, String notes, String location) {
        super(registrationId, userId, phonNumbr, userName, email, registrationDate);
        this.campaignName = campaignName;
        this.date = date;
        this.notes = notes;
        this.location = location;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getNotes() {
        return notes;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public boolean validateRegistration() {
        return Validation.isValidId(getUserId()) && Validation.isValidPhoneNumber(getPhonNumbr())
        && Validation.isValidEmail(getEmail()) &&
                (notes.isEmpty() || Validation.characterLimit(notes,200));
    }

    @Override
    public String toString() {
        return "CampaignRegister{" +
                "campaignName='" + campaignName + '\'' +
                ", date=" + date +
                ", notes='" + notes + '\'' +
                ", location='" + location + '\'' +
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
