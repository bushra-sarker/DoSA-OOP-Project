module c213.dosaoopproject {
    requires javafx.controls;
    requires javafx.fxml;

    opens c213.dosaoopproject to javafx.fxml;
    opens c213.dosaoopproject.commonClass.controller to javafx.fxml;
    opens c213.dosaoopproject.commonClass.util to javafx.fxml;

    opens c213.dosaoopproject.Nahin.controller.u_03 to javafx.fxml;
    opens c213.dosaoopproject.Nahin.controller.u_04 to javafx.fxml;
    opens c213.dosaoopproject.Nahin.utility to javafx.fxml;

    opens c213.dosaoopproject.Nahin.model.u_04 to javafx.base;
    opens c213.dosaoopproject.Nahin.model.u_03 to javafx.base;

    exports c213.dosaoopproject;
}