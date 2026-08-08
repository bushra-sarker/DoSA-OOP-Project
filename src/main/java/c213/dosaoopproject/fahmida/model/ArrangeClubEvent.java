package c213.dosaoopproject.fahmida.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A club event created by a Club Advisor. Students see these under
 * "Register for Event" and "View Event Schedule".
 */
public class ArrangeClubEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    private int eventId;
    private String eventName;
    private String description;
    private LocalDate eventDate;
    private String venue;
    private String status;   // "Upcoming", "Cancelled", "Completed"

    public ArrangeClubEvent(int eventId, String eventName, String description,
                            LocalDate eventDate, String venue, String status) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.description = description;
        this.eventDate = eventDate;
        this.venue = venue;
        this.status = status;
    }

    public int getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public String getVenue() {
        return venue;
    }

    public String getStatus() {
        return status;
    }

    public boolean cancelEvent() {
        this.status = "Cancelled";
        return true;
    }

    public void updateEvent(String description, LocalDate eventDate, String venue) {
        this.description = description;
        this.eventDate = eventDate;
        this.venue = venue;
    }

    public String viewDetails() {
        return eventName + " @ " + venue + " on " + eventDate + " (" + status + ")";
    }
}
