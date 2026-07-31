package c213.dosaoopproject.Bushra.U07.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class MajorEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    private String eventName;
    private String clubName;
    private double requestedBudget;
    private LocalDate eventDate;
    private String venue;
    private String scope;
    private String riskLevel;
    private String status;
    private boolean proposalUploaded;
    private boolean budgetSheetUploaded;
    private ArrayList<BudgetItem> budgetItems;
    private String revisionComments;
    private String rejectionReason;

    public MajorEvent(String eventName, String clubName, double requestedBudget, LocalDate eventDate,
                      String venue, String scope, String riskLevel, String status,
                      boolean proposalUploaded, boolean budgetSheetUploaded, ArrayList<BudgetItem> budgetItems) {
        this.eventName = eventName;
        this.clubName = clubName;
        this.requestedBudget = requestedBudget;
        this.eventDate = eventDate;
        this.venue = venue;
        this.scope = scope;
        this.riskLevel = riskLevel;
        this.status = status;
        this.proposalUploaded = proposalUploaded;
        this.budgetSheetUploaded = budgetSheetUploaded;
        this.budgetItems = budgetItems != null ? budgetItems : new ArrayList<>();
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

    public String getVenue() { return venue; }
    public void setVenue(String venue) { this.venue = venue; }

    public String getScope() { return scope; }
    public void setScope(String scope) { this.scope = scope; }

    public String getRiskLevel() { return riskLevel; }
    public void setRiskLevel(String riskLevel) { this.riskLevel = riskLevel; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public boolean isProposalUploaded() { return proposalUploaded; }
    public void setProposalUploaded(boolean proposalUploaded) { this.proposalUploaded = proposalUploaded; }

    public boolean isBudgetSheetUploaded() { return budgetSheetUploaded; }
    public void setBudgetSheetUploaded(boolean budgetSheetUploaded) { this.budgetSheetUploaded = budgetSheetUploaded; }

    public ArrayList<BudgetItem> getBudgetItems() { return budgetItems; }
    public void setBudgetItems(ArrayList<BudgetItem> budgetItems) { this.budgetItems = budgetItems; }

    public String getRevisionComments() { return revisionComments; }
    public void setRevisionComments(String revisionComments) { this.revisionComments = revisionComments; }

    public String getRejectionReason() { return rejectionReason; }
    public void setRejectionReason(String rejectionReason) { this.rejectionReason = rejectionReason; }
}