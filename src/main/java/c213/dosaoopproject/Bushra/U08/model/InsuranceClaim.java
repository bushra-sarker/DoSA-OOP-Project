package c213.dosaoopproject.Bushra.U08.model;

public class InsuranceClaim {

    private String claimId;
    private String studentId;
    private String studentName;
    private String claimType;
    private String submissionDate;
    private String status;
    private String documents;
    private String explanation;

    public InsuranceClaim(String claimId, String studentId,
                          String studentName, String claimType,
                          String submissionDate, String status,
                          String documents, String explanation) {
        this.claimId = claimId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.claimType = claimType;
        this.submissionDate = submissionDate;
        this.status = status;
        this.documents = documents;
        this.explanation = explanation;
    }

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClaimType() {
        return claimType;
    }

    public void setClaimType(String claimType) {
        this.claimType = claimType;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDocuments() {
        return documents;
    }

    public void setDocuments(String documents) {
        this.documents = documents;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
