package c213.dosaoopproject.Bushra.U08.model;

import java.io.Serializable;

public class InsuranceClaim implements Serializable {

    private String claimId;
    private String studentId;
    private String studentName;
    private String hospitalName;
    private String claimedAmount;
    private String submissionDate;
    private String status;
    private String expenses;
    private boolean dischargeSummary;
    private boolean hospitalBills;
    private String action;
    private String actionNote;

    public InsuranceClaim(String claimId,
                          String studentId,
                          String studentName,
                          String hospitalName,
                          String claimedAmount,
                          String submissionDate,
                          String status,
                          String expenses,
                          boolean dischargeSummary,
                          boolean hospitalBills) {

        this.claimId = claimId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.hospitalName = hospitalName;
        this.claimedAmount = claimedAmount;
        this.submissionDate = submissionDate;
        this.status = status;
        this.expenses = expenses;
        this.dischargeSummary = dischargeSummary;
        this.hospitalBills = hospitalBills;
    }

    public String getClaimId() {
        return claimId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public String getClaimedAmount() {
        return claimedAmount;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public String getStatus() {
        return status;
    }

    public String getExpenses() {
        return expenses;
    }

    public boolean isDischargeSummary() {
        return dischargeSummary;
    }

    public boolean isHospitalBills() {
        return hospitalBills;
    }

    public String getAction() {
        return action;
    }

    public String getActionNote() {
        return actionNote;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setActionNote(String actionNote) {
        this.actionNote = actionNote;
    }

    @Override
    public String toString() {
        return "InsuranceClaim{" +
                "claimId='" + claimId + '\'' +
                ", studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", hospitalName='" + hospitalName + '\'' +
                ", claimedAmount='" + claimedAmount + '\'' +
                ", submissionDate='" + submissionDate + '\'' +
                ", status='" + status + '\'' +
                ", expenses='" + expenses + '\'' +
                ", dischargeSummary=" + dischargeSummary +
                ", hospitalBills=" + hospitalBills +
                ", action='" + action + '\'' +
                ", actionNote='" + actionNote + '\'' +
                '}';
    }
    public String toTxt() {
        return String.join(",",
                claimId,
                studentId,
                studentName,
                hospitalName,
                claimedAmount,
                submissionDate,
                status,
                expenses != null ? expenses : "None",
                String.valueOf(dischargeSummary),
                String.valueOf(hospitalBills)
        );
    }
}