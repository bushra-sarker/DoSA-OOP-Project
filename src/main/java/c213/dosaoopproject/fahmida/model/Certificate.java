package c213.dosaoopproject.fahmida.model;

import java.io.Serializable;
import java.time.LocalDate;


public class Certificate implements Serializable {

    private static final long serialVersionUID = 1L;

    private int certificateId;
    private int studentId;
    private String activityName;
    private LocalDate issueDate;
    private String filePath;

    public Certificate(int certificateId, int studentId, String activityName,
                       LocalDate issueDate) {
        this.certificateId = certificateId;
        this.studentId = studentId;
        this.activityName = activityName;
        this.issueDate = issueDate;
    }

    public int getCertificateId() {
        return certificateId;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getActivityName() {
        return activityName;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String content() {
        return "CERTIFICATE OF PARTICIPATION\n"
                + "Awarded to student ID " + studentId + "\n"
                + "Activity: " + activityName + "\n"
                + "Date: " + issueDate + "\n";
    }
}
