package c213.dosaoopproject.esha.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class VenueBooking {
    private int bookingId;
    private int executiveId;
    private String venueName;
    private LocalDate bookingDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String status; // "Pending", "Approved", "Rejected"

    public VenueBooking() {}

    public VenueBooking(int bookingId, int executiveId, String venueName,
                        LocalDate bookingDate, LocalTime startTime, LocalTime endTime, String status) {
        this.bookingId = bookingId;
        this.executiveId = executiveId;
        this.venueName = venueName;
        this.bookingDate = bookingDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public int getExecutiveId() { return executiveId; }
    public void setExecutiveId(int executiveId) { this.executiveId = executiveId; }

    public String getVenueName() { return venueName; }
    public void setVenueName(String venueName) { this.venueName = venueName; }

    public LocalDate getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; }

    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "VenueBooking{bookingId=" + bookingId + ", venue='" + venueName + "', status='" + status + "'}";
    }
}
