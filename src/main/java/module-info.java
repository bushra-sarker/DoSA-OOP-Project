module c213.dosaoopproject {
    requires javafx.controls;
    requires javafx.fxml;

    // Export & Open Root Packages
    exports c213.dosaoopproject;
    opens c213.dosaoopproject to javafx.fxml;

    // Fiha's project part
    exports c213.dosaoopproject.Nahin.controller.u_03;
    opens c213.dosaoopproject.Nahin.controller.u_03 to javafx.fxml;

    // Export & Open Common Classes
    exports commonClass;
    opens commonClass to javafx.fxml;

    // Export & Open Bushra's Controllers
    exports c213.dosaoopproject.Bushra.bushraController;
    opens c213.dosaoopproject.Bushra.bushraController to javafx.fxml;

    exports c213.dosaoopproject.Bushra.bushraController.U07;
    opens c213.dosaoopproject.Bushra.bushraController.U07 to javafx.fxml;

    exports c213.dosaoopproject.Bushra.bushraController.U08;
    opens c213.dosaoopproject.Bushra.bushraController.U08 to javafx.fxml;

    // Export Bushra's Models
    exports c213.dosaoopproject.Bushra.bushraModel.U07;
    exports c213.dosaoopproject.Bushra.bushraModel.U08;
}