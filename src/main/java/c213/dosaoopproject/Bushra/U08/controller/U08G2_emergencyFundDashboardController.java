package c213.dosaoopproject.Bushra.U08.controller;

import c213.dosaoopproject.Bushra.U08.model.EmergencyRequest;
import c213.dosaoopproject.commonClass.util.AlertUtil;
import c213.dosaoopproject.commonClass.util.SubViewSwitcher;
import c213.dosaoopproject.commonClass.data.TextFileUtil;

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

import java.util.List;

public class U08G2_emergencyFundDashboardController {

    @FXML private Label availableFundBalanceLabel;
    @FXML private Label pendingCountLabel;
    @FXML private TableView<EmergencyRequest> emergencyRequestsTableView;
    @FXML private TableColumn<EmergencyRequest, String> colRequestId;
    @FXML private TableColumn<EmergencyRequest, String> colStudentId;
    @FXML private TableColumn<EmergencyRequest, String> colStudentName;
    @FXML private TableColumn<EmergencyRequest, String> colCategory;
    @FXML private TableColumn<EmergencyRequest, Double> colRequestedAmount;
    @FXML private TableColumn<EmergencyRequest, String> colStatus;
    @FXML private Button processRequestButton;

    private static final String FILE_PATH = "emergency_fund.txt";
    private final ObservableList<EmergencyRequest> requestList = FXCollections.observableArrayList();
    private static EmergencyRequest selectedRequest = null;
    private static double availableBalance = 250000.0; // Default fund balance

    public static EmergencyRequest getSelectedRequest() {
        return selectedRequest;
    }

    public static double getAvailableBalance() {
        return availableBalance;
    }

    public static void setAvailableBalance(double balance) {
        availableBalance = balance;
    }

    @FXML
    public void initialize() {
        colRequestId.setCellValueFactory(new PropertyValueFactory<>("requestId"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colRequestedAmount.setCellValueFactory(new PropertyValueFactory<>("requestedAmount"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadEmergencyFundData();
    }

    private void loadEmergencyFundData() {

        requestList.clear();
        int pending = 0;
        List<String> lines = TextFileUtil.readLines(FILE_PATH);

        for (String line : lines) {
            if (line == null || line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split(";");

            // Balance line
            if ("BALANCE".equalsIgnoreCase(parts[0])) {
                availableBalance = Double.parseDouble(parts[1].trim());
                continue;
            }

            // Emergency request
            if (parts.length >= 11) {

                EmergencyRequest req = new EmergencyRequest(
                        parts[0].trim(),
                        parts[1].trim(),
                        parts[2].trim(),
                        parts[3].trim(),
                        parts[4].trim(),
                        Double.parseDouble(parts[5].trim()),
                        parts[6].trim(),
                        parts[7].trim(),
                        parts[8].trim(),
                        Double.parseDouble(parts[9].trim()),
                        parts[10].trim()
                );

                requestList.add(req);

                if ("Pending".equalsIgnoreCase(req.getStatus())) {
                    pending++;
                }
            }
        }

        // 1. Attach the ObservableList to the TableView
        emergencyRequestsTableView.setItems(requestList);

        // 2. Refresh UI Labels
        if (availableFundBalanceLabel != null) {
            availableFundBalanceLabel.setText("BDT " + String.format("%.2f", availableBalance));
        }
        if (pendingCountLabel != null) {
            pendingCountLabel.setText(String.valueOf(pending));
        }
    }

    @FXML
    public void processRequestOA(ActionEvent event) {
        selectedRequest = emergencyRequestsTableView.getSelectionModel().getSelectedItem();

        if (selectedRequest == null) {
            AlertUtil.showWarning("No Selection", "Please select an emergency request row to process.");
            return;
        }

        AnchorPane contentArea = (AnchorPane) emergencyRequestsTableView.getScene().lookup("#contentArea");
        SubViewSwitcher.loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U08/U08G2_requestDetail.fxml");
    }
}