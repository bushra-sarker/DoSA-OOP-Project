package c213.dosaoopproject.Bushra.U07.model;

public class ClubBudget {
    private String clubName;
    private double requestedAmount;
    private double allocatedAmount;
    private String status;
    private String remarks;

    public ClubBudget(String clubName, double requestedAmount, double allocatedAmount, String status, String remarks) {
        this.clubName = clubName;
        this.requestedAmount = requestedAmount;
        this.allocatedAmount = allocatedAmount;
        this.status = status;
        this.remarks = remarks;
    }

    // Convert to comma-separated text line for saving to file
    public String toFileLine() {
        return clubName + "," + requestedAmount + "," + allocatedAmount + "," + status + "," + remarks;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public double getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(double requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public double getAllocatedAmount() {
        return allocatedAmount;
    }

    public void setAllocatedAmount(double allocatedAmount) {
        this.allocatedAmount = allocatedAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}