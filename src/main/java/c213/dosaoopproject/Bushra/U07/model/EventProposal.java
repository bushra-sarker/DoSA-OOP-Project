package c213.dosaoopproject.Bushra.U07.model;

import java.io.Serializable;

public class EventProposal implements Serializable {
    private static final long serialVersionUID = 1L;

    private String eventName;
    private String clubName;
    private String eventDate;
    private String venue;
    private String budget;
    private String scope;
    private String riskLevel;
    private String status;
    private boolean proposalPdfUploaded;
    private boolean budgetSheetUploaded;
    private String decisionRationale;

    public EventProposal(String eventName, String clubName, String eventDate, String venue,
                         String budget, String scope, String riskLevel, String status,
                         boolean proposalPdfUploaded, boolean budgetSheetUploaded) {
        this.eventName = eventName;
        this.clubName = clubName;
        this.eventDate = eventDate;
        this.venue = venue;
        this.budget = budget;
        this.scope = scope;
        this.riskLevel = riskLevel;
        this.status = status;
        this.proposalPdfUploaded = proposalPdfUploaded;
        this.budgetSheetUploaded = budgetSheetUploaded;
        this.decisionRationale = "";
    }

    // Getters and Setters
    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }

    public String getClubName() { return clubName; }
    public void setClubName(String clubName) { this.clubName = clubName; }

    public String getEventDate() { return eventDate; }
    public void setEventDate(String eventDate) { this.eventDate = eventDate; }

    public String getVenue() { return venue; }
    public void setVenue(String venue) { this.venue = venue; }

    public String getBudget() { return budget; }
    public void setBudget(String budget) { this.budget = budget; }

    public String getScope() { return scope; }
    public void setScope(String scope) { this.scope = scope; }

    public String getRiskLevel() { return riskLevel; }
    public void setRiskLevel(String riskLevel) { this.riskLevel = riskLevel; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public boolean isProposalPdfUploaded() { return proposalPdfUploaded; }
    public void setProposalPdfUploaded(boolean proposalPdfUploaded) { this.proposalPdfUploaded = proposalPdfUploaded; }

    public boolean isBudgetSheetUploaded() { return budgetSheetUploaded; }
    public void setBudgetSheetUploaded(boolean budgetSheetUploaded) { this.budgetSheetUploaded = budgetSheetUploaded; }

    public String getDecisionRationale() { return decisionRationale; }
    public void setDecisionRationale(String decisionRationale) { this.decisionRationale = decisionRationale; }
}