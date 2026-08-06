package c213.dosaoopproject.Bushra.U07.controller;

import c213.dosaoopproject.Bushra.U07.model.ClubBudget;
import c213.dosaoopproject.commonClass.util.AlertUtil;
import c213.dosaoopproject.commonClass.util.SubViewSwitcher;
import c213.dosaoopproject.commonClass.data.TextFileUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class U07G2_budgetManagementController {

    @FXML private TableView<ClubBudget> budgetTableView;
    @FXML private TableColumn<ClubBudget, String> clubColumn;
    @FXML private TableColumn<ClubBudget, Double> requestedColumn;
    @FXML private TableColumn<ClubBudget, Double> allocatedColumn;
    @FXML private TableColumn<ClubBudget, String> statusColumn;
    @FXML private TableColumn<ClubBudget, String> remarksColumn;
    @FXML private Button viewBudgetDetailButton;

    private static final String FILE_PATH = "src/club_budgets.txt";
    private static ClubBudget selectedBudget; // Stores selection to pass to detail view

    public static ClubBudget getSelectedBudget() {
        return selectedBudget;
    }

    @FXML
    public void initialize() {

        // Map columns to model fields
        clubColumn.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        requestedColumn.setCellValueFactory(new PropertyValueFactory<>("requestedAmount"));
        allocatedColumn.setCellValueFactory(new PropertyValueFactory<>("allocatedAmount"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        remarksColumn.setCellValueFactory(new PropertyValueFactory<>("remarks"));

        loadBudgetData();
    }

    // Easy & Clean loadBudgetData
    private void loadBudgetData() {
        ArrayList<String> lines = TextFileUtil.readLines(FILE_PATH);
        ObservableList<ClubBudget> budgetList = FXCollections.observableArrayList();

        if (lines != null) {
            for (String line : lines) {
                String[] data = line.split(",");
                if (data.length >= 5) {
                    ClubBudget budget = new ClubBudget(
                            data[0].trim(),
                            Double.parseDouble(data[1].trim()),
                            Double.parseDouble(data[2].trim()),
                            data[3].trim(),
                            data[4].trim()
                    );
                    budgetList.add(budget);
                }
            }
        }

        budgetTableView.setItems(budgetList);
    }

    @FXML
    public void viewBudgetDetailOA(ActionEvent event) {
        selectedBudget = budgetTableView.getSelectionModel().getSelectedItem();

        if (selectedBudget == null) {
            AlertUtil.showWarning("Warning", "Please select a club first.");
            return;
        }

        // Switch to Detail View
        AnchorPane contentArea = (AnchorPane) ((Node) event.getSource()).getScene().lookup("#contentArea");
        SubViewSwitcher.loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U07/U07G2_budgetDetail.fxml");
    }
}