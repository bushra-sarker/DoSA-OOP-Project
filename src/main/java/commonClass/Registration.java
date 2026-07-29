package commonClass;

import java.time.LocalDate;

public abstract class Registration {
    protected final int registrationId;
    protected final String userId;
    protected int phonNumbr;
    protected final String userName;
    protected String email,status;
    protected LocalDate date;

    public Registration(int registrationId, String userId, String userName, int phonNumbr, String email, String status, LocalDate date) {
        this.registrationId = registrationId;
        this.userId = userId;
        this.userName = userName;
        this.phonNumbr = phonNumbr;
        this.email = email;
        this.status = status;
        this.date = date;
    }

    public int getRegistrationId() {
        return registrationId;
    }

    public String getUserId() {
        return userId;
    }

    public int getPhonNumbr() {
        return phonNumbr;
    }

    public void setPhonNumbr(int phonNumbr) {
        this.phonNumbr = phonNumbr;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public abstract boolean validateRegistration();

    @Override
    public String toString() {
        return "Registration{" +
                "registrationId=" + registrationId +
                ", userId='" + userId + '\'' +
                ", phonNumbr=" + phonNumbr +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                ", date=" + date +
                '}';
    }
}
