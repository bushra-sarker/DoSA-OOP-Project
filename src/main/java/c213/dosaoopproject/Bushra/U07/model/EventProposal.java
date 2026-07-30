package c213.dosaoopproject.Bushra.U07.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventProposal implements Serializable {
    private static final long serialVersionUID = 1L;

    private String eventId;
    private String eventName;
    private String clubName;
    private double requestedBudget;
    private LocalDate eventDate;
    private String riskLevel;
    private String status;
    private boolean interUniversityScope;
    private boolean proposalPdfAttached;
    private boolean budgetSheetAttached;
    private String feedbackComments;

    // Direct list reference using the standalone BudgetItem class
    private List<BudgetItem> budgetItems = new ArrayList<>();

    public EventProposal() {}

    public EventProposal(String eventId, String eventName, String clubName, double requestedBudget, LocalDate eventDate, String riskLevel, String status, boolean interUniversityScope, boolean proposalPdfAttached, boolean budgetSheetAttached) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.clubName = clubName;
        this.requestedBudget = requestedBudget;
        this.eventDate = eventDate;
        this.riskLevel = riskLevel;
        this.status = status;
        this.interUniversityScope = interUniversityScope;
        this.proposalPdfAttached = proposalPdfAttached;
        this.budgetSheetAttached = budgetSheetAttached;
    }

    // Getters & Setters
    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }

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

    public boolean isInterUniversityScope() { return interUniversityScope; }
    public void setInterUniversityScope(boolean interUniversityScope) { this.interUniversityScope = interUniversityScope; }

    public boolean isProposalPdfAttached() { return proposalPdfAttached; }
    public void setProposalPdfAttached(boolean proposalPdfAttached) { this.proposalPdfAttached = proposalPdfAttached; }

    public boolean isBudgetSheetAttached() { return budgetSheetAttached; }
    public void setBudgetSheetAttached(boolean budgetSheetAttached) { this.budgetSheetAttached = budgetSheetAttached; }

    public String getFeedbackComments() { return feedbackComments; }
    public void setFeedbackComments(String feedbackComments) { this.feedbackComments = feedbackComments; }

    public List<BudgetItem> getBudgetItems() { return budgetItems; }
    public void setBudgetItems(List<BudgetItem> budgetItems) { this.budgetItems = budgetItems; }

    public void addBudgetItem(String description, double amount) {
        this.budgetItems.add(new BudgetItem(description, amount));
    }
}