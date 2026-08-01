package c213.dosaoopproject.Nahin.model.u_03;

import c213.dosaoopproject.Nahin.utility.Validation;

import java.io.Serializable;
import java.time.LocalDate;

public class ReportConcerns implements Serializable {
    private final String userID;
    private final int complaintID;
    private final String eventName;
    private final String category;
    private final String complaintDetails;
    private final LocalDate date;
    private final String incidentTime;
    private String status;

    public ReportConcerns(String userID, int complaintID, String eventName, String category, String complaintDetails, LocalDate date, String incidentTime) {
        this.userID = userID;
        this.complaintID = complaintID;
        this.eventName = eventName;
        this.category = category;
        this.complaintDetails = complaintDetails;
        this.date = date;
        this.incidentTime = incidentTime;

        this.status = "Pending";
    }

    public String getUserID() {
        return userID;
    }

    public int getComplaintID() {
        return complaintID;
    }

    public String getEventName() {
        return eventName;
    }

    public String getCategory() {
        return category;
    }

    public String getComplaintDetails() {
        return complaintDetails;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getIncidentTime() {
        return incidentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReportConcerns{" +
                "userID='" + userID + '\'' +
                ", complaintID=" + complaintID +
                ", eventName='" + eventName + '\'' +
                ", category='" + category + '\'' +
                ", complaintDetails='" + complaintDetails + '\'' +
                ", date=" + date +
                ", incidentTime=" + incidentTime +
                ", status='" + status + '\'' +
                '}';
    }

    public boolean validateInfo(){
        return Validation.isValidId(getUserID()) && Validation.characterLimit(getComplaintDetails(),1000);
    }
}
