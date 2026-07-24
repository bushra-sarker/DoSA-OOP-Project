package c213.dosaoopproject.Bushra.bushraModel;

import java.time.LocalDate;

public class Event {
    private String eventName;
    private String clubName;
    private double requestedBudget;
    private LocalDate eventDate;
    private String riskLevel;
    private String status;

    public Event(String eventName, String clubName, double requestedBudget, LocalDate eventDate, String riskLevel, String status) {
        this.eventName = eventName;
        this.clubName = clubName;
        this.requestedBudget = requestedBudget;
        this.eventDate = eventDate;
        this.riskLevel = riskLevel;
        this.status = status;
    }

    // Getters and Setters
    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }

    public String getClubName() { return clubName; }
    public void setClubName(String clubName) { this.clubName = clubName; }

    public double getRequestedBudget() { return requestedBudget; }
    public void setRequestedBudget(double requestedBudget) { this.requestedBudget = requestedBudget; }

    public LocalDate getEventDate() { return eventDate; }
    public void setEventDate(LocalDate eventDate) { this.eventDate = eventDate; }

    public String getRiskLevel() { return riskLevel; }
    public void setRiskLevel(String riskLevel) { this.riskLevel = riskLevel; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}