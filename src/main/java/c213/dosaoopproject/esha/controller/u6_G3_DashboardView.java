package c213.dosaoopproject.esha.controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.ActionEvent;

public class u6_G3_DashboardView {

    private static final double CERTIFICATE_HOUR_THRESHOLD = 20.0;

    @FXML
    private TableView<VolunteerRow> recordsTable;

    @FXML
    private TableColumn<VolunteerRow,Integer> approvalIdColumn;

    @FXML
    private TableColumn<VolunteerRow,Integer> volunteerIdColumn;

    @FXML
    private TableColumn<VolunteerRow,Double> totalHoursColumn;

    @FXML
    private TableColumn<VolunteerRow,String> hourStatusColumn;

    @FXML
    private TableColumn<VolunteerRow,String> certificateStatusColumn;

    @FXML
    private Label statusLabel;

    @FXML
    private Button approveHoursBtn;

    @FXML
    private Button checkEligibilityBtn;

    @FXML
    private Button generateCertificateBtn;

    @FXML
    private Button refreshBtn;

    private final ObservableList<VolunteerRow> records =
            FXCollections.observableArrayList();

    private int coordinatorId;

    public void setCoordinatorId(int coordinatorId){
        this.coordinatorId = coordinatorId;
        loadRecords();
    }

    @FXML
    public void initialize(){

        approvalIdColumn.setCellValueFactory(new PropertyValueFactory<>("approvalId"));
        volunteerIdColumn.setCellValueFactory(new PropertyValueFactory<>("volunteerId"));
        totalHoursColumn.setCellValueFactory(new PropertyValueFactory<>("totalVolunteerHours"));
        hourStatusColumn.setCellValueFactory(new PropertyValueFactory<>("currentStatus"));
        certificateStatusColumn.setCellValueFactory(new PropertyValueFactory<>("certificateStatus"));

        recordsTable.setItems(records);

        loadRecords();
    }

    private void loadRecords(){

        records.setAll(
                new VolunteerRow(1,3001,12.5,"Pending","Not Eligible"),
                new VolunteerRow(2,3002,30.0,"Pending","Not Eligible"),
                new VolunteerRow(3,3003,22.0,"Approved","Not Generated")
        );

    }

    private VolunteerRow requireSelection(){

        VolunteerRow row = recordsTable.getSelectionModel().getSelectedItem();

        if(row==null){
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Select a record first.");
        }

        return row;
    }

    private boolean validateRecord(VolunteerRow row){

        if(row.getTotalVolunteerHours()<0){
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Hours cannot be negative.");
            return false;
        }

        return true;
    }

    private void updateCertificateEligibility(VolunteerRow row){

        boolean approved =
                row.getCurrentStatus().equals("Approved");

        boolean enoughHours =
                row.getTotalVolunteerHours()>=CERTIFICATE_HOUR_THRESHOLD;

        if(!approved){
            row.setCertificateStatus("Not Eligible");
        }
        else if(!enoughHours){
            row.setCertificateStatus("Not Eligible");
        }
        else if(!row.getCertificateStatus().equals("Generated")){
            row.setCertificateStatus("Not Generated");
        }
    }

    @FXML
    private void handleApproveHours(ActionEvent e){

        VolunteerRow row=requireSelection();

        if(row==null) return;

        if(!validateRecord(row)) return;

        row.setCurrentStatus("Approved");

        updateCertificateEligibility(row);

        recordsTable.refresh();

        statusLabel.setText("Hours approved.");
    }

    @FXML
    private void handleCheckEligibility(ActionEvent e){

        VolunteerRow row=requireSelection();

        if(row==null) return;

        updateCertificateEligibility(row);

        recordsTable.refresh();

        statusLabel.setText("Eligibility checked.");
    }

    @FXML
    private void handleGenerateCertificate(ActionEvent e){

        VolunteerRow row=requireSelection();

        if(row==null) return;

        if(!row.getCurrentStatus().equals("Approved")){
            statusLabel.setText("Approve hours first.");
            return;
        }

        if(row.getTotalVolunteerHours()<CERTIFICATE_HOUR_THRESHOLD){
            statusLabel.setText("Volunteer is not eligible.");
            return;
        }

        row.setCertificateStatus("Generated");

        recordsTable.refresh();

        statusLabel.setText("Certificate generated.");
    }

    @FXML
    private void handleRefresh(ActionEvent e){

        loadRecords();

        statusLabel.setText("Refreshed.");
    }

    public static class VolunteerRow{

        private final SimpleIntegerProperty approvalId;
        private final SimpleIntegerProperty volunteerId;
        private final SimpleDoubleProperty totalVolunteerHours;
        private final SimpleStringProperty currentStatus;
        private final SimpleStringProperty certificateStatus;

        public VolunteerRow(int approvalId,int volunteerId,double totalVolunteerHours,String currentStatus,String certificateStatus){

            this.approvalId=new SimpleIntegerProperty(approvalId);
            this.volunteerId=new SimpleIntegerProperty(volunteerId);
            this.totalVolunteerHours=new SimpleDoubleProperty(totalVolunteerHours);
            this.currentStatus=new SimpleStringProperty(currentStatus);
            this.certificateStatus=new SimpleStringProperty(certificateStatus);

        }

        public int getApprovalId(){
            return approvalId.get();
        }

        public int getVolunteerId(){
            return volunteerId.get();
        }

        public double getTotalVolunteerHours(){
            return totalVolunteerHours.get();
        }

        public String getCurrentStatus(){
            return currentStatus.get();
        }

        public void setCurrentStatus(String status){
            currentStatus.set(status);
        }

        public String getCertificateStatus(){
            return certificateStatus.get();
        }

        public void setCertificateStatus(String status){
            certificateStatus.set(status);
        }
    }
}