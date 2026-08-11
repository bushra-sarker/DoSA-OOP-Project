package c213.dosaoopproject.Bushra.U07.controller;

import c213.dosaoopproject.Bushra.U07.model.CrisisInterventionRecord;
import c213.dosaoopproject.commonClass.data.BinaryFileUtil;
import c213.dosaoopproject.commonClass.util.AlertUtil;
import c213.dosaoopproject.commonClass.util.SubViewSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class U07G5_CrisisDashboardController
{
    @javafx.fxml.FXML
    private TableColumn<CrisisInterventionRecord, String> colCaseId;
    @javafx.fxml.FXML
    private TableColumn<CrisisInterventionRecord, String> colTeam;
    @javafx.fxml.FXML
    private Label totalCountLabel;
    @javafx.fxml.FXML
    private TableColumn<CrisisInterventionRecord, String> colStudentId;
    @javafx.fxml.FXML
    private TableView<CrisisInterventionRecord> activeCrisisTableView;
    @javafx.fxml.FXML
    private TableColumn<CrisisInterventionRecord, String> colUrgency;
    @javafx.fxml.FXML
    private TableColumn<CrisisInterventionRecord, String> colStatus;
    @javafx.fxml.FXML
    private Button viewCaseButton;

    //part of loading the table
    private static CrisisInterventionRecord selectedRecord;
    private static final String FILE_PATH = "crisis_records.dat";

    //select the raw
    public static CrisisInterventionRecord getSelectedRecord() {
        return selectedRecord;
    }

    @FXML
    public void initialize() {
        colCaseId.setCellValueFactory(new PropertyValueFactory<>("caseId"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colUrgency.setCellValueFactory(new PropertyValueFactory<>("urgencyLevel"));
        colTeam.setCellValueFactory(new PropertyValueFactory<>("responseTeam"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        activeCrisisTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            viewCaseButton.setDisable(newSelection == null);
        });

        // Ensure file exists with dummy data if missing
        File file = new File(FILE_PATH);
        if (!file.exists() || file.length() == 0) {
            generateDummyData();
        }

        loadTableData();
    }

    private void generateDummyData() {
        List<Object> data = new ArrayList<>();

        data.add(new CrisisInterventionRecord(
                "CRS-2026-001",
                "21301042",
                "CRITICAL",
                "Medical & Counseling Unit A",
                "Initial counseling provided. Follow-up scheduled.",
                "OPEN"
        ));

        data.add(new CrisisInterventionRecord(
                "CRS-2026-002",
                "21301088",
                "HIGH",
                "Student Affairs Helpline",
                "Hostel accommodation emergency resolved.",
                "OPEN"
        ));

        data.add(new CrisisInterventionRecord(
                "CRS-2026-003",
                "21301105",
                "MEDIUM",
                "Financial Aid Assistance",
                "Tuition extension granted under medical grounds.",
                "RESOLVED"
        ));

        data.add(new CrisisInterventionRecord(
                "CRS-2026-004",
                "21301199",
                "CRITICAL",
                "Campus Security Response",
                "Medical transport provided to nearest hospital.",
                "OPEN"
        ));

        BinaryFileUtil.writeObjects(FILE_PATH, data);
    }

    //populate the table
    private void loadTableData() {
        ArrayList<Object> rawList = BinaryFileUtil.readObjects("crisis_records.dat");
        ObservableList<CrisisInterventionRecord> records = FXCollections.observableArrayList();

        if (rawList != null) {
            for (Object obj : rawList) {
                if (obj instanceof CrisisInterventionRecord) {
                    records.add((CrisisInterventionRecord) obj);
                }
            }
        }

        activeCrisisTableView.setItems(records);
        totalCountLabel.setText("Total: " + records.size() + " items");
    }

    //open details of the selected row + view
    @FXML
    public void handleViewCaseOA(ActionEvent actionEvent) {
        selectedRecord = activeCrisisTableView.getSelectionModel().getSelectedItem();
        if (selectedRecord == null) {
            AlertUtil.showWarning("Selection Error", "Please select a crisis record from the table.");
            return;
        }

        AnchorPane contentArea = (AnchorPane) activeCrisisTableView.getScene().lookup("#contentArea");
        SubViewSwitcher.loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U07/U07G5_crisisDetail.fxml");
    }
}