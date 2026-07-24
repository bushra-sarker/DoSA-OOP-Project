module c213.dosaoopproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens c213.dosaoopproject.Nahin.controller.u_03 to javafx.fxml;
    opens c213.dosaoopproject.Nahin.controller to javafx.fxml;
    exports c213.dosaoopproject;
}