package c213.dosaoopproject.esha.model;

public class VolunteerTaskCompletion {
    private int assignmentId;
    private int volunteerId;
    private String eventName;
    private String task;
    private String completionStatus; // "Not Reported", "Completed", "Partially Completed", "No-show"
    private int hoursWorked;
    private String remarks;

    public VolunteerTaskCompletion() {}

    public VolunteerTaskCompletion(int assignmentId, int volunteerId, String eventName,
                                   String task, String completionStatus,
                                   int hoursWorked, String remarks) {
        this.assignmentId = assignmentId;
        this.volunteerId = volunteerId;
        this.eventName = eventName;
        this.task = task;
        this.completionStatus = completionStatus;
        this.hoursWorked = hoursWorked;
        this.remarks = remarks;
    }

    public int getAssignmentId() { return assignmentId; }
    public void setAssignmentId(int assignmentId) { this.assignmentId = assignmentId; }

    public int getVolunteerId() { return volunteerId; }
    public void setVolunteerId(int volunteerId) { this.volunteerId = volunteerId; }

    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }

    public String getTask() { return task; }
    public void setTask(String task) { this.task = task; }

    public String getCompletionStatus() { return completionStatus; }
    public void setCompletionStatus(String completionStatus) { this.completionStatus = completionStatus; }

    public int getHoursWorked() { return hoursWorked; }
    public void setHoursWorked(int hoursWorked) { this.hoursWorked = hoursWorked; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    @Override
    public String toString() {
        return "VolunteerTaskCompletion{assignmentId=" + assignmentId + ", volunteerId=" + volunteerId
                + ", completionStatus='" + completionStatus + "'}";
    }
}
