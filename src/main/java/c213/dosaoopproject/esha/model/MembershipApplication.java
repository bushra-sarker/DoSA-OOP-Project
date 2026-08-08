package c213.dosaoopproject.esha.model;

public class MembershipApplication {
    private int applicationId;
    private int studentId;
    private String clubName;
    private String major;
    private String reasonToJoin;
    private String skills;
    private String status; // "Pending", "Approved", "Rejected"

    public MembershipApplication() {}

    public MembershipApplication(int applicationId, int studentId, String clubName,
                                 String major, String reasonToJoin, String skills, String status) {
        this.applicationId = applicationId;
        this.studentId = studentId;
        this.clubName = clubName;
        this.major = major;
        this.reasonToJoin = reasonToJoin;
        this.skills = skills;
        this.status = status;
    }

    public int getApplicationId() { return applicationId; }
    public void setApplicationId(int applicationId) { this.applicationId = applicationId; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getClubName() { return clubName; }
    public void setClubName(String clubName) { this.clubName = clubName; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public String getReasonToJoin() { return reasonToJoin; }
    public void setReasonToJoin(String reasonToJoin) { this.reasonToJoin = reasonToJoin; }

    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "MembershipApplication{applicationId=" + applicationId + ", studentId=" + studentId + ", status='" + status + "'}";
    }
}
