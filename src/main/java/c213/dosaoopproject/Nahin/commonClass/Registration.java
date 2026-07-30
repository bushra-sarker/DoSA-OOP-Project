package c213.dosaoopproject.Nahin.commonClass;

import java.time.LocalDate;

public abstract class Registration {
    protected final int registrationId;
    protected final String userId;
    protected String phonNumbr;
    protected final String userName;
    protected String email,status;
    protected LocalDate date;

    public Registration(int registrationId, String userId, String userName, String phonNumbr, String email, LocalDate date) {
        this.registrationId = registrationId;
        this.userId = userId;
        this.userName = userName;
        this.phonNumbr = phonNumbr;
        this.email = email;
        this.date = date;

        this.status = "Pending";
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

    public void setPhonNumbr(String phonNumbr) {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public void approveRegistration(){
        this.status = "Approved";
    }

    public void rejectRegistration(){
        this.status = "Rejected";
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
