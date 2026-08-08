package c213.dosaoopproject.Bushra.U07.model;

import java.io.Serializable;

public class ExchangeNomination implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nominationId;
    private String studentName;
    private String hostUniversity;
    private double cgpa;
    private String status;
    private String comments;

    public ExchangeNomination(String nominationId,
                              String studentName,
                              String hostUniversity,
                              double cgpa,
                              String status,
                              String comments) {

        this.nominationId = nominationId;
        this.studentName = studentName;
        this.hostUniversity = hostUniversity;
        this.cgpa = cgpa;
        this.status = status;
        this.comments = comments;
    }

    public String getNominationId() { return nominationId; }
    public void setNominationId(String nominationId) { this.nominationId = nominationId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getHostUniversity() { return hostUniversity; }
    public void setHostUniversity(String hostUniversity) { this.hostUniversity = hostUniversity; }

    public double getCgpa() { return cgpa; }
    public void setCgpa(double cgpa) { this.cgpa = cgpa; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }
}