module c213.dosaoopproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;


    opens c213.dosaoopproject.Nahin.controller.u_03 to javafx.fxml;
    opens c213.dosaoopproject.Nahin.controller to javafx.fxml;
    exports c213.dosaoopproject;
    opens c213.dosaoopproject to javafx.fxml;

    // --- Common Package ---
    exports commonClass;
    opens commonClass to javafx.fxml;

    // --- Bushra: Controllers ---
    exports c213.dosaoopproject.Bushra.Controller.U07;
    opens c213.dosaoopproject.Bushra.Controller.U07 to javafx.fxml;

    exports c213.dosaoopproject.Bushra.Controller.U08;
    opens c213.dosaoopproject.Bushra.Controller.U08 to javafx.fxml;

    // --- Bushra: Models ---
    exports c213.dosaoopproject.Bushra.Model.U07;
    opens c213.dosaoopproject.Bushra.Model.U07 to javafx.base;

    exports c213.dosaoopproject.Bushra.Model.U08;
    opens c213.dosaoopproject.Bushra.Model.U08 to javafx.base;

    // --- Nahin: Controllers & Models ---
    exports c213.dosaoopproject.Nahin.controller.u_03;
    opens c213.dosaoopproject.Nahin.controller.u_03 to javafx.fxml;

    exports c213.dosaoopproject.Nahin.model;
    opens c213.dosaoopproject.Nahin.model to javafx.base;
}