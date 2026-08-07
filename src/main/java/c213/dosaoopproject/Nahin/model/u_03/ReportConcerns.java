package c213.dosaoopproject.Nahin.model.u_03;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import static c213.dosaoopproject.Nahin.utility.FileManager.readFile;
import static c213.dosaoopproject.Nahin.utility.FileManager.writeFile;
import static c213.dosaoopproject.Nahin.utility.VIA.characterLimit;
import static c213.dosaoopproject.Nahin.utility.VIA.isValidId;

public class ReportConcerns implements Serializable {
    private final String userID;
    private final int complaintID;
    private final String eventName;
    private final String category;
    private final String complaintDetails;
    private final LocalDate date;
    private final LocalDate incidentDate;
    private String status;

    public ReportConcerns(String userID, int complaintID, String eventName, String category, String complaintDetails, LocalDate date, LocalDate incidentDate) {
        this.userID = userID;
        this.complaintID = complaintID;
        this.eventName = eventName;
        this.category = category;
        this.complaintDetails = complaintDetails;
        this.date = date;
        this.incidentDate = incidentDate;

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

    public LocalDate getIncidentDate() {
        return incidentDate;
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
                ", incidentDate=" + incidentDate +
                ", status='" + status + '\'' +
                '}';
    }

    public boolean validateInfo(){
        return isValidId(getUserID()) && characterLimit(getComplaintDetails(),1000) &&
                (!getIncidentDate().isAfter(LocalDate.now())) && getDate().equals(LocalDate.now());
    }


    public void markSolved(){
        this.status="Solved";
    }
    public void markInProgress(){this.status="In Progress";}
    public void markUnderReview(){
        this.status ="Under Review";
    }


    public static boolean updateStatus(String fileName,int complaintID, String selectedStatus){
        ArrayList<ReportConcerns> issueFile = readFile(fileName);
        if(issueFile==null){
            return false;
        }ReportConcerns found = null;


        for(ReportConcerns x:issueFile){
            if (x.getComplaintID()==complaintID){
                found = x;
            }
        }


        if(found==null){
            return false;
        }
        if(selectedStatus.equals("Under Review")){
            found.markUnderReview();
        }
        if(selectedStatus.equals("In Progress")){
            found.markInProgress();
        }
        if(selectedStatus.equals("Solved")){
            found.markSolved();
        }
        writeFile(fileName,issueFile);
        return true;
    }
}
