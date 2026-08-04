package c213.dosaoopproject.Bushra.U07.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Event implements Serializable {
    // Recommended for version compatibility during serialization
    private static final long serialVersionUID = 1L;

    private String eventId;
    private String eventName;
    private String clubName;
    private String eventDate;
    private double budget;
    private String riskLevel;
    private String status;
    private String venue;
    private List<BudgetItem> budgetItems; // List of associated budget items

    // Default Constructor
    public Event() {
        this.budgetItems = new ArrayList<>();
    }

    // Constructor with parameters
    public Event(String eventId, String eventName, String clubName, String eventDate, double budget, String riskLevel, String status, String venue) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.clubName = clubName;
        this.eventDate = eventDate;
        this.budget = budget;
        this.riskLevel = riskLevel;
        this.status = status;
        this.venue = venue;
        this.budgetItems = new ArrayList<>();
    }

    // Getters and Setters
    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }

    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }

    public String getClubName() { return clubName; }
    public void setClubName(String clubName) { this.clubName = clubName; }

    public String getEventDate() { return eventDate; }
    public void setEventDate(String eventDate) { this.eventDate = eventDate; }

    public double getBudget() { return budget; }
    public void setBudget(double budget) { this.budget = budget; }

    public String getRiskLevel() { return riskLevel; }
    public void setRiskLevel(String riskLevel) { this.riskLevel = riskLevel; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getVenue() { return venue; }
    public void setVenue(String venue) { this.venue = venue; }

    public List<BudgetItem> getBudgetItems() { return budgetItems; }
    public void setBudgetItems(List<BudgetItem> budgetItems) { this.budgetItems = budgetItems; }
}