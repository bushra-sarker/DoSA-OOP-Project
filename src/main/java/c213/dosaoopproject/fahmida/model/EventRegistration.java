package c213.dosaoopproject.fahmida.model;

import java.io.Serializable;


public class EventRegistration implements Serializable {

    private static final long serialVersionUID = 1L;

    private int studentId;
    private String eventName;
    private String departmentName;
    private String email;
    private String phone;
    private String responsibility;
    private String status;   // "Pending", "Approved", "Rejected", "Assigned"

    public EventRegistration(int studentId, String eventName,
                             String departmentName, String status) {
        this(studentId, eventName, departmentName, null, null, status);
    }

    public EventRegistration(int studentId, String eventName, String departmentName,
                             String email, String phone, String status) {
        this.studentId = studentId;
        this.eventName = eventName;
        this.departmentName = departmentName;
        this.email = email;
        this.phone = phone;
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

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }

    public String getStatus() {
        return status;
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }
}
