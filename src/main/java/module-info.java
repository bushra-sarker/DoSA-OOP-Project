module c213.dosaoopproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens c213.dosaoopproject.Fiha.controller.user_03 to javafx.fxml;
    opens c213.dosaoopproject.Fiha.controller to javafx.fxml;

    // 1. ALLOW JAVAFX FXML TO REFLECTIVELY ACCESS CONTROLLERS
    opens c213.dosaoopproject.bushraController to javafx.fxml;
    opens c213.dosaoopproject.commonClass to javafx.fxml;

    // 2. ALLOW TABLEVIEW TO READ YOUR MODEL PROPERTIES (If you use TableView in subviews)
    opens c213.dosaoopproject.bushraModel to javafx.base;

    // 3. EXPORT PACKAGES
    exports c213.dosaoopproject.bushraController;
    exports c213.dosaoopproject.commonClass;
}