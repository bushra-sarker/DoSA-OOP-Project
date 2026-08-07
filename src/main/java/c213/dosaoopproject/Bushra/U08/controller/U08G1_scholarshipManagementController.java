package c213.dosaoopproject.Bushra.U08.controller;

import c213.dosaoopproject.Bushra.U08.model.ScholarshipApplication;
import c213.dosaoopproject.commonClass.util.SubViewSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class U08G1_scholarshipManagementController {

    @FXML private Label pendingApplicationsCountLabel;
    @FXML private Label rejectedApplicationsCountLabel;
    @FXML private TableColumn cgpaTableC;
    @FXML private TableColumn studentNameTableC;
    @FXML private TableColumn studentIdTableC;
    @FXML private TableColumn monthlyFamilyIncomeTableC;
    @FXML private TableView pendingApplicationsTableView;
    @FXML private Label eligibleApplicationsCountLabel;
    @FXML private Label needReviewCountLabel;
    @FXML private TableColumn applicationStatusTableC;
    @FXML private TableColumn applicationIdTableC;
    @FXML private TableColumn reviewButtonTableC;
    @FXML private TableColumn scholarshipSchemeTableC;

    private ObservableList<ScholarshipApplication> appList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Simple Dummy Data
        appList.add(new ScholarshipApplication("APP-101", "21301001", "Rahim Ahmed", "Merit Scholarship", 3.85, 45000, "Good CGPA", "Pending"));
        appList.add(new ScholarshipApplication("APP-102", "21301002", "Fatima Khan", "Financial Aid", 3.20, 20000, "Needs Aid", "Eligible"));

        // Setup columns
        applicationIdTableC.setCellValueFactory(new PropertyValueFactory<>("applicationId"));
        studentIdTableC.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        studentNameTableC.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        scholarshipSchemeTableC.setCellValueFactory(new PropertyValueFactory<>("scholarshipScheme"));
        cgpaTableC.setCellValueFactory(new PropertyValueFactory<>("cgpa"));
        monthlyFamilyIncomeTableC.setCellValueFactory(new PropertyValueFactory<>("familyIncome"));
        applicationStatusTableC.setCellValueFactory(new PropertyValueFactory<>("applicationStatus"));

        pendingApplicationsTableView.setItems(appList);

        // Simple review button setup
        reviewButtonTableC.setCellFactory(column -> new TableCell<ScholarshipApplication, Void>() {
            private final Button reviewButton = new Button("Review");

            {reviewButton.setOnAction(event -> openReviewPage());}

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : reviewButton);
            }
        });

        // Set simple counter labels
        pendingApplicationsCountLabel.setText("1");
        eligibleApplicationsCountLabel.setText("1");
        needReviewCountLabel.setText("0");
        rejectedApplicationsCountLabel.setText("0");

    }


    private void openReviewPage() {
        AnchorPane contentArea = (AnchorPane) pendingApplicationsTableView.getScene().lookup("#contentArea");
        SubViewSwitcher.loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U08/U08G1_scholarshipReviewDetail.fxml"
        );
    }


}