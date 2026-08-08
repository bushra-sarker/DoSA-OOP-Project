package c213.dosaoopproject.Nahin.controller.u_04;

import c213.dosaoopproject.Nahin.model.u_04.Announcement;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import static c213.dosaoopproject.Nahin.utility.FileManager.readFile;
import static c213.dosaoopproject.Nahin.utility.Navigation.navigate;

public class G_3_Announcement_History_Controller
{
    @javafx.fxml.FXML
    private TableColumn<Announcement,String> titleCOL;
    @javafx.fxml.FXML
    private TableColumn<Announcement,Integer> announcementIdCOL;
    @javafx.fxml.FXML
    private TableView<Announcement>historyTABLEVIEW;
    @javafx.fxml.FXML
    private TableColumn<Announcement,LocalDate> publishedDATECOL;
    @javafx.fxml.FXML
    private SideMenuBar_Controller nullController;

    @javafx.fxml.FXML
    public void initialize() {
        announcementIdCOL.setCellValueFactory(new PropertyValueFactory<>("announcementId"));
        titleCOL.setCellValueFactory(new PropertyValueFactory<>("title"));
        publishedDATECOL.setCellValueFactory(new PropertyValueFactory<>("publishDate"));

        loadHistory();
    }

    private void loadHistory(){
        ArrayList<Announcement> list = readFile("Announcements.bin");
        historyTABLEVIEW.getItems().clear();

        if (list != null) {
            historyTABLEVIEW.getItems().addAll(list);
        }
    }


    @javafx.fxml.FXML
    public void refreshOA(ActionEvent actionEvent) {
        historyTABLEVIEW.getItems().clear();
        loadHistory();
    }


    @javafx.fxml.FXML
    public void backOA(ActionEvent actionEvent) throws IOException {
        navigate(actionEvent, "/Nahin/fxmlView/u4_dashBoard.fxml");
    }
}