module c213.dosaoopproject {
    requires javafx.controls;
    requires javafx.fxml;

    // --- Teammate's Package Permissions ---
    opens c213.dosaoopproject.Fiha.controller.user_03 to javafx.fxml;
    opens c213.dosaoopproject.Fiha.controller to javafx.fxml;

    // --- Your Package Permissions (Bushra) ---
    exports c213.dosaoopproject.bushraController to javafx.graphics;
    opens c213.dosaoopproject.bushraController to javafx.fxml;

    // Root export
    exports c213.dosaoopproject;
}