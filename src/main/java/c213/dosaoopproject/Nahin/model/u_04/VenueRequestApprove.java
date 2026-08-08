package c213.dosaoopproject.Nahin.model.u_04;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VenueRequestApprove {
    private final String requestId;
    private final String eventName;
    private final String clubName;
    private final String requestedVenue;
    private final String requestedBy;
    private final LocalDate requestDate;
    private final String requestTime;
    private String status;

    public VenueRequestApprove(String requestId, String eventName, String clubName, String requestedVenue, String requestedBy, LocalDate requestDate, String requestTime) {
        this.requestId = requestId;
        this.eventName = eventName;
        this.clubName = clubName;
        this.requestedVenue = requestedVenue;
        this.requestedBy = requestedBy;
        this.requestDate = requestDate;
        this.requestTime = requestTime;
        this.status = "Pending";
        }

    public String getRequestId() {
        return requestId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getClubName() {
        return clubName;
    }

    public String getRequestedVenue() {
        return requestedVenue;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public String getFormattedDate() {
        return requestDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }


    public void approve() {
        this.status = "Approved";
    }
    public void reject() {
        this.status = "Rejected";
    }


    @Override
    public String toString() {
        return "VenueRequestApprove{" +
                "requestId='" + requestId + '\'' +
                ", eventName='" + eventName + '\'' +
                ", clubName='" + clubName + '\'' +
                ", requestedVenue='" + requestedVenue + '\'' +
                ", requestedBy='" + requestedBy + '\'' +
                ", requestDate=" + requestDate +
                ", requestTime='" + requestTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
