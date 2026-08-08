package c213.dosaoopproject.Bushra.U08.controller;

import c213.dosaoopproject.Bushra.U08.model.InsuranceClaim;
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

import java.util.List;

public class U08G4_InsuranceClaimsListController {

    @FXML
    private TableView<InsuranceClaim> pendingClaimsTableView;
    @FXML
    private TableColumn<InsuranceClaim, String> claimIdTableC;
    @FXML
    private TableColumn<InsuranceClaim, String> studentIdTableC;
    @FXML
    private TableColumn<InsuranceClaim, String> studentNameTableC;
    @FXML
    private TableColumn<InsuranceClaim, String> hospitalNameTableC;
    @FXML
    private TableColumn<InsuranceClaim, String> claimedAmountTableC;
    @FXML
    private TableColumn<InsuranceClaim, String> submissionDateTableC;
    @FXML
    private TableColumn<InsuranceClaim, String> claimStatusTableC;
    @FXML
    private Label pendingClaimsLabel;
    @FXML
    private Label approvedClaimsLabel;
    @FXML
    private Label needDocumentsLabel;
    @FXML
    private Label rejectedClaimsLabel;
    @FXML
    private Button processClaimButton;

    private final ObservableList<InsuranceClaim> claimList = FXCollections.observableArrayList();
    private static InsuranceClaim selectedClaim;
    private final String FILE_PATH = "insurance_claims.TXT";



    @FXML
    public void initialize() {

        claimIdTableC.setCellValueFactory(new PropertyValueFactory<>("claimId"));
        studentIdTableC.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        studentNameTableC.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        hospitalNameTableC.setCellValueFactory(new PropertyValueFactory<>("hospitalName"));
        claimedAmountTableC.setCellValueFactory(new PropertyValueFactory<>("claimedAmount"));
        submissionDateTableC.setCellValueFactory(new PropertyValueFactory<>("submissionDate"));
        claimStatusTableC.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadClaims();
    }


    private void loadClaims() {

        claimList.clear();

        List<String> lines = TextFileUtil.readLines(FILE_PATH);

        if (lines != null) {
            for (String line : lines) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",", -1);

                if (parts.length >= 10) {
                    InsuranceClaim claim = new InsuranceClaim(
                            parts[0],
                            parts[1],
                            parts[2],
                            parts[3],
                            parts[4],
                            parts[5],
                            parts[6],
                            parts[7],
                            Boolean.parseBoolean(parts[8]),
                            Boolean.parseBoolean(parts[9])
                    );

                    claimList.add(claim);
                }
            }
        }

        pendingClaimsTableView.setItems(claimList);
        updateSummary();
    }


    private void updateSummary() {

        int pending = 0;
        int approved = 0;
        int documents = 0;
        int rejected = 0;

        for (InsuranceClaim claim : claimList) {

            if (claim.getStatus().equalsIgnoreCase("Pending")) {
                pending++;
            }

            else if (claim.getStatus().equalsIgnoreCase("Approved")) {
                approved++;
            }

            else if (claim.getStatus().equalsIgnoreCase("Need Documents")) {
                documents++;
            }

            else if (claim.getStatus().equalsIgnoreCase("Rejected")) {
                rejected++;
            }
        }

        pendingClaimsLabel.setText(String.valueOf(pending));
        approvedClaimsLabel.setText(String.valueOf(approved));
        needDocumentsLabel.setText(String.valueOf(documents));
        rejectedClaimsLabel.setText(String.valueOf(rejected));
    }

    @FXML
    public void processClaimOA(ActionEvent event) {

        selectedClaim = pendingClaimsTableView.getSelectionModel().getSelectedItem();

        if (selectedClaim == null) {
            AlertUtil.showWarning("No Claim Selected", "Please select a claim first.");
            return;
        }

        AnchorPane contentArea = (AnchorPane) pendingClaimsTableView.getScene().lookup("#contentArea");
        SubViewSwitcher.loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U08/U08G4_claimDetail.fxml");
    }

    public static InsuranceClaim getSelectedClaim() {
        return selectedClaim;
    }
}