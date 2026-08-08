package c213.dosaoopproject.Bushra.U08.controller;

import c213.dosaoopproject.Bushra.U08.model.HousingApplication;
import c213.dosaoopproject.commonClass.data.BinaryFileUtil;
import c213.dosaoopproject.commonClass.util.AlertUtil;
import c213.dosaoopproject.commonClass.util.SubViewSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class U08G6_HousingDashboardController
{
    @javafx.fxml.FXML
    private TableColumn<HousingApplication, LocalDate> applicationDateTableC;
    @javafx.fxml.FXML
    private TableColumn<HousingApplication, String> genderTableC;
    @javafx.fxml.FXML
    private TableColumn<HousingApplication, String> studentNameTableC;
    @javafx.fxml.FXML
    private TableColumn<HousingApplication, String> requestIdTableC;
    @javafx.fxml.FXML
    private TableColumn<HousingApplication, String> studentIdTableC;
    @javafx.fxml.FXML
    private TableColumn<HousingApplication, String> urgencyPriorityTableC;
    @javafx.fxml.FXML
    private TableView <HousingApplication>waitingListTableView;
    @javafx.fxml.FXML
    private Button allocateRoomButton;
    @javafx.fxml.FXML
    private TableColumn<HousingApplication, String> applicationStatusTableC;

    private static HousingApplication selectedApplication;
    private static final String FILE_PATH = "housing_data.dat";

    public static HousingApplication getSelectedApplication() {
        return selectedApplication;
    }

    @FXML
    public void initialize() {
        // Step 4: Map Table Columns
        requestIdTableC.setCellValueFactory(new PropertyValueFactory<>("requestId"));
        studentIdTableC.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        studentNameTableC.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        genderTableC.setCellValueFactory(new PropertyValueFactory<>("gender"));
        applicationDateTableC.setCellValueFactory(new PropertyValueFactory<>("applicationDate"));
        urgencyPriorityTableC.setCellValueFactory(new PropertyValueFactory<>("urgencyPriority"));
        applicationStatusTableC.setCellValueFactory(new PropertyValueFactory<>("applicationStatus"));

        // Table Selection Listener
        waitingListTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                selectedApplication = newSel;
            }
        });

        // Initialize Binary Data
        File file = new File(FILE_PATH);
        if (!file.exists() || file.length() == 0) {
            generateDummyData();
        }

        loadTableData();
    }

    private void generateDummyData() {
        List<Object> dummyList = new ArrayList<>();
        dummyList.add(new HousingApplication("REQ-101", "2130101", "Tanzim Ahmed", "Male", LocalDate.parse("2026-03-20"), "High", "Pending"));
        dummyList.add(new HousingApplication("REQ-102", "2130105", "Nusrat Jahan", "Female", LocalDate.parse("2026-03-22"), "Medium", "Pending"));
        dummyList.add(new HousingApplication("REQ-103", "2130120", "Arafat Hossain", "Male", LocalDate.parse("2026-03-25"), "Urgent", "Pending"));
        dummyList.add(new HousingApplication("REQ-104", "2130142", "Sumaiya Kabir", "Female", LocalDate.parse("2026-03-28"), "Low", "Allocated"));

        BinaryFileUtil.writeObjects(FILE_PATH, dummyList);
    }

    private void loadTableData() {
        ArrayList<Object> rawList = BinaryFileUtil.readObjects(FILE_PATH);
        ObservableList<HousingApplication> applications = FXCollections.observableArrayList();

        if (rawList != null) {
            for (Object obj : rawList) {
                if (obj instanceof HousingApplication) {
                    applications.add((HousingApplication) obj);
                }
            }
        }

        waitingListTableView.setItems(applications);
    }

    @FXML
    private void allocateRoomOA(ActionEvent event) {
        if (selectedApplication == null) {
            AlertUtil.showWarning("No Selection", "Please select a student from the waiting list to allocate a room.");
            return;
        }

        openAllocationView();
    }

    private void openAllocationView() {
        AnchorPane contentArea = (AnchorPane) waitingListTableView.getScene().lookup("#contentArea");
        SubViewSwitcher.loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U08/U08G6_roomAllocation.fxml");
    }
}