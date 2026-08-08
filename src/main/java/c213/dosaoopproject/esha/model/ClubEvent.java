package c213.dosaoopproject.esha.model;

import java.time.LocalDate;

public class ClubEvent {
    private int eventId;
    private String eventName;
    private String description;
    private LocalDate eventDate;
    private String venue;
    private String status; // "Planned", "Confirmed", "Cancelled", "Completed"

    public ClubEvent() {}

    public ClubEvent(int eventId, String eventName, String description,
                     LocalDate eventDate, String venue, String status) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.description = description;
        this.eventDate = eventDate;
        this.venue = venue;
        this.status = status;
    }

    public int getEventId() { return eventId; }
    public void setEventId(int eventId) { this.eventId = eventId; }

    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getEventDate() { return eventDate; }
    public void setEventDate(LocalDate eventDate) { this.eventDate = eventDate; }

    public String getVenue() { return venue; }
    public void setVenue(String venue) { this.venue = venue; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "ClubEvent{eventId=" + eventId + ", eventName='" + eventName + "', status='" + status + "'}";
    }
}
