package c213.dosaoopproject.esha.model;

public class EventCompletionReport {
    private int reportId;
    private int executiveId;
    private String eventName;
    private int attendanceCount;
    private String outcomeSummary;
    private String status; // "Submitted", "Reviewed"

    public EventCompletionReport() {}

    public EventCompletionReport(int reportId, int executiveId, String eventName,
                                 int attendanceCount, String outcomeSummary, String status) {
        this.reportId = reportId;
        this.executiveId = executiveId;
        this.eventName = eventName;
        this.attendanceCount = attendanceCount;
        this.outcomeSummary = outcomeSummary;
        this.status = status;
    }

    public int getReportId() { return reportId; }
    public void setReportId(int reportId) { this.reportId = reportId; }

    public int getExecutiveId() { return executiveId; }
    public void setExecutiveId(int executiveId) { this.executiveId = executiveId; }

    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }

    public int getAttendanceCount() { return attendanceCount; }
    public void setAttendanceCount(int attendanceCount) { this.attendanceCount = attendanceCount; }

    public String getOutcomeSummary() { return outcomeSummary; }
    public void setOutcomeSummary(String outcomeSummary) { this.outcomeSummary = outcomeSummary; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "EventCompletionReport{reportId=" + reportId + ", eventName='" + eventName + "', status='" + status + "'}";
    }
}
