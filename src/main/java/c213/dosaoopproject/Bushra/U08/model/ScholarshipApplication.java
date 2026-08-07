package c213.dosaoopproject.Bushra.U08.model;

import java.io.Serializable;

public class ScholarshipApplication implements Serializable {
    private static final long serialVersionUID = 1L;

    private String applicationId;
    private String studentId;
    private String studentName;
    private String scholarshipScheme;
    private double cgpa;
    private double familyIncome;
    private String transcriptSummary;
    private String applicationStatus;
    private String reviewNotes;

    public ScholarshipApplication() {
    }

    public ScholarshipApplication(String applicationId, String studentId, String studentName,
                                  String scholarshipScheme, double cgpa, double familyIncome,
                                  String transcriptSummary, String applicationStatus) {
        this.applicationId = applicationId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.scholarshipScheme = scholarshipScheme;
        this.cgpa = cgpa;
        this.familyIncome = familyIncome;
        this.transcriptSummary = transcriptSummary;
        this.applicationStatus = applicationStatus;
        this.reviewNotes = "";
    }

    // Getters and Setters
    public String getApplicationId() { return applicationId; }
    public void setApplicationId(String applicationId) { this.applicationId = applicationId; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getScholarshipScheme() { return scholarshipScheme; }
    public void setScholarshipScheme(String scholarshipScheme) { this.scholarshipScheme = scholarshipScheme; }

    public double getCgpa() { return cgpa; }
    public void setCgpa(double cgpa) { this.cgpa = cgpa; }

    public double getFamilyIncome() { return familyIncome; }
    public void setFamilyIncome(double familyIncome) { this.familyIncome = familyIncome; }

    public String getTranscriptSummary() { return transcriptSummary; }
    public void setTranscriptSummary(String transcriptSummary) { this.transcriptSummary = transcriptSummary; }

    public String getApplicationStatus() { return applicationStatus; }
    public void setApplicationStatus(String applicationStatus) { this.applicationStatus = applicationStatus; }

    public String getReviewNotes() { return reviewNotes; }
    public void setReviewNotes(String reviewNotes) { this.reviewNotes = reviewNotes; }
}