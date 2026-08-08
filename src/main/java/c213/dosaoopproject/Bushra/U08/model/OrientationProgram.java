package c213.dosaoopproject.Bushra.U08.model;

import java.io.Serializable;

public class OrientationProgram implements Serializable {

    private static final long serialVersionUID = 1L;

    private String programId;
    private String semester;
    private String startDate;
    private String endDate;
    private String venue;
    private String targetCohort;
    private String status;
    private String modules;

    public OrientationProgram(String programId, String semester, String startDate, String endDate,
                              String venue, String targetCohort, String status, String modules) {
        this.programId = programId;
        this.semester = semester;
        this.startDate = startDate;
        this.endDate = endDate;
        this.venue = venue;
        this.targetCohort = targetCohort;
        this.status = status;
        this.modules = modules;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getTargetCohort() {
        return targetCohort;
    }

    public void setTargetCohort(String targetCohort) {
        this.targetCohort = targetCohort;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getModules() {
        return modules;
    }

    public void setModules(String modules) {
        this.modules = modules;
    }
}
