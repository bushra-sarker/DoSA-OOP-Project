package c213.dosaoopproject.Bushra.U08.model;

public class EmergencyRequest {
    private String requestId;
    private String studentId;
    private String studentName;
    private String department;
    private String category;
    private double requestedAmount;
    private String statement;
    private String documents;
    private String deptVerificationStatus;
    private double approvedAmount;
    private String status;

    public EmergencyRequest(String requestId, String studentId, String studentName, String department,
                            String category, double requestedAmount, String statement, String documents,
                            String deptVerificationStatus, double approvedAmount, String status) {
        this.requestId = requestId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.department = department;
        this.category = category;
        this.requestedAmount = requestedAmount;
        this.statement = statement;
        this.documents = documents;
        this.deptVerificationStatus = deptVerificationStatus;
        this.approvedAmount = approvedAmount;
        this.status = status;
    }

    public String getRequestId() { return requestId; }
    public String getStudentId() { return studentId; }
    public String getStudentName() { return studentName; }
    public String getDepartment() { return department; }
    public String getCategory() { return category; }
    public double getRequestedAmount() { return requestedAmount; }
    public String getStatement() { return statement; }
    public String getDocuments() { return documents; }
    public String getDeptVerificationStatus() { return deptVerificationStatus; }
    public void setDeptVerificationStatus(String deptVerificationStatus) { this.deptVerificationStatus = deptVerificationStatus; }
    public double getApprovedAmount() { return approvedAmount; }
    public void setApprovedAmount(double approvedAmount) { this.approvedAmount = approvedAmount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String toFileFormat() {
        return requestId + ";" + studentId + ";" + studentName + ";" + department + ";" +
                category + ";" + requestedAmount + ";" + statement + ";" + documents + ";" +
                deptVerificationStatus + ";" + approvedAmount + ";" + status;
    }
}