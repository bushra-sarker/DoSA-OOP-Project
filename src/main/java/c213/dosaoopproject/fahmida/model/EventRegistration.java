package c213.dosaoopproject.fahmida.model;

import java.io.Serializable;

/**
 * A student's registration for an event. Created when a student clicks
 * "Register" and reviewed by the Club Advisor under "Registered Participants".
 */
public class EventRegistration implements Serializable {

    private static final long serialVersionUID = 1L;

    private int studentId;
    private String eventName;
    private String departmentName;
    private String status;   // "Pending", "Approved", "Rejected"

    public EventRegistration(int studentId, String eventName,
                             String departmentName, String status) {
        this.studentId = studentId;
        this.eventName = eventName;
        this.departmentName = departmentName;
        this.status = status;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getStatus() {
        return status;
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }
}
