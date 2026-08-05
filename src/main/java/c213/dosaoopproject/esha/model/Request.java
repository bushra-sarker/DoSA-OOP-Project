package c213.dosaoopproject.esha.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Request {
    private static int counter = 0;

    private final int requestId;
    private final String type;        // "Event Request", "Venue Booking", "Sponsorship Request", "Resource Request"
    private final String details;
    private final LocalDateTime submittedAt;
    private String status;            // "Pending", "Approved", "Rejected"
    private String coordinatorNote;

    public Request(String type, String details) {
        this.requestId = ++counter;
        this.type = type;
        this.details = details;
        this.submittedAt = LocalDateTime.now();
        this.status = "Pending";
        this.coordinatorNote = "";
    }

    public int getRequestId() { return requestId; }
    public String getType() { return type; }
    public String getDetails() { return details; }
    public LocalDateTime getSubmittedAt() { return submittedAt; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getCoordinatorNote() { return coordinatorNote; }
    public void setCoordinatorNote(String coordinatorNote) { this.coordinatorNote = coordinatorNote; }

    @Override
    public String toString() {
        return "[#" + requestId + "] " + type + " — " + status;
    }
}
