package c213.dosaoopproject.Bushra.U08.controller;

import c213.dosaoopproject.Bushra.U08.model.Grievance;
import c213.dosaoopproject.commonClass.data.BinaryFileUtil;
import c213.dosaoopproject.commonClass.util.SubViewSwitcher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class U08G3_GrievanceManagementController {

    @FXML
    private TableView<Grievance> academicGrievancesTableView;
    @FXML
    private TableView<Grievance> facilityGrievancesTableView;
    @FXML
    private TableColumn<Grievance, String> academicCategoryTableC;
    @FXML
    private TableColumn<Grievance, String> facilityCategoryTableC;
    @FXML
    private TableColumn<Grievance, String> academicGrievanceIdTableC;
    @FXML
    private TableColumn<Grievance, String> academicGrievanceStatusTableC;
    @FXML
    private Label facilityGrievanceCountLabel;
    @FXML
    private Label closedGrievanceCountLabel;
    @FXML
    private TableColumn<Grievance, String> facilityGrievanceIdTableC;
    @FXML
    private TableColumn<Grievance, String> facilityGrievanceStatusTableC;
    @FXML
    private Label academicGrievanceCountLabel;
    @FXML
    private Label openGrievanceCountLabel;


    private final ObservableList<Grievance> academicList = FXCollections.observableArrayList();
    private final ObservableList<Grievance> facilityList = FXCollections.observableArrayList();
    private static Grievance selectedGrievance;
    private final String FILE_PATH = "grievances_data.dat";

    @FXML
    public void initialize() {

        // Load data
        loadGrievanceData();

        // Put data into tables
        academicGrievancesTableView.setItems(academicList);
        facilityGrievancesTableView.setItems(facilityList);

        // Academic table columns
        academicGrievanceIdTableC.setCellValueFactory(new PropertyValueFactory<>("grievanceId"));
        academicCategoryTableC.setCellValueFactory(new PropertyValueFactory<>("category"));
        academicGrievanceStatusTableC.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Facility table columns
        facilityGrievanceIdTableC.setCellValueFactory(new PropertyValueFactory<>("grievanceId"));
        facilityCategoryTableC.setCellValueFactory(new PropertyValueFactory<>("category"));
        facilityGrievanceStatusTableC.setCellValueFactory(new PropertyValueFactory<>("status"));
    }


    private void loadGrievanceData() {

        academicList.clear();
        facilityList.clear();

        java.io.File file = new java.io.File(FILE_PATH);

        if (!file.exists()) {
            generateDummyData();
        }

        List<Object> objects = BinaryFileUtil.readObjects(FILE_PATH);

        for (Object obj : objects) {
            if (obj instanceof Grievance) {
                Grievance g = (Grievance) obj;
                if (g.getCategory().equalsIgnoreCase("Grading / Exam") || g.getCategory().equalsIgnoreCase("Faculty Conduct")) {
                    academicList.add(g);
                } else {
                    facilityList.add(g);
                }
            }
        }

        academicGrievanceCountLabel.setText(String.valueOf(academicList.size()));
        facilityGrievanceCountLabel.setText(String.valueOf(facilityList.size()));

        int open = 0;
        int closed = 0;

        for (Grievance g : academicList) {
            if (g.getStatus().equalsIgnoreCase("Open")) {
                open++;
            } else {
                closed++;
            }
        }

        for (Grievance g : facilityList) {
            if (g.getStatus().equalsIgnoreCase("Open")) {
                open++;
            } else {
                closed++;
            }
        }

        openGrievanceCountLabel.setText(String.valueOf(open));
        closedGrievanceCountLabel.setText(String.valueOf(closed));
    }

    public static Grievance getSelectedGrievance() {
        return selectedGrievance;
    }

    private void generateDummyData() {

        List<Object> data = new java.util.ArrayList<>();

        data.add(new Grievance(
                "GRV-001",
                "21301001",
                "Rahim Ahmed",
                "Grading / Exam",
                "2026-08-01",
                "High",
                "Academic Affairs",
                "Problem with exam result",
                "Academic Affairs",
                "Open",
                "exam_result.pdf"
        ));

        data.add(new Grievance(
                "GRV-002",
                "21301002",
                "Fatima Khan",
                "Faculty Conduct",
                "2026-08-02",
                "Medium",
                "Academic Affairs",
                "Complaint about faculty conduct",
                "Academic Affairs",
                "Closed",
                "faculty_complaint.pdf"
        ));

        data.add(new Grievance(
                "GRV-003",
                "21301003",
                "Nusrat Jahan",
                "Classroom Facility",
                "2026-08-03",
                "Medium",
                "Facilities",
                "Air conditioning problem",
                "Facilities Department",
                "Open",
                "classroom_photo.jpg"
        ));

        data.add(new Grievance(
                "GRV-004",
                "21301004",
                "Sakib Hasan",
                "Library Facility",
                "2026-08-04",
                "Low",
                "Facilities",
                "Library computer problem",
                "Library Administration",
                "Closed",
                "computer_problem.jpg"
        ));

        BinaryFileUtil.writeObjects(FILE_PATH, data);
    }

    @FXML
    public void openGrievanceOA(ActionEvent actionEvent) {
        Grievance selected = academicGrievancesTableView
                .getSelectionModel()
                .getSelectedItem();

        if (selected == null) {
            selected = facilityGrievancesTableView
                    .getSelectionModel()
                    .getSelectedItem();
        }

        if (selected == null) {
            return;
        }

        selectedGrievance = selected;

        AnchorPane contentArea = (AnchorPane) academicGrievancesTableView.getScene().lookup("#contentArea");
        SubViewSwitcher.loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U08/U08G3_grievanceDetail.fxml");
    }
}