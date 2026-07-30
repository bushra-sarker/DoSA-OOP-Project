module c213.dosaoopproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires javafx.base;
    requires javafx.graphics;

    // --- Main Application Entry Point ---
    exports c213.dosaoopproject;
    opens c213.dosaoopproject to javafx.fxml;

    // --- Common Architecture Packages ---
    exports commonClass.model;
    opens commonClass.model to javafx.base;

    exports commonClass.controller;
    opens commonClass.controller to javafx.fxml;

    exports commonClass.util;
    opens commonClass.util to javafx.fxml;

    exports commonClass.data;

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
}