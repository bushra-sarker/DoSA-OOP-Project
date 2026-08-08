package c213.dosaoopproject.esha.model;

public class Certificate {
    private int certificateId;
    private int approvalId;
    private int volunteerId;
    private double totalHours;
    private String certificateStatus; // "Not Generated", "Generated"

    public Certificate() {}

    public Certificate(int certificateId, int approvalId, int volunteerId,
                       double totalHours, String certificateStatus) {
        this.certificateId = certificateId;
        this.approvalId = approvalId;
        this.volunteerId = volunteerId;
        this.totalHours = totalHours;
        this.certificateStatus = certificateStatus;
    }

    public int getCertificateId() { return certificateId; }
    public void setCertificateId(int certificateId) { this.certificateId = certificateId; }

    public int getApprovalId() { return approvalId; }
    public void setApprovalId(int approvalId) { this.approvalId = approvalId; }

    public int getVolunteerId() { return volunteerId; }
    public void setVolunteerId(int volunteerId) { this.volunteerId = volunteerId; }

    public double getTotalHours() { return totalHours; }
    public void setTotalHours(double totalHours) { this.totalHours = totalHours; }

    public String getCertificateStatus() { return certificateStatus; }
    public void setCertificateStatus(String certificateStatus) { this.certificateStatus = certificateStatus; }

    public void generate() {
        this.certificateStatus = "Generated";
    }

    @Override
    public String toString() {
        return "Certificate{certificateId=" + certificateId + ", volunteerId=" + volunteerId + ", status='" + certificateStatus + "'}";
    }
}
