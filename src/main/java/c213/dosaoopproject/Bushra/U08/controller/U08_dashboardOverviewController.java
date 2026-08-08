package c213.dosaoopproject.Bushra.U08.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class U08_dashboardOverviewController {

    @FXML private Label scholarshipReviewCountLabel;
    @FXML private Label financialAssistanceCountLabel;
    @FXML private Label studentGrievanceCountLabel;
    @FXML private Label healthInsuranceCountLabel;
    @FXML private Label studentOrientationCountLabel;
    @FXML private Label accommodationCountLabel;
    @FXML private Label studentFacilityAccessCountLabel;
    @FXML private Label studentFeedbackCountLabel;

    @FXML
    public void initialize() {
        // Load initial metric counts
        loadDashboardMetrics();
    }

    public void loadDashboardMetrics() {
        // Example dynamic values / hooks to your data model:
        setScholarshipReviewCount(3);
        setFinancialAssistanceCount(5);
        setStudentGrievanceCount(2);
        setHealthInsuranceCount(1);
        setStudentOrientationCount(4);
        setAccommodationCount(6);
        setStudentFacilityAccessCount(8);
        setStudentFeedbackCount(2);
    }

    // --- Setter methods for dynamic runtime updates ---

    public void setScholarshipReviewCount(int count) {
        scholarshipReviewCountLabel.setText(String.valueOf(count));
    }

    public void setFinancialAssistanceCount(int count) {
        financialAssistanceCountLabel.setText(String.valueOf(count));
    }

    public void setStudentGrievanceCount(int count) {
        studentGrievanceCountLabel.setText(String.valueOf(count));
    }

    public void setHealthInsuranceCount(int count) {
        healthInsuranceCountLabel.setText(String.valueOf(count));
    }

    public void setStudentOrientationCount(int count) {
        studentOrientationCountLabel.setText(String.valueOf(count));
    }

    public void setAccommodationCount(int count) {
        accommodationCountLabel.setText(String.valueOf(count));
    }

    public void setStudentFacilityAccessCount(int count) {
        studentFacilityAccessCountLabel.setText(String.valueOf(count));
    }

    public void setStudentFeedbackCount(int count) {
        studentFeedbackCountLabel.setText(String.valueOf(count));
    }
}