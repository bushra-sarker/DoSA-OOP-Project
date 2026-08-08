package c213.dosaoopproject.esha.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EventRequest {
    private int requestId;
    private int executiveId;
    private String eventDescription;
    private LocalDate eventDate;
    private String status; // "Pending", "Approved", "Rejected"
    private LocalDateTime submittedAt;

    public EventRequest() {}

    public EventRequest(int requestId, int executiveId, String eventDescription,
                        LocalDate eventDate, String status, LocalDateTime submittedAt) {
        this.requestId = requestId;
        this.executiveId = executiveId;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.status = status;
        this.submittedAt = submittedAt;
    }

    public int getRequestId() { return requestId; }
    public void setRequestId(int requestId) { this.requestId = requestId; }

    public int getExecutiveId() { return executiveId; }
    public void setExecutiveId(int executiveId) { this.executiveId = executiveId; }

    public String getEventDescription() { return eventDescription; }
    public void setEventDescription(String eventDescription) { this.eventDescription = eventDescription; }

    public LocalDate getEventDate() { return eventDate; }
    public void setEventDate(LocalDate eventDate) { this.eventDate = eventDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(LocalDateTime submittedAt) { this.submittedAt = submittedAt; }

    @Override
    public String toString() {
        return "EventRequest{requestId=" + requestId + ", status='" + status + "'}";
    }
}
