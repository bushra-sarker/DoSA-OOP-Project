package c213.dosaoopproject.Bushra.Model.U07;

public class EventModel {
    private String eventName;
    private String clubName;
    private double requestedBudget;
    private String eventDate;
    private String riskLevel;
    private boolean hasProposalPdf;
    private boolean hasBudgetSheet;

    public EventModel(String eventName, String clubName, double requestedBudget, String eventDate, String riskLevel, boolean hasProposalPdf, boolean hasBudgetSheet) {
        this.eventName = eventName;
        this.clubName = clubName;
        this.requestedBudget = requestedBudget;
        this.eventDate = eventDate;
        this.riskLevel = riskLevel;
        this.hasProposalPdf = hasProposalPdf;
        this.hasBudgetSheet = hasBudgetSheet;
    }

    public String getEventName() { return eventName; }
    public String getClubName() { return clubName; }
    public double getRequestedBudget() { return requestedBudget; }
    public String getEventDate() { return eventDate; }
    public String getRiskLevel() { return riskLevel; }
    public boolean isHasProposalPdf() { return hasProposalPdf; }
    public boolean isHasBudgetSheet() { return hasBudgetSheet; }
}