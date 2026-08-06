module c213.DoSA.OOP.Project {
    requires javafx.controls;
    requires javafx.fxml;

    // Main application
    exports c213.dosaoopproject;
    opens c213.dosaoopproject to javafx.graphics, javafx.fxml;

    // Common shared packages
    exports c213.dosaoopproject.commonClass.controller;
    opens c213.dosaoopproject.commonClass.controller to javafx.fxml;

    // Bushra U07 packages
    exports c213.dosaoopproject.Bushra.U07.controller;
    opens c213.dosaoopproject.Bushra.U07.controller to javafx.fxml;

    exports c213.dosaoopproject.Bushra.U07.model;
    opens c213.dosaoopproject.Bushra.U07.model to javafx.base, javafx.fxml;
    exports c213.dosaoopproject.Bushra.U07.util;
    opens c213.dosaoopproject.Bushra.U07.util to javafx.base, javafx.fxml;
}