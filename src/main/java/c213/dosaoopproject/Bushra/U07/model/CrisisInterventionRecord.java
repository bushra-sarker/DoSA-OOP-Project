package c213.dosaoopproject.Bushra.U07.model;

import java.io.Serializable;

public class CrisisInterventionRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    private String caseId;
    private String studentId;
    private String urgencyLevel;
    private String responseTeam;
    private String interventionLog;
    private String status;

    public CrisisInterventionRecord(String caseId, String studentId, String urgencyLevel, String responseTeam, String interventionLog, String status) {
        this.caseId = caseId;
        this.studentId = studentId;
        this.urgencyLevel = urgencyLevel;
        this.responseTeam = responseTeam;
        this.interventionLog = interventionLog;
        this.status = status;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getUrgencyLevel() {
        return urgencyLevel;
    }

    public void setUrgencyLevel(String urgencyLevel) {
        this.urgencyLevel = urgencyLevel;
    }

    public String getResponseTeam() {
        return responseTeam;
    }

    public void setResponseTeam(String responseTeam) {
        this.responseTeam = responseTeam;
    }

    public String getInterventionLog() {
        return interventionLog;
    }

    public void setInterventionLog(String interventionLog) {
        this.interventionLog = interventionLog;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}