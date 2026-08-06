package c213.dosaoopproject.Nahin.nonUser;

import java.io.Serializable;
import java.time.LocalDate;

public class AvailableEvents_false implements Serializable {
    private final String clubName;
    private final String eventName;
    private String venue;
    private LocalDate eventDate;
    private String time;

    public AvailableEvents_false(String clubName, String eventName, String venue, LocalDate eventDate, String time) {
        this.clubName = clubName;
        this.eventName = eventName;
        this.venue = venue;
        this.eventDate = eventDate;
        this.time = time;
    }

    public String getClubName() {
        return clubName;
    }

    public String getEventName() {
        return eventName;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "AvailableEvents_false{" +
                "clubName='" + clubName + '\'' +
                ", eventName='" + eventName + '\'' +
                ", venue='" + venue + '\'' +
                ", eventDate=" + eventDate +
                ", time='" + time + '\'' +
                '}';
    }
}
