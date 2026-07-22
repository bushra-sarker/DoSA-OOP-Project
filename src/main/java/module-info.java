module c213.dosaoopproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens c213.dosaoopproject.Fiha.controller.user_03 to javafx.fxml;
    opens c213.dosaoopproject.Fiha.controller to javafx.fxml;
    exports c213.dosaoopproject;
}