package c213.dosaoopproject.Bushra.U08.controller;

import c213.dosaoopproject.Bushra.U08.model.ScholarshipApplication;
import c213.dosaoopproject.commonClass.data.BinaryFileUtil;
import c213.dosaoopproject.commonClass.util.SubViewSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class U08G1_scholarshipManagementController {

    @FXML private Label pendingApplicationsCountLabel;
    @FXML private Label rejectedApplicationsCountLabel;
    @FXML private TableColumn<ScholarshipApplication, Integer> cgpaTableC;
    @FXML private TableColumn<ScholarshipApplication, String> studentNameTableC;
    @FXML private TableColumn<ScholarshipApplication, Integer> studentIdTableC;
    @FXML private TableColumn<ScholarshipApplication, Double> monthlyFamilyIncomeTableC;
    @FXML private TableView<ScholarshipApplication> pendingApplicationsTableView;
    @FXML private Label eligibleApplicationsCountLabel;
    @FXML private Label needReviewCountLabel;
    @FXML private TableColumn<ScholarshipApplication, String> applicationStatusTableC;
    @FXML private TableColumn<ScholarshipApplication, String> applicationIdTableC;
    @FXML private TableColumn<ScholarshipApplication, String> reviewButtonTableC;
    @FXML private TableColumn<ScholarshipApplication, String> scholarshipSchemeTableC;

    private static ScholarshipApplication selectedApplication;
    private static final String FILE_PATH = "scholarships.dat";

    public static ScholarshipApplication getSelectedApplication() {
        return selectedApplication;
    }

    @FXML
    public void initialize() {
        // Step 1: Map Table Columns
        applicationIdTableC.setCellValueFactory(new PropertyValueFactory<>("applicationId"));
        studentIdTableC.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        studentNameTableC.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        scholarshipSchemeTableC.setCellValueFactory(new PropertyValueFactory<>("scholarshipScheme"));
        cgpaTableC.setCellValueFactory(new PropertyValueFactory<>("cgpa"));
        monthlyFamilyIncomeTableC.setCellValueFactory(new PropertyValueFactory<>("familyIncome"));
        applicationStatusTableC.setCellValueFactory(new PropertyValueFactory<>("applicationStatus"));

        // Step 2: Configure Review Button Column
        reviewButtonTableC.setCellFactory(column -> new TableCell<ScholarshipApplication, String>() {
            private final Button reviewButton = new Button("Review");

            {
                reviewButton.setStyle("-fx-background-color: #083C57; -fx-text-fill: white; -fx-cursor: hand;");
                reviewButton.setOnAction(event -> {
                    ScholarshipApplication app = getTableView().getItems().get(getIndex());
                    selectedApplication = app;
                    openReviewPage();
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : reviewButton);
            }
        });

        // Step 3: Check Binary Data File
        File file = new File(FILE_PATH);
        if (!file.exists() || file.length() == 0) {
            generateDummyData();
        }

        loadTableData();
    }

    private void generateDummyData() {
        List<Object> dummyList = new ArrayList<>();
        dummyList.add(new ScholarshipApplication("APP-101", "21301001", "Rahim Ahmed", "Merit Scholarship", 3.85, 45000.0, "Good CGPA", "Pending"));
        dummyList.add(new ScholarshipApplication("APP-102", "21301002", "Fatima Khan", "Financial Aid", 3.20, 20000.0, "Needs Aid", "Eligible"));

        BinaryFileUtil.writeObjects(FILE_PATH, dummyList);
    }

    private void loadTableData() {
        ArrayList<Object> rawList = BinaryFileUtil.readObjects(FILE_PATH);
        ObservableList<ScholarshipApplication> appList = FXCollections.observableArrayList();

        int pending = 0;
        int eligible = 0;
        int needReview = 0;
        int rejected = 0;

        if (rawList != null) {
            for (Object obj : rawList) {
                if (obj instanceof ScholarshipApplication) {
                    ScholarshipApplication app = (ScholarshipApplication) obj;
                    appList.add(app);

                    String status = app.getApplicationStatus();
                    if ("Pending".equalsIgnoreCase(status)) pending++;
                    else if ("Eligible".equalsIgnoreCase(status)) eligible++;
                    else if ("Under Review".equalsIgnoreCase(status)) needReview++;
                    else if ("Rejected".equalsIgnoreCase(status)) rejected++;
                }
            }
        }

        pendingApplicationsTableView.setItems(appList);

        // Update Dashboard Cards
        pendingApplicationsCountLabel.setText(String.valueOf(pending));
        eligibleApplicationsCountLabel.setText(String.valueOf(eligible));
        needReviewCountLabel.setText(String.valueOf(needReview));
        rejectedApplicationsCountLabel.setText(String.valueOf(rejected));
    }

    private void openReviewPage() {
        AnchorPane contentArea = (AnchorPane) pendingApplicationsTableView.getScene().lookup("#contentArea");
        SubViewSwitcher.loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U08/view/U08G1_scholarshipReviewDetail.fxml");
    }
}