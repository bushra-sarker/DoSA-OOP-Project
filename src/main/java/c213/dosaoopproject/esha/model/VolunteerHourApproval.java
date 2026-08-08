package c213.dosaoopproject.esha.model;

public class VolunteerHourApproval {
    private int approvalId;
    private int volunteerId;
    private double totalVolunteerHours;
    private String currentStatus; // "Pending", "Approved"

    public VolunteerHourApproval() {}

    public VolunteerHourApproval(int approvalId, int volunteerId,
                                 double totalVolunteerHours, String currentStatus) {
        this.approvalId = approvalId;
        this.volunteerId = volunteerId;
        this.totalVolunteerHours = totalVolunteerHours;
        this.currentStatus = currentStatus;
    }

    public int getApprovalId() { return approvalId; }
    public void setApprovalId(int approvalId) { this.approvalId = approvalId; }

    public int getVolunteerId() { return volunteerId; }
    public void setVolunteerId(int volunteerId) { this.volunteerId = volunteerId; }

    public double getTotalVolunteerHours() { return totalVolunteerHours; }
    public void setTotalVolunteerHours(double totalVolunteerHours) { this.totalVolunteerHours = totalVolunteerHours; }

    public String getCurrentStatus() { return currentStatus; }
    public void setCurrentStatus(String currentStatus) { this.currentStatus = currentStatus; }

    public boolean checkCertificateEligibility() {
        return "Approved".equals(currentStatus) && totalVolunteerHours >= 20.0;
    }

    @Override
    public String toString() {
        return "VolunteerHourApproval{approvalId=" + approvalId + ", volunteerId=" + volunteerId + ", status='" + currentStatus + "'}";
    }
}
