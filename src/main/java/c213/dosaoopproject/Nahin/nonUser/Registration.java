package c213.dosaoopproject.Nahin.nonUser;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Registration implements Serializable {
    protected final int registrationId;
    protected final String userId;
    protected final String phonNumbr;
    protected final String userName;
    protected final String email;
    protected final String status;
    protected final LocalDate registrationDate;

    public Registration(int registrationId, String userId, String phonNumbr, String userName, String email, LocalDate registrationDate) {
        this.registrationId = registrationId;
        this.userId = userId;
        this.phonNumbr = phonNumbr;
        this.userName = userName;
        this.email = email;
        this.status = "Pending";
        this.registrationDate = registrationDate;
    }

    public String getUserId() {
        return userId;
    }

    public String getPhonNumbr() {
        return phonNumbr;
    }

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }

    public abstract boolean validateRegistration();
}
