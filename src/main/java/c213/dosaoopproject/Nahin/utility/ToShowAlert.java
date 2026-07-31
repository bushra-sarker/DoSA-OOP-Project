package c213.dosaoopproject.Nahin.utility;

import javafx.scene.control.Alert;

public class ToShowAlert {
     public static void showWaitAlert(Alert.AlertType type, String msg){
        Alert a = new Alert(type);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }

    public static void showAlert(Alert.AlertType type, String msg){
        Alert a = new Alert(type);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.show();
    }
}
