package c213.dosaoopproject.Bushra.U07.controller;

import c213.dosaoopproject.Bushra.U07.model.DisciplinaryAppeal;
import c213.dosaoopproject.commonClass.data.BinaryFileUtil;
import c213.dosaoopproject.commonClass.util.AlertUtil;
import c213.dosaoopproject.commonClass.util.SubViewSwitcher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class U07G3_appealsListController {

    @FXML private TableView<DisciplinaryAppeal> appealsTableView;
    @FXML private TableColumn<DisciplinaryAppeal, String> appealIdTableC;
    @FXML private TableColumn<DisciplinaryAppeal, String> studentIDTableC;
    @FXML private TableColumn<DisciplinaryAppeal, String> offenseTypeTableC;
    @FXML private TableColumn<DisciplinaryAppeal, String> originalPenaltyTableC;
    @FXML private TableColumn<DisciplinaryAppeal, Object> submissionDateTableC;

    private static final String FILE_PATH = "student_cases.dat";
    private static DisciplinaryAppeal selectedAppeal;


    public static DisciplinaryAppeal getSelectedAppeal() {
        return selectedAppeal;
    }

    @FXML
    public void initialize() {
        appealIdTableC.setCellValueFactory(new PropertyValueFactory<>("appealId"));
        studentIDTableC.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        offenseTypeTableC.setCellValueFactory(new PropertyValueFactory<>("offense"));
        originalPenaltyTableC.setCellValueFactory(new PropertyValueFactory<>("originalPenalty"));
        submissionDateTableC.setCellValueFactory(new PropertyValueFactory<>("submissionDate"));

        loadAppealsData();
    }

    private void loadAppealsData() {
        ArrayList<Object> rawList = BinaryFileUtil.readObjects(FILE_PATH);
        ObservableList<DisciplinaryAppeal> appealsList = FXCollections.observableArrayList();

        int pendingCount = 0;

        if (rawList != null) {
            for (Object obj : rawList) {
                if (obj instanceof DisciplinaryAppeal) {
                    DisciplinaryAppeal appeal = (DisciplinaryAppeal) obj;
                    appealsList.add(appeal);
                    if ("Pending".equalsIgnoreCase(appeal.getStatus())) {
                        pendingCount++;
                    }
                }
            }
        }

        appealsTableView.setItems(appealsList);

    }

    @FXML
    public void viewAppealOA(ActionEvent event) {
        selectedAppeal = appealsTableView.getSelectionModel().getSelectedItem();

        if (selectedAppeal == null) {
            AlertUtil.showWarning("Warning", "Please select an appeal from the table.");
            return;
        }

        AnchorPane contentArea = (AnchorPane) ((Node) event.getSource()).getScene().lookup("#contentArea");
        SubViewSwitcher.loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U07/U07G3_AppealDetail.fxml");
    }
}