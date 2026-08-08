package c213.dosaoopproject.Bushra.U07.controller;

import c213.dosaoopproject.Bushra.U07.model.ExchangeNomination;
import c213.dosaoopproject.commonClass.data.BinaryFileUtil;
import c213.dosaoopproject.commonClass.data.TextFileUtil;
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

public class U07G6_ExchangeProgramsListController
{
    @javafx.fxml.FXML
    private TableColumn<ExchangeNomination, Double> colCgpa;
    @javafx.fxml.FXML
    private Label totalCountLabel;
    @javafx.fxml.FXML
    private TableView<ExchangeNomination> nominationsTableView;
    @javafx.fxml.FXML
    private TableColumn<ExchangeNomination, String> colStudentName;
    @javafx.fxml.FXML
    private TableColumn<ExchangeNomination, String> colHostUniv;
    @javafx.fxml.FXML
    private TableColumn<ExchangeNomination, String> colNominationId;
    @javafx.fxml.FXML
    private Button reviewNominationButton;
    @javafx.fxml.FXML
    private TableColumn<ExchangeNomination, String> colStatus;

    private static ExchangeNomination selectedNomination;
    private static final String FILE_PATH = "exchange_data.dat";

    public static ExchangeNomination getSelectedNomination() {
        return selectedNomination;
    }

    @FXML
    public void initialize() {
        colNominationId.setCellValueFactory(new PropertyValueFactory<>("nominationId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colHostUniv.setCellValueFactory(new PropertyValueFactory<>("hostUniversity"));
        colCgpa.setCellValueFactory(new PropertyValueFactory<>("cgpa"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        nominationsTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            reviewNominationButton.setDisable(newSel == null);
        });

        File file = new File(FILE_PATH);
        if (!file.exists() || file.length() == 0) {
            generateDummyData();
        }

        loadTableData();
    }

    private void generateDummyData() {
        List<Object> dummyList = new ArrayList<>();
        dummyList.add(new ExchangeNomination("EX-2026-01", "Tanvir Hasan", "University of Malaya", 3.85, "Pending Review", "N/A"));
        dummyList.add(new ExchangeNomination("EX-2026-02", "Ayesha Siddiqua", "National University of Singapore", 3.92, "Pending Review", "N/A"));
        dummyList.add(new ExchangeNomination("EX-2026-03", "Mahir Chowdhury", "Kyoto University", 3.65, "Request Info", "Transcript missing semester 5"));
        dummyList.add(new ExchangeNomination("EX-2026-04", "Sumiya Akter", "University of Sydney", 3.78, "Approved", "Formally nominated"));

        BinaryFileUtil.writeObjects(FILE_PATH, dummyList);
    }

    private void loadTableData() {
        ArrayList<Object> rawList = BinaryFileUtil.readObjects(FILE_PATH);
        ObservableList<ExchangeNomination> nominations = FXCollections.observableArrayList();

        if (rawList != null) {
            for (Object obj : rawList) {
                if (obj instanceof ExchangeNomination) {
                    nominations.add((ExchangeNomination) obj);
                }
            }
        }

        nominationsTableView.setItems(nominations);
        totalCountLabel.setText("Total: " + nominations.size() + " items");
    }

    @FXML
    private void handleReviewNomination(ActionEvent event) {
        selectedNomination = nominationsTableView.getSelectionModel().getSelectedItem();
        if (selectedNomination == null) {
            AlertUtil.showWarning("Selection Error", "Please select a nomination from the table.");
            return;
        }

        AnchorPane contentArea = (AnchorPane) nominationsTableView.getScene().lookup("#contentArea");
        SubViewSwitcher.loadSubView(
                contentArea,
                "/c213/dosaoopproject/Bushra/U07/view/U07G6_nominationDetail.fxml"
        );
    }
}