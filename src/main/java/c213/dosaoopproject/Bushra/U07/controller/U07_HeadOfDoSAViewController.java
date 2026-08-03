package c213.dosaoopproject.Bushra.U07.controller;

import c213.dosaoopproject.commonClass.util.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class U07_HeadOfDoSAViewController {

    @FXML private StackPane contentArea;
    @FXML private Button btnDashboard;
    @FXML private Button btnPendingEventsCard;
    @FXML private Button btnPendingScholarshipsCard;
    @FXML private Button btnBudgetManagement;
    @FXML private Button btnDisciplinaryAppeals;
    @FXML private Button btnCrisisManagement;
    @FXML private Button btnReportsAnalytics;
    @FXML private Button btnExchangePrograms;
    @FXML private Button btnExternalRelations;
    @FXML private Button btnCoCurricularTranscripts;

    @FXML private TextField searchOFCRTF;
    @FXML private ImageView ppImageView;
    @FXML private Label nameLabel;
    @FXML private Label userIdLabel;

    private static U07_HeadOfDoSAViewController instance;

    public static U07_HeadOfDoSAViewController getInstance() {
        return instance;
    }

    @FXML
    public void initialize() {
        instance = this;
        // Default to loading the dashboard overview upon login
        loadSubView("/c213/dosaoopproject/Bushra/U07/U07_dashboardOverview.fxml");
        highlightActiveButton(btnDashboard);
    }

    /**
     * Loads child FXML content inside the main contentArea StackPane
     */
    public void loadSubView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent view = loader.load();
            contentArea.getChildren().clear();
            contentArea.getChildren().add(view);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load sub-view: " + fxmlPath);
        }
    }

    private void highlightActiveButton(Button activeButton) {
        Button[] sidebarButtons = {
                btnDashboard, btnPendingEventsCard, btnPendingScholarshipsCard,
                btnBudgetManagement, btnDisciplinaryAppeals, btnCrisisManagement,
                btnReportsAnalytics, btnExchangePrograms, btnExternalRelations,
                btnCoCurricularTranscripts
        };

        for (Button btn : sidebarButtons) {
            if (btn != null) {
                if (btn == activeButton) {
                    btn.setStyle("-fx-background-color: #004675; -fx-text-fill: white; -fx-background-radius: 7; -fx-border-radius: 7;");
                } else {
                    btn.setStyle("-fx-background-color: #083C57; -fx-text-fill: white; -fx-background-radius: 7; -fx-border-radius: 7;");
                }
            }
        }
    }

    @FXML
    public void dashboardViewOA(ActionEvent actionEvent) {
        highlightActiveButton(btnDashboard);
        loadSubView("/c213/dosaoopproject/Bushra/U07/U07_dashboardOverview.fxml");
    }

    @FXML
    public void majorEventViewOA(ActionEvent actionEvent) {
        highlightActiveButton(btnPendingEventsCard);
        loadSubView("/c213/dosaoopproject/Bushra/U07/U07G1_eventApprovalQueue.fxml");
    }

    @FXML
    public void scholarshipViewOA(ActionEvent actionEvent) {
        highlightActiveButton(btnPendingScholarshipsCard);
        // Load scholarship view when built
    }

    @FXML
    public void budgetManagementOA(ActionEvent actionEvent) {
        highlightActiveButton(btnBudgetManagement);
    }

    @FXML
    public void disciplinaryAppealsOA(ActionEvent actionEvent) {
        highlightActiveButton(btnDisciplinaryAppeals);
    }

    @FXML
    public void crisisViewOA(ActionEvent actionEvent) {
        highlightActiveButton(btnCrisisManagement);
    }

    @FXML
    public void reportViewOA(ActionEvent actionEvent) {
        highlightActiveButton(btnReportsAnalytics);
    }

    @FXML
    public void exchangeViewOA(ActionEvent actionEvent) {
        highlightActiveButton(btnExchangePrograms);
    }

    @FXML
    public void partnershipViewOA(ActionEvent actionEvent) {
        highlightActiveButton(btnExternalRelations);
    }

    @FXML
    public void transcriptViewOA(ActionEvent actionEvent) {
        highlightActiveButton(btnCoCurricularTranscripts);
    }

    @FXML
    public void notificationOA(ActionEvent actionEvent) {
        System.out.println("Notifications clicked");
    }

    @FXML
    public void logOutOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo(actionEvent, "/commonFXML/LoginView.fxml", "Login");
    }

    public StackPane getContentArea() {
        return contentArea;
    }
}