package c213.dosaoopproject.Bushra.U08.controller;

import c213.dosaoopproject.Bushra.U08.model.OrientationProgram;
import c213.dosaoopproject.commonClass.data.BinaryFileUtil;
import c213.dosaoopproject.commonClass.util.SubViewSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class U08G5_OrientationOverviewController {
    @javafx.fxml.FXML
    private TableView<OrientationProgram> orientationsTableView;
    @javafx.fxml.FXML
    private TableColumn<OrientationProgram, String> venueTableC;
    @javafx.fxml.FXML
    private Button createNewProgramButton;
    @javafx.fxml.FXML
    private TableColumn<OrientationProgram, String> semesterTableC;
    @javafx.fxml.FXML
    private TableColumn<OrientationProgram, String> endDateTableC;
    @javafx.fxml.FXML
    private TableColumn<OrientationProgram, String> programStatusTableC;
    @javafx.fxml.FXML
    private TableColumn<OrientationProgram, String> startDateTableC;
    @javafx.fxml.FXML
    private TableColumn<OrientationProgram, String> targetCohortTableC;
    @javafx.fxml.FXML
    private TableColumn<OrientationProgram, String> programIdTableC;

    private static OrientationProgram selectedProgram;
    private static final String FILE_PATH = "orientations.dat";

    public static OrientationProgram getSelectedProgram() {
        return selectedProgram;
    }

    @FXML
    public void initialize() {
        // Step 4: Map Table Columns
        programIdTableC.setCellValueFactory(new PropertyValueFactory<>("programId"));
        semesterTableC.setCellValueFactory(new PropertyValueFactory<>("semester"));
        startDateTableC.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateTableC.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        venueTableC.setCellValueFactory(new PropertyValueFactory<>("venue"));
        targetCohortTableC.setCellValueFactory(new PropertyValueFactory<>("targetCohort"));
        programStatusTableC.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Step 5: Table Selection Listener
        orientationsTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                selectedProgram = newSel;
                openSetupView();
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
        dummyList.add(new OrientationProgram("ORI-2026-01", "Spring 2026", "2026-01-15", "2026-01-17", "Main Auditorium", "Undergraduate Freshmen", "Scheduled", "Campus & Facilities Tour;Academic Policies & Curriculum Briefing"));
        dummyList.add(new OrientationProgram("ORI-2026-02", "Summer 2026", "2026-05-10", "2026-05-12", "Multipurpose Hall", "Postgraduate Cohort", "Scheduled", "Academic Policies & Curriculum Briefing;Student Welfare Services"));
        dummyList.add(new OrientationProgram("ORI-2025-03", "Fall 2025", "2025-09-01", "2025-09-03", "Main Auditorium", "Undergraduate Freshmen", "Completed", "Campus & Facilities Tour;Cultural Evening & Student Club Fair"));

        BinaryFileUtil.writeObjects(FILE_PATH, dummyList);
    }

    private void loadTableData() {
        ArrayList<Object> rawList = BinaryFileUtil.readObjects(FILE_PATH);
        ObservableList<OrientationProgram> nominations = FXCollections.observableArrayList();

        if (rawList != null) {
            for (Object obj : rawList) {
                if (obj instanceof OrientationProgram) {
                    nominations.add((OrientationProgram) obj);
                }
            }
        }

        orientationsTableView.setItems(nominations);
    }

    @FXML
    private void createNewProgramOA(ActionEvent event) {
        selectedProgram = null; // Fresh creation
        openSetupView();
    }

    private void openSetupView() {
        AnchorPane contentArea = (AnchorPane) orientationsTableView.getScene().lookup("#contentArea");
        SubViewSwitcher.loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U08/U08G5_orientationSetup.fxml");
    }
}