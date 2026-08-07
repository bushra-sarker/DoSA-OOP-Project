module c213.dosaoopproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens c213.dosaoopproject.Nahin.controller.u_03 to javafx.fxml;
    opens c213.dosaoopproject.Nahin.controller.u_04 to javafx.fxml;
//    opens c213.dosaoopproject.Nahin.controller to javafx.fxml;
    opens c213.dosaoopproject.Nahin.model.u_03 to javafx.base;
    opens c213.dosaoopproject.Nahin.model.u_04 to javafx.base;
    opens c213.dosaoopproject.Nahin.commonClass to javafx.base;
    opens c213.dosaoopproject.Nahin.utility to javafx.base;
    exports c213.dosaoopproject;
    opens c213.dosaoopproject.Nahin.nonUser to javafx.base;
}