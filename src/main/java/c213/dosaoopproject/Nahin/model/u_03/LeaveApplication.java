package c213.dosaoopproject.Nahin.model.u_03;

import c213.dosaoopproject.Nahin.utility.Validation;
import java.io.Serializable;
import java.time.LocalDate;

public class LeaveApplication implements Serializable {
    private final String userID;
    private final int applicationId;
    private final String activityName;
    private final String details;
    private final String reason;
    private final LocalDate date;
    private String status;

    public LeaveApplication(String userID, int applicationId, String activityName, String details, String reason, LocalDate date) {
        this.userID = userID;
        this.applicationId = applicationId;
        this.activityName = activityName;
        this.details = details;
        this.reason = reason;
        this.date = date;

        this.status = "Pending";
    }

    public String getUserID() {
        return userID;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public String getActivityName() {
        return activityName;
    }

    public String getDetails() {
        return details;
    }

    public String getReason() {
        return reason;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean validateRequestInfo(){
        return Validation.isValidId(getUserID()) &&
                Validation.characterLimit(getDetails(),500);
    }

    @Override
    public String toString() {
        return "LeaveApplication{" +
                "userID=" + userID +
                ", applicationId=" + applicationId +
                ", activityName='" + activityName + '\'' +
                ", detials='" + details + '\'' +
                ", reason='" + reason + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                '}';
    }
}