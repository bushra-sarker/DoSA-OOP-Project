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

    public String getAppealId() {
        return appealId;
    }

    public void setAppealId(String appealId) {
        this.appealId = appealId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getOffense() {
        return offense;
    }

    public void setOffense(String offense) {
        this.offense = offense;
    }

    public String getOriginalPenalty() {
        return originalPenalty;
    }

    public void setOriginalPenalty(String originalPenalty) {
        this.originalPenalty = originalPenalty;
    }

    public LocalDate getIncidentDate() {
        return incidentDate;
    }

    public void setIncidentDate(LocalDate incidentDate) {
        this.incidentDate = incidentDate;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getIncidentReport() {
        return incidentReport;
    }

    public void setIncidentReport(String incidentReport) {
        this.incidentReport = incidentReport;
    }

    public String getAppealStatement() {
        return appealStatement;
    }

    public void setAppealStatement(String appealStatement) {
        this.appealStatement = appealStatement;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getNewPenalty() {
        return newPenalty;
    }

    public void setNewPenalty(String newPenalty) {
        this.newPenalty = newPenalty;
    }

    public String getDecisionRationale() {
        return decisionRationale;
    }

    public void setDecisionRationale(String decisionRationale) {
        this.decisionRationale = decisionRationale;
    }
}
