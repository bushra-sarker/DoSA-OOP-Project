package c213.dosaoopproject.fahmida;

import commonClass.User;
import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.ApprovalLetter;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.util.Notifications;
import c213.dosaoopproject.fahmida.util.SceneManager;
import c213.dosaoopproject.fahmida.util.Search;
import c213.dosaoopproject.fahmida.util.Ui;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class U1_DashboardController
{
    @javafx.fxml.FXML
    private Label welcomeUserLabel;
    @javafx.fxml.FXML
    private Label noticesCardLabel;
    @javafx.fxml.FXML
    private Label userIdLabel11;
    @javafx.fxml.FXML
    private Label trackHistoryLabel;
    @javafx.fxml.FXML
    private Label submitComplaintsCardLabel;
    @javafx.fxml.FXML
    private TextField searchOFCRTF;
    @javafx.fxml.FXML
    private ImageView ppImageView11;
    @javafx.fxml.FXML
    private Label downloadLetterCardLabel;
    @javafx.fxml.FXML
    private Label DOSALabel;
    @javafx.fxml.FXML
    private Label nameLabel11;

    @javafx.fxml.FXML
    public void initialize() {
        User user = Session.getCurrentUser();
        if (user != null) {
            if (welcomeUserLabel != null) {
                welcomeUserLabel.setText("Welcome, " + user.getFullName());
            }
            if (nameLabel11 != null) {
                nameLabel11.setText(user.getFullName());
            }
            if (userIdLabel11 != null) {
                userIdLabel11.setText(user.getLoginId());
            }
        }
        if (searchOFCRTF != null) {
            // Search runs when the user presses Enter in the search box.
            searchOFCRTF.setOnAction(e -> Ui.info(Search.query(searchOFCRTF.getText())));
        }
    }

    @javafx.fxml.FXML
    public void registerEventsOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1G2_EventList");
    }

    @javafx.fxml.FXML
    public void notificationOA(ActionEvent actionEvent) {
        Notifications.showForCurrentUser();
    }

    @javafx.fxml.FXML
    public void viewScheduleOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1G4_ViewEventSchedule");
    }

    @javafx.fxml.FXML
    public void communityProgramOA(ActionEvent actionEvent) {
        String programs = DataStore.get().getCommunityPrograms().stream()
                .map(p -> "• " + p.getDetails())
                .collect(Collectors.joining("\n"));
        Ui.info(programs.isEmpty() ? "No community programs available." : programs);
    }

    @javafx.fxml.FXML
    public void viewNoticesOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1G1_ViewNotices");
    }

    @javafx.fxml.FXML
    public void logOutOA(ActionEvent actionEvent) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Do you want to exit?", ButtonType.YES, ButtonType.NO);
        confirm.setHeaderText(null);
        confirm.showAndWait().ifPresent(choice -> {
            if (choice == ButtonType.YES) {
                Session.clear();
                SceneManager.switchTo("LoginView");
            }
        });
    }

    @javafx.fxml.FXML
    public void downloadApprovalOA(ActionEvent actionEvent) {
        User user = Session.getCurrentUser();
        if (user == null) {
            return;
        }
        DataStore store = DataStore.get();
        ApprovalLetter letter = new ApprovalLetter(
                store.getApprovalLetters().size() + 1, user.getUserId(),
                user.getFullName(), LocalDate.now());
        Path path = Path.of("ApprovalLetter_" + user.getLoginId() + ".txt");
        try {
            Files.writeString(path, letter.content());
            letter.setFilePath(path.toString());
            store.getApprovalLetters().add(letter);
            store.logHistory(user.getUserId(), "Downloaded approval letter");
            Ui.info("Approval letter saved to:\n" + path.toAbsolutePath());
        } catch (IOException e) {
            Ui.info("Could not save the letter: " + e.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void clubMembershipOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1G3_ApplyForClub");
    }

    @javafx.fxml.FXML
    public void studentdashboardOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1_Dashboard");
    }

    @javafx.fxml.FXML
    public void trackHistoryOA(ActionEvent actionEvent) {
        User user = Session.getCurrentUser();
        String history = DataStore.get().getHistory().stream()
                .filter(h -> user != null && h.getUserId() == user.getUserId())
                .map(h -> h.getDate() + " — " + h.getAction())
                .collect(Collectors.joining("\n"));
        Ui.info(history.isEmpty() ? "No activity yet." : history);
    }

    @javafx.fxml.FXML
    public void submitComplaintsOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1G6");
    }
}