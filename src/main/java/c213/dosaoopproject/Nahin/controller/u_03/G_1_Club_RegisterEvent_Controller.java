package c213.dosaoopproject.Nahin.controller.u_03;

import c213.dosaoopproject.Nahin.nonUser.AvailableEvents;
import c213.dosaoopproject.Nahin.model.u_03.ClubEventRegister;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static c213.dosaoopproject.Nahin.utility.FileManager.readFile;
import static c213.dosaoopproject.Nahin.utility.FileManager.writeFile;
import static c213.dosaoopproject.Nahin.utility.Navigation.navigate;
import static c213.dosaoopproject.Nahin.utility.VIA.*;
public class G_1_Club_RegisterEvent_Controller {
    @javafx.fxml.FXML
    private TextField userIDTF;
    @javafx.fxml.FXML
    private TextField mailTF;
    @javafx.fxml.FXML
    private TextField phoneTF;
    @javafx.fxml.FXML
    private TextField nameTF;
    @javafx.fxml.FXML
    private TextArea experienceTXTAR;
    @javafx.fxml.FXML
    private TableColumn venueCOL;
    @javafx.fxml.FXML
    private Label registerIDLABEL;
    @javafx.fxml.FXML
    private VBox pane_2;
    @javafx.fxml.FXML
    private VBox pane_1;
    @javafx.fxml.FXML
    private TableView<AvailableEvents> availableEventsTABLEVIEW;
    @javafx.fxml.FXML
    private TableColumn<AvailableEvents, LocalDate> dateCOL;
    @javafx.fxml.FXML
    private TableColumn<AvailableEvents, String> timeCOL;
    @javafx.fxml.FXML
    private TableColumn<AvailableEvents, String> eventNameCol;
    @javafx.fxml.FXML
    private TableColumn<AvailableEvents, String> clubNameCOL;
    @javafx.fxml.FXML
    private Label registrationDateLBL;
    @javafx.fxml.FXML
    private Label clubNameLBL;
    @javafx.fxml.FXML
    private Label eventNameLBL;
    @javafx.fxml.FXML
    private SideMenuBar_Controller nullController;

    @javafx.fxml.FXML
    public void initialize() {
        pane_2.setVisible(false);pane_2.setManaged(false);
        pane_1.setVisible(true);pane_1.setManaged(true);

        registrationDateLBL.setText(LocalDate.now().toString());

        clubNameCOL.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        eventNameCol.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        dateCOL.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
        venueCOL.setCellValueFactory(new PropertyValueFactory<>("venue"));
        timeCOL.setCellValueFactory(new PropertyValueFactory<>("time"));
    }

    @javafx.fxml.FXML
    public void refreshEventOA(ActionEvent actionEvent) {
        availableEventsTABLEVIEW.getItems().clear();
        loadAvailableEvents();
    }


    @javafx.fxml.FXML
    public void submitFormOA(ActionEvent actionEvent) {
        if (userIDTF.getText().isEmpty() || nameTF.getText().isEmpty() ||
                phoneTF.getText().isEmpty() || mailTF.getText().isEmpty()) {
            showWaitAlert(Alert.AlertType.ERROR, "Please fill up all required fields");
            return;
        }

        int registrationID = generateRegistrationId();

        ClubEventRegister eventRegister = new ClubEventRegister(
                registrationID,
                userIDTF.getText(),
                phoneTF.getText(),
                nameTF.getText(),
                mailTF.getText(),
                LocalDate.now(),
                clubNameLBL.getText(),
                eventNameLBL.getText(),
                experienceTXTAR.getText()
        );

        if (!eventRegister.validateRegistration()) {
            showAlert(Alert.AlertType.INFORMATION, "Invalid Information");
            return;
        }

        //read old data
        ArrayList<ClubEventRegister> eventList = readFile("AvailableEvents.bin");
        if(eventList==null){
            eventList = new ArrayList<>();
        }eventList.add(eventRegister);

        //save new data
        writeFile("AvailableEvents.bin",eventList);
        showAlert(Alert.AlertType.INFORMATION, "Submitted Successfully");
        System.out.println(eventList);


        userIDTF.clear();nameTF.clear();phoneTF.clear();mailTF.clear();experienceTXTAR.clear();
        //back to previous page
        pane_2.setVisible(false);pane_2.setManaged(false);
        pane_1.setVisible(true);pane_1.setManaged(true);
    }

    //pane2-->form
    @javafx.fxml.FXML
    public void clearOA(ActionEvent actionEvent) {
        userIDTF.clear();nameTF.clear();phoneTF.clear();mailTF.clear();experienceTXTAR.clear();
    }

    @javafx.fxml.FXML
    public void backToFirstOA(ActionEvent actionEvent) {
        pane_2.setVisible(false);pane_2.setManaged(false);
        pane_1.setVisible(true);pane_1.setManaged(true);
    }

    @javafx.fxml.FXML
    public void registerButtonOA(ActionEvent actionEvent) {
        AvailableEvents selectedEvent =  availableEventsTABLEVIEW.getSelectionModel().getSelectedItem();

        if(selectedEvent == null) {
            showWaitAlert(Alert.AlertType.ERROR, "Please select an event first");
            return;
        }
        clubNameLBL.setText(selectedEvent.getClubName());
        eventNameLBL.setText(selectedEvent.getEventName());
        registerIDLABEL.setText(String.valueOf(generateRegistrationId()));

        pane_1.setVisible(false);pane_1.setManaged(false);
        pane_2.setVisible(true);pane_2.setManaged(true);
    }

    @javafx.fxml.FXML
    public void backDashOA(ActionEvent actionEvent) throws IOException {
        navigate(actionEvent, "/Nahin/fxmlView/u3G1_Register_view.fxml");
    }

    public void loadAvailableEvents() {
        //reload published table data from the list/file
        ArrayList<AvailableEvents> eventList = new ArrayList<>();
        eventList.add(new AvailableEvents("IUB Debate Club", "Intra-University Debate Championship", "IUB Auditorium", LocalDate.of(2026, 9, 10), "10:00 am-4:00 pm"));
        eventList.add(new AvailableEvents("JUKTI", "Tech Workshop", "IUB Lecture Gallery", LocalDate.of(2026, 8, 15), "12:00 pm-4:00 pm"));
        eventList.add(new AvailableEvents("IUB Music Club", "Musical Evening", "IUB Auditorium", LocalDate.of(2026, 8, 15), "4:00 pm-8:00 pm"));
        eventList.add(new AvailableEvents("IUB Art Club", "Art & Sketch Exhibition", "IUB Cultural Centre", LocalDate.of(2026, 8, 18), "10:00 am-5:00 pm"));
        eventList.add(new AvailableEvents("IUB Dance Club", "Folk Dance Festival", "IUB Central Court", LocalDate.of(2026, 8, 12), "10:00 am-4:00 pm"));
        eventList.add(new AvailableEvents("IUB Theatre Club", "Short Drama Showcase", "IUB Lecture Gallery", LocalDate.of(2026, 9, 10), "3:00 pm-8:00 pm"));
        eventList.add(new AvailableEvents("IUB Photography Club", "Photography Exhibition", "IUB Student Activity Area", LocalDate.of(2026, 9, 1), "10:00 am-4:00 pm"));

        availableEventsTABLEVIEW.getItems().clear();
        if (eventList != null) {
            availableEventsTABLEVIEW.getItems().addAll(eventList);
        }
    }
}