package c213.dosaoopproject.Nahin.commonClass;

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

    public int getRegistrationId() {
        return registrationId;
    }

    public String getUserId() {
        return userId;
    }

    public String getPhonNumbr() {
        return phonNumbr;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public abstract boolean validateRegistration();

    @Override
    public String toString() {
        return "Registration{" +
                "registrationId=" + registrationId +
                ", userId='" + userId + '\'' +
                ", phonNumbr='" + phonNumbr + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
