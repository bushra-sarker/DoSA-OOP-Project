package c213.dosaoopproject.Bushra.U08.model;

import java.io.Serializable;
import java.time.LocalDate;

public class HousingApplication implements Serializable {
    private static final long serialVersionUID = 1L;

    private String requestId;
    private String studentId;
    private String studentName;
    private String gender;
    private LocalDate applicationDate;
    private String urgencyPriority;
    private String applicationStatus; // e.g., "Pending", "Allocated"
    private String allocatedHall;
    private String allocatedRoomNumber;
    private Double monthlyRent;
    private LocalDate allocationStartDate;
    private String housingNotes;

    public HousingApplication() {
    }

    public HousingApplication(String requestId, String studentId, String studentName, String gender,
                              LocalDate applicationDate, String urgencyPriority, String applicationStatus) {
        this.requestId = requestId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.gender = gender;
        this.applicationDate = applicationDate;
        this.urgencyPriority = urgencyPriority;
        this.applicationStatus = applicationStatus;
    }

    // Getters and Setters
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getUrgencyPriority() {
        return urgencyPriority;
    }

    public void setUrgencyPriority(String urgencyPriority) {
        this.urgencyPriority = urgencyPriority;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getAllocatedHall() {
        return allocatedHall;
    }

    public void setAllocatedHall(String allocatedHall) {
        this.allocatedHall = allocatedHall;
    }

    public String getAllocatedRoomNumber() {
        return allocatedRoomNumber;
    }

    public void setAllocatedRoomNumber(String allocatedRoomNumber) {
        this.allocatedRoomNumber = allocatedRoomNumber;
    }

    public Double getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(Double monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public LocalDate getAllocationStartDate() {
        return allocationStartDate;
    }

    public void setAllocationStartDate(LocalDate allocationStartDate) {
        this.allocationStartDate = allocationStartDate;
    }

    public String getHousingNotes() {
        return housingNotes;
    }

    public void setHousingNotes(String housingNotes) {
        this.housingNotes = housingNotes;
    }
}