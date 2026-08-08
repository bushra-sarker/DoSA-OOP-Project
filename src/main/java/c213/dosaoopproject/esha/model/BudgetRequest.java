package c213.dosaoopproject.esha.model;

public class BudgetRequest {
    private int budgetId;
    private int executiveId;
    private String eventType;
    private String expectedEventDate;
    private double estimatedAmount;
    private String status; // "Pending", "Approved", "Rejected"
    private String coordinatorRemarks;

    public BudgetRequest() {}

    public BudgetRequest(int budgetId, int executiveId, String eventType,
                         String expectedEventDate, double estimatedAmount,
                         String status) {
        this.budgetId = budgetId;
        this.executiveId = executiveId;
        this.eventType = eventType;
        this.expectedEventDate = expectedEventDate;
        this.estimatedAmount = estimatedAmount;
        this.status = status;
        this.coordinatorRemarks = "";
    }

    public int getBudgetId() { return budgetId; }
    public void setBudgetId(int budgetId) { this.budgetId = budgetId; }

    public int getExecutiveId() { return executiveId; }
    public void setExecutiveId(int executiveId) { this.executiveId = executiveId; }

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }

    public String getExpectedEventDate() { return expectedEventDate; }
    public void setExpectedEventDate(String expectedEventDate) { this.expectedEventDate = expectedEventDate; }

    public double getEstimatedAmount() { return estimatedAmount; }
    public void setEstimatedAmount(double estimatedAmount) { this.estimatedAmount = estimatedAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getCoordinatorRemarks() { return coordinatorRemarks; }
    public void setCoordinatorRemarks(String coordinatorRemarks) { this.coordinatorRemarks = coordinatorRemarks; }

    @Override
    public String toString() {
        return "BudgetRequest{budgetId=" + budgetId + ", eventType='" + eventType + "', amount=" + estimatedAmount + ", status='" + status + "'}";
    }
}
