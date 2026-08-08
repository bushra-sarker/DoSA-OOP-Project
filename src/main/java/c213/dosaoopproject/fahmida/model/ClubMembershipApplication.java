package c213.dosaoopproject.fahmida.model;

import java.io.Serializable;


public class ClubMembershipApplication implements Serializable {

    private static final long serialVersionUID = 1L;

    private int applicationId;
    private int studentId;
    private String clubName;
    private String major;
    private String reasonToJoin;
    private String skills;
    private String status;   // "Pending", "Approved", "Rejected"

    public ClubMembershipApplication(int applicationId, int studentId, String clubName,
                                     String major, String reasonToJoin, String skills) {
        this.applicationId = applicationId;
        this.studentId = studentId;
        this.clubName = clubName;
        this.major = major;
        this.reasonToJoin = reasonToJoin;
        this.skills = skills;
        this.status = "Pending";
    }

    public int getApplicationId() {
        return applicationId;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getClubName() {
        return clubName;
    }

    public String getMajor() {
        return major;
    }

    public String getReasonToJoin() {
        return reasonToJoin;
    }

    public String getSkills() {
        return skills;
    }

    public String getStatus() {
        return status;
    }

    public boolean approve() {
        this.status = "Approved";
        return true;
    }

    public boolean reject() {
        this.status = "Rejected";
        return true;
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }
}
