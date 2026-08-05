package c213.dosaoopproject.fahmida.model;

import java.io.Serializable;

/**
 * A volunteer assigned by a Club Advisor to help run an event.
 */
public class VolunteerAssignment implements Serializable {

    private static final long serialVersionUID = 1L;

    private String assignmentId;
    private int volunteerId;
    private int eventId;
    private String responsibility;
    private String status;   // "Assigned", "Confirmed", "Cancelled"

    public VolunteerAssignment(String assignmentId, int volunteerId, int eventId,
                               String responsibility, String status) {
        this.assignmentId = assignmentId;
        this.volunteerId = volunteerId;
        this.eventId = eventId;
        this.responsibility = responsibility;
        this.status = status;
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public int getVolunteerId() {
        return volunteerId;
    }

    public int getEventId() {
        return eventId;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public String getStatus() {
        return status;
    }

    /** A valid assignment must name a responsibility. */
    public boolean validateAssignment() {
        return responsibility != null && !responsibility.isBlank();
    }

    public void confirmAssignment() {
        this.status = "Confirmed";
    }

    public void updateStatus(String status) {
        this.status = status;
    }
}
