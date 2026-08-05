package c213.dosaoopproject.fahmida;

import commonClass.User;
import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.Complaint;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.util.SceneManager;
import c213.dosaoopproject.fahmida.util.Ui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.time.LocalDate;

/**
 * User-1 Goal-6: Submit Complaints or Reports. Creates a {@link Complaint} from
 * the form and stores it, then returns to the dashboard.
 */
public class U1G6Controller {

    @javafx.fxml.FXML
    private TextField coomplaintsTitleTF;
    @javafx.fxml.FXML
    private ComboBox<String> complaitsCB;
    @javafx.fxml.FXML
    private TextField descriptionTF;
    @javafx.fxml.FXML
    private ComboBox<String> attachDocumentsCB;

    @javafx.fxml.FXML
    public void initialize() {
        complaitsCB.setItems(FXCollections.observableArrayList(
                "Academic", "Facilities", "Harassment", "Other"));
        attachDocumentsCB.setItems(FXCollections.observableArrayList("None", "Yes"));
    }

    @javafx.fxml.FXML
    public void submitComplaintsOA(ActionEvent actionEvent) {
        String category = complaitsCB.getValue();
        String title = coomplaintsTitleTF.getText();
        String description = descriptionTF.getText();

        // Validation: category + description required.
        if (category == null || description == null || description.isBlank()) {
            Ui.info("Please choose a category and enter the details.");
            return;
        }

        User user = Session.getCurrentUser();
        DataStore store = DataStore.get();
        String details = (title == null || title.isBlank() ? "" : title + ": ") + description;
        store.getComplaints().add(new Complaint(
                store.getComplaints().size() + 1, user.getUserId(),
                category, details, LocalDate.now()));
        store.logHistory(user.getUserId(), "Submitted a " + category + " complaint");
        Ui.info("Complaint submitted. Thank you.");
        SceneManager.switchTo("U1_Dashboard");
    }
}
