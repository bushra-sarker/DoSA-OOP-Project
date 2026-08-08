package c213.dosaoopproject.esha.model;

import java.time.LocalDate;

public class ServiceOpportunity {
    private int opportunityId;
    private int coordinatorId;
    private String title;
    private String description;
    private LocalDate date;
    private String location;
    private int availableSlots;
    private String status; // "Draft", "Published"

    public ServiceOpportunity() {}

    public ServiceOpportunity(int opportunityId, int coordinatorId, String title,
                              String description, LocalDate date, String location,
                              int availableSlots, String status) {
        this.opportunityId = opportunityId;
        this.coordinatorId = coordinatorId;
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
        this.availableSlots = availableSlots;
        this.status = status;
    }

    public int getOpportunityId() { return opportunityId; }
    public void setOpportunityId(int opportunityId) { this.opportunityId = opportunityId; }

    public int getCoordinatorId() { return coordinatorId; }
    public void setCoordinatorId(int coordinatorId) { this.coordinatorId = coordinatorId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public int getAvailableSlots() { return availableSlots; }
    public void setAvailableSlots(int availableSlots) { this.availableSlots = availableSlots; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "ServiceOpportunity{opportunityId=" + opportunityId + ", title='" + title + "', status='" + status + "'}";
    }
}
