package c213.dosaoopproject.fahmida.model;

import java.io.Serializable;


public class EventCompletionReport implements Serializable {

    private static final long serialVersionUID = 1L;

    private int reportId;
    private int eventId;
    private String eventName;
    private int actualAttendance;
    private String outcomeSummary;
    private String status;   // "Draft", "Submitted"

    public EventCompletionReport(int reportId, int eventId, String eventName,
                                 int actualAttendance, String outcomeSummary,
                                 String status) {
        this.reportId = reportId;
        this.eventId = eventId;
        this.eventName = eventName;
        this.actualAttendance = actualAttendance;
        this.outcomeSummary = outcomeSummary;
        this.status = status;
    }

    public int getReportId() {
        return reportId;
    }

    public int getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public int getActualAttendance() {
        return actualAttendance;
    }

    public String getOutcomeSummary() {
        return outcomeSummary;
    }

    public String getStatus() {
        return status;
    }

    public void updateStatus(String status) {
        this.status = status;
    }
}
