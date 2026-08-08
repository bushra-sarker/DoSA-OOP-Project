package c213.dosaoopproject.Bushra.U08.model;

import java.io.Serializable;

public class HostelRoom implements Serializable {
    private static final long serialVersionUID = 1L;

    private String hallName;
    private String roomNumber;
    private String genderAllocation;
    private int capacity;
    private double monthlyRent;
    private String roomStatus;

    public HostelRoom() {
    }

    public HostelRoom(String hallName, String roomNumber, String genderAllocation, int capacity, double monthlyRent, String roomStatus) {
        this.hallName = hallName;
        this.roomNumber = roomNumber;
        this.genderAllocation = genderAllocation;
        this.capacity = capacity;
        this.monthlyRent = monthlyRent;
        this.roomStatus = roomStatus;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getGenderAllocation() {
        return genderAllocation;
    }

    public void setGenderAllocation(String genderAllocation) {
        this.genderAllocation = genderAllocation;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(double monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }
}