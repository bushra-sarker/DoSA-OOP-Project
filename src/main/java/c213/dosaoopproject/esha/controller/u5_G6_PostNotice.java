package c213.dosaoopproject.esha.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
public class u5_G6_PostNotice
{
    @FXML private TextField titleField;
    @FXML private TextArea bodyArea;
    @FXML private Label statusLabel;
    @FXML private ListView<String> noticesListView;

    private final ObservableList<String> notices = FXCollections.observableArrayList();

    private String clubName; // autofilled from the logged-in executive's session
    private Integer selectedNoticeId; // null unless a notice from the list is selected
    @FXML
    private Button publishBtn;
    @FXML
    private Button editBtn;
    @FXML
    private Button deleteBtn;

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    @FXML
    public void initialize() { noticesListView.setItems(notices);
        // TODO: load existing notices for this club and populate 'notices', e.g.:
        // notices.setAll(noticeService.getTitlesByClub(clubName));
    }

    @FXML
    private void handlePublishNotice(ActionEvent event) {
        if (titleField.getText().isBlank() || bodyArea.getText().isBlank()) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Title and body are required.");
            return;
        }

        // TODO: replace with real service call, e.g.:
        // Notice notice = new Notice(clubName, titleField.getText(), bodyArea.getText(), LocalDate.now());
        // noticeService.publishNotice(notice);

        notices.add(titleField.getText());
        statusLabel.setStyle("-fx-text-fill:green;");
        statusLabel.setText("Notice published.");
        titleField.clear();
        bodyArea.clear();
    }

    @FXML
    private void handleSelectNotice(MouseEvent event) {
        String selected = noticesListView.getSelectionModel().getSelectedItem();
        if (selected == null) return;
        // TODO: look up the real notice id + full body and populate the form fields
        titleField.setText(selected);
        statusLabel.setText("Loaded \"" + selected + "\" for editing.");
    }

    @FXML
    private void handleEditNotice(ActionEvent event) {
        if (selectedNoticeId == null && noticesListView.getSelectionModel().getSelectedItem() == null) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Select a notice from the list first.");
            return;
        }

        // TODO: noticeService.editNotice(selectedNoticeId, titleField.getText(), bodyArea.getText());

        statusLabel.setStyle("-fx-text-fill:green;");
        statusLabel.setText("Notice updated.");
    }

    @FXML
    private void handleDeleteNotice(ActionEvent event) {
        String selected = noticesListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Select a notice from the list first.");
            return;
        }

        // TODO: noticeService.deleteNotice(selectedNoticeId);

        notices.remove(selected);
        statusLabel.setStyle("-fx-text-fill:orange;");
        statusLabel.setText("Notice deleted.");
    }}