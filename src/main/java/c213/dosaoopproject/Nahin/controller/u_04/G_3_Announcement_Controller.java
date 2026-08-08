package c213.dosaoopproject.Nahin.controller.u_04;

import c213.dosaoopproject.Nahin.model.u_04.Announcement;
import c213.dosaoopproject.Nahin.utility.FileManager;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.ArrayList;

import static c213.dosaoopproject.Nahin.utility.FileManager.readFile;
import static c213.dosaoopproject.Nahin.utility.FileManager.writeFile;
import static c213.dosaoopproject.Nahin.utility.IdGenerator.generateRegistrationId;
import static c213.dosaoopproject.Nahin.utility.Navigation.navigate;
import static c213.dosaoopproject.Nahin.utility.VIA.showAlert;

public class G_3_Announcement_Controller {
    @javafx.fxml.FXML
    private ComboBox<String> categoryCOMBO;
    @javafx.fxml.FXML
    private TextArea detailsTXTAR;
    @javafx.fxml.FXML
    private DatePicker datePublishDP;
    @javafx.fxml.FXML
    private TextField titleTF;

    @javafx.fxml.FXML
    public void initialize() {
        categoryCOMBO.getItems().addAll("General", "Academic", "Event", "SuspendClub", "Emergency");
    }

    @javafx.fxml.FXML
    public void publishOA(ActionEvent actionEvent) throws IOException {

        if (titleTF.getText().isEmpty() || categoryCOMBO.getValue() == null ||
                detailsTXTAR.getText().isEmpty() || datePublishDP.getValue() == null) {
            showAlert(Alert.AlertType.WARNING, "Please fill up all fields");
            return;
        }

        int announcementId = generateRegistrationId();
        Announcement announcement = new Announcement(
                announcementId,
                titleTF.getText(),
                categoryCOMBO.getValue(),
                detailsTXTAR.getText(),
                datePublishDP.getValue()
        );

        if (!announcement.validateInfo()) {
            showAlert(Alert.AlertType.WARNING, "Invalid information");
            return;
        }

        ArrayList<Announcement> list = readFile("Announcements.bin");
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(announcement);

        writeFile("Announcements.bin", list);
        System.out.println(list);

        showAlert(Alert.AlertType.CONFIRMATION, "Announcement published successfully");

        //auto navigate to history page
        navigate(actionEvent, "/Nahin/fxmlView/u4_G3_announcements_view.fxml");

        titleTF.clear();categoryCOMBO.setValue(null);detailsTXTAR.clear();datePublishDP.setValue(null);
    }


    //back to dashBoard
    @javafx.fxml.FXML
    public void cancelOA(ActionEvent actionEvent) throws IOException{
        navigate(actionEvent, "/Nahin/fxmlView/u4_dashBoard.fxml");
    }

}