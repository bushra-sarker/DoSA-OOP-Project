package c213.dosaoopproject.fahmida.model;

import java.io.Serializable;
import java.time.LocalDate;


public class ApprovalLetter implements Serializable {

    private static final long serialVersionUID = 1L;

    private int letterId;
    private int studentId;
    private String studentName;
    private LocalDate issueDate;
    private String filePath;

    public ApprovalLetter(int letterId, int studentId, String studentName,
                          LocalDate issueDate) {
        this.letterId = letterId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.issueDate = issueDate;
    }

    public int getLetterId() {
        return letterId;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
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

    /** The letter's text content (written to a file when downloaded). */
    public String content() {
        return "DoSA APPROVAL LETTER\n"
                + "--------------------\n"
                + "Letter ID : " + letterId + "\n"
                + "Student   : " + studentName + " (ID " + studentId + ")\n"
                + "Issued    : " + issueDate + "\n\n"
                + "This letter confirms that the above student's request has been "
                + "approved by the Division of Student Affairs.\n";
    }
}
