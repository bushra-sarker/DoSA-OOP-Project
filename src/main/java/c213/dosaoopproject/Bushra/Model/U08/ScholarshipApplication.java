package c213.dosaoopproject.Bushra.Model.U08;

import java.time.LocalDate;

public class ScholarshipApplication {
    private String studentName;
    private int studentId;
    private String scholarshipType;
    private double cgpa;
    private LocalDate applicationDate;
    private String department;
    private String semester;
    private String personalStatement;

    public ScholarshipApplication(String studentName, int studentId, String scholarshipType, double cgpa, LocalDate applicationDate, String department, String semester, String personalStatement) {
        this.studentName = studentName;
        this.studentId = studentId;
        this.scholarshipType = scholarshipType;
        this.cgpa = cgpa;
        this.applicationDate = applicationDate;
        this.department = department;
        this.semester = semester;
        this.personalStatement = personalStatement;
    }

    public String getStudentName() { return studentName; }
    public int getStudentId() { return studentId; }
    public String getScholarshipType() { return scholarshipType; }
    public double getCgpa() { return cgpa; }
    public LocalDate getApplicationDate() { return applicationDate; }
    public String getDepartment() { return department; }
    public String getSemester() { return semester; }
    public String getPersonalStatement() { return personalStatement; }
}