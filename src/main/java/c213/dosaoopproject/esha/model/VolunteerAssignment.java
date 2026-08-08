package c213.dosaoopproject.esha.model;

public class VolunteerAssignment {
    private int approvalId;
    private int volunteerId;
    private double totalVolunteerHours;
    private String hourStatus;        // "Pending", "Approved"
    private String certificateStatus; // "Not Eligible", "Eligible", "Certificate Generated"

    private static final double ELIGIBILITY_HOURS_THRESHOLD = 20.0;

    public VolunteerAssignment() {}

    public VolunteerAssignment(int approvalId, int volunteerId, double totalVolunteerHours,
                               String hourStatus, String certificateStatus) {
        this.approvalId = approvalId;
        this.volunteerId = volunteerId;
        this.totalVolunteerHours = totalVolunteerHours;
        this.hourStatus = hourStatus;
        this.certificateStatus = certificateStatus;
    }

    public int getApprovalId() { return approvalId; }
    public void setApprovalId(int approvalId) { this.approvalId = approvalId; }

    public int getVolunteerId() { return volunteerId; }
    public void setVolunteerId(int volunteerId) { this.volunteerId = volunteerId; }

    public double getTotalVolunteerHours() { return totalVolunteerHours; }
    public void setTotalVolunteerHours(double totalVolunteerHours) { this.totalVolunteerHours = totalVolunteerHours; }

    public String getHourStatus() { return hourStatus; }
    public void setHourStatus(String hourStatus) { this.hourStatus = hourStatus; }

    public String getCertificateStatus() { return certificateStatus; }
    public void setCertificateStatus(String certificateStatus) { this.certificateStatus = certificateStatus; }

    public boolean validateRecord() {
        return totalVolunteerHours > 0;
    }

    public boolean isEligibleForCertificate() {
        return "Approved".equals(hourStatus) && totalVolunteerHours >= ELIGIBILITY_HOURS_THRESHOLD;
    }

    @Override
    public String toString() {
        return "VolunteerAssignment{approvalId=" + approvalId + ", volunteerId=" + volunteerId
                + ", hourStatus='" + hourStatus + "', certificateStatus='" + certificateStatus + "'}";
    }
}
