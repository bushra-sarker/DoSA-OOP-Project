package c213.dosaoopproject.Bushra.U07.model;

import java.io.Serializable;
import java.time.LocalDate;

public class DisciplinaryAppeal implements Serializable {
    private static final long serialVersionUID = 1L;

    private String appealId;
    private String studentId;
    private String offense;
    private String originalPenalty;
    private LocalDate incidentDate;
    private LocalDate submissionDate;
    private String incidentReport;
    private String appealStatement;
    private String status;          // "Pending", "Reviewed"
    private String decision;        // "Uphold", "Reduce", "Dismiss"
    private String newPenalty;
    private String decisionRationale;

    // Constructor
    public DisciplinaryAppeal(String appealId, String studentId, String offense,
                              String originalPenalty, LocalDate incidentDate,
                              LocalDate submissionDate, String incidentReport,
                              String appealStatement) {
        this.appealId = appealId;
        this.studentId = studentId;
        this.offense = offense;
        this.originalPenalty = originalPenalty;
        this.incidentDate = incidentDate;
        this.submissionDate = submissionDate;
        this.incidentReport = incidentReport;
        this.appealStatement = appealStatement;
        this.status = "Pending";
    }

    // Getters & Setters for all fields...
    public String getAppealId() { return appealId; }
    public String getStudentId() { return studentId; }
    public String getOffense() { return offense; }
    public String getOriginalPenalty() { return originalPenalty; }
    public LocalDate getIncidentDate() { return incidentDate; }
    public LocalDate getSubmissionDate() { return submissionDate; }
    public String getIncidentReport() { return incidentReport; }
    public String getAppealStatement() { return appealStatement; }
    public String getStatus() { return status; }
    public String getDecision() { return decision; }
    public void setDecision(String decision) { this.decision = decision; }
    public String getNewPenalty() { return newPenalty; }
    public void setNewPenalty(String newPenalty) { this.newPenalty = newPenalty; }
    public String getDecisionRationale() { return decisionRationale; }
    public void setDecisionRationale(String decisionRationale) { this.decisionRationale = decisionRationale; }
    public void setStatus(String status) { this.status = status; }
}