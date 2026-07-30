package c213.dosaoopproject.Nahin.model.u_03;

import c213.dosaoopproject.Nahin.commonClass.Registration;
import c213.dosaoopproject.Nahin.utility.Validation;

import java.time.LocalDate;

public class EventRegister extends Registration {
    private final String clubName, eventName;
    private String experience;

    public EventRegister(int registrationId, String userId, String userName, String phonNumbr, String email, LocalDate date, String clubName, String eventName, String experience) {
        super(registrationId, userId, userName, phonNumbr, email, date);
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
        if(userId==null||userId.isEmpty())
            return false;
        if(userName==null||userName.isEmpty())
            return false;
        if(email==null||email.isEmpty())
            return false;
        if (phonNumbr==null)
            return false;
        if(date==null)
            return false;
        if(experience!=null && !experience.isEmpty()){
            if(!Validation.characterLimit(experience,500))
                return false;
        }

        return Validation.isValidId(userId) &&
                Validation.isValidEmail(email) && Validation.isValidPhoneNumber(phonNumbr);
    }
}