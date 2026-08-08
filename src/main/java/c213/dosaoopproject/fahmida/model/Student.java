package c213.dosaoopproject.fahmida.model;

import commonClass.User;


public class Student extends User {

    private static final long serialVersionUID = 1L;

    private String email;
    private String department;

    public Student(String name, String passwordHash, int studentId,
                   String loginId, String email, String department) {
        super(name, passwordHash, studentId);
        setLoginId(loginId);
        this.email = email;
        this.department = department;
    }

    public int getStudentId() {
        return getUserId();
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String getRole() {
        return "Student";
    }

    @Override
    public String getDashboardFxml() {
        return "/c213/dosaoopproject/fahmida/U1_Dashboard.fxml";
    }

    @Override
    public void loadDashboard() {
        // Navigation is handled by SceneManager using getDashboardFxml().
    }
}
