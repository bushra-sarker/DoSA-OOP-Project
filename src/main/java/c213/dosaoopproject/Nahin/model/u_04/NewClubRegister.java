package c213.dosaoopproject.Nahin.model.u_04;

import java.io.Serializable;
import java.time.LocalDate;

public class NewClubRegister implements Serializable {
    private final int applicationID;
    private final String contactNumber;
    private final String category;
    private final String clubName;
    private String founderName;
    private final String purpose;
    private String remarks;
    private String status;
    private final LocalDate submissionDate;

    public NewClubRegister(int applicationID, String category, String clubName, String founderName, String purpose, LocalDate submissionDate, String contactNumber) {
        this.applicationID = applicationID;
        this.category = category;
        this.clubName = clubName;
        this.founderName = founderName;
        this.purpose = purpose;
        this.submissionDate = submissionDate;
        this.contactNumber = contactNumber;
        this.status = "Pending";
        this.remarks = "";
    }

    public int getApplicationID() {
        return applicationID;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getCategory() {
        return category;
    }

    public String getClubName() {
        return clubName;
    }

    public String getFounderName() {
        return founderName;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public void markApprove(){
        this.status = "Approved";
    }
    public boolean markRejected(String remark){
        if(remark==null || remark.trim().isEmpty()){
            return false;
        }
        this.status="Rejected";
        this.remarks=remark;
        return true;
    }

    @Override
    public String toString() {
        return "NewClubRegister{" +
                "applicationID=" + applicationID +
                ", contactNumber='" + contactNumber + '\'' +
                ", category='" + category + '\'' +
                ", clubName='" + clubName + '\'' +
                ", founderName='" + founderName + '\'' +
                ", remarks='" + remarks + '\'' +
                ", status='" + status + '\'' +
                ", submissionDate=" + submissionDate +
                '}';
    }
}
