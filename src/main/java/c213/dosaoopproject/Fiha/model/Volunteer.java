package c213.dosaoopproject.Fiha.model;

import c213.dosaoopproject.Application;
import commonClass.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Volunteer extends User {
    private String volID;
    private final String email;
    private int phone;

    public Volunteer(String fullName, int userId, String email, String volID, int phone) {
        super(fullName, userId);
        this.email = email;
        this.volID = volID;
        this.phone = phone;
    }

    @Override
    public void loadDashboard() {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("user_3_dashBoard_view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("DoSA Management Simulation");
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            e.getStackTrace();
        }

    }

    public String getVolID() {
        return volID;
    }

    public void setVolID(String volID) {
        this.volID = volID;
    }

    public String getEmail() {
        return email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
