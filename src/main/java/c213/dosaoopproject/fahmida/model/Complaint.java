package c213.dosaoopproject.fahmida.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A complaint or report submitted by a student.
 */
public class Complaint implements Serializable {

    private static final long serialVersionUID = 1L;

    private int complaintId;
    private int studentId;
    private String category;
    private String details;
    private LocalDate dateSubmitted;
    private String status;   // "Open", "Resolved"

    public Complaint(int complaintId, int studentId, String category,
                     String details, LocalDate dateSubmitted) {
        this.complaintId = complaintId;
        this.studentId = studentId;
        this.category = category;
        this.details = details;
        this.dateSubmitted = dateSubmitted;
        this.status = "Open";
    }

    public int getComplaintId() {
        return complaintId;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getCategory() {
        return category;
    }

    public String getDetails() {
        return details;
    }

    public LocalDate getDateSubmitted() {
        return dateSubmitted;
    }

    public String getStatus() {
        return status;
    }

    public void updateStatus(String status) {
        this.status = status;
    }
}
