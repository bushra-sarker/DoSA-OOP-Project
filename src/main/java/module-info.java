module c213.dosaoopproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens c213.dosaoopproject.controller.user_03 to javafx.fxml;
    exports c213.dosaoopproject;
}