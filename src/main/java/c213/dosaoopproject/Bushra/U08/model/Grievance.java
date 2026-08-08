package c213.dosaoopproject.Bushra.U08.model;

import java.io.Serializable;

public class Grievance implements Serializable {
    private static final long serialVersionUID = 1L;

    private String grievanceId;
    private String studentId;
    private String studentName;
    private String category;
    private String submissionDate;
    private String urgency;
    private String assignedUnit;
    private String complaintBody;
    private String suggestedUnit;
    private String actionTaken;
    private String dismissalReason;
    private String resolutionSummary;
    private String status;
    private String evidenceFiles;


    public Grievance(String grievanceId,
                     String studentId,
                     String studentName,
                     String category,
                     String submissionDate,
                     String urgency,
                     String assignedUnit,
                     String complaintBody,
                     String suggestedUnit,
                     String status,
                     String evidenceFiles) {

        this.grievanceId = grievanceId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.category = category;
        this.submissionDate = submissionDate;
        this.urgency = urgency;
        this.assignedUnit = assignedUnit;
        this.complaintBody = complaintBody;
        this.suggestedUnit = suggestedUnit;
        this.status = status;
        this.evidenceFiles = evidenceFiles;
    }


    public String getGrievanceId() {
        return grievanceId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCategory() {
        return category;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public String getUrgency() {
        return urgency;
    }

    public String getAssignedUnit() {
        return assignedUnit;
    }

    public String getComplaintBody() {
        return complaintBody;
    }

    public String getSuggestedUnit() {
        return suggestedUnit;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public String getDismissalReason() {
        return dismissalReason;
    }

    public String getResolutionSummary() {
        return resolutionSummary;
    }

    public String getStatus() {
        return status;
    }

    public String getEvidenceFiles() {
        return evidenceFiles;
    }


    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    public void setDismissalReason(String dismissalReason) {
        this.dismissalReason = dismissalReason;
    }

    public void setResolutionSummary(String resolutionSummary) {
        this.resolutionSummary = resolutionSummary;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEvidenceFiles(String evidenceFiles) {
        this.evidenceFiles = evidenceFiles;
    }


}