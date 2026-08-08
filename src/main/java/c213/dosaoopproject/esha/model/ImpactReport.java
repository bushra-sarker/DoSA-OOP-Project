package c213.dosaoopproject.esha.model;

public class ImpactReport {
    private int reportId;
    private int coordinatorId;
    private String semester; // "Spring", "Summer", "Fall"
    private String year;
    private int totalEvents;
    private int totalVolunteers;
    private double totalImpactHours;
    private String executiveSummary;
    private String status; // "Draft", "Published"

    public ImpactReport() {}

    public ImpactReport(int reportId, int coordinatorId, String semester, String year,
                        int totalEvents, int totalVolunteers, double totalImpactHours,
                        String executiveSummary, String status) {
        this.reportId = reportId;
        this.coordinatorId = coordinatorId;
        this.semester = semester;
        this.year = year;
        this.totalEvents = totalEvents;
        this.totalVolunteers = totalVolunteers;
        this.totalImpactHours = totalImpactHours;
        this.executiveSummary = executiveSummary;
        this.status = status;
    }

    public int getReportId() { return reportId; }
    public void setReportId(int reportId) { this.reportId = reportId; }

    public int getCoordinatorId() { return coordinatorId; }
    public void setCoordinatorId(int coordinatorId) { this.coordinatorId = coordinatorId; }

    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }

    public int getTotalEvents() { return totalEvents; }
    public void setTotalEvents(int totalEvents) { this.totalEvents = totalEvents; }

    public int getTotalVolunteers() { return totalVolunteers; }
    public void setTotalVolunteers(int totalVolunteers) { this.totalVolunteers = totalVolunteers; }

    public double getTotalImpactHours() { return totalImpactHours; }
    public void setTotalImpactHours(double totalImpactHours) { this.totalImpactHours = totalImpactHours; }

    public String getExecutiveSummary() { return executiveSummary; }
    public void setExecutiveSummary(String executiveSummary) { this.executiveSummary = executiveSummary; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "ImpactReport{reportId=" + reportId + ", semester='" + semester + " " + year + "', status='" + status + "'}";
    }
}
