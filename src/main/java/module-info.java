module c213.dosaoopproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens c213.dosaoopproject to javafx.fxml;
    opens c213.dosaoopproject.fahmida to javafx.fxml;
    // TableView PropertyValueFactory reflects on model getters at runtime.
    opens c213.dosaoopproject.fahmida.model to javafx.base;

    exports c213.dosaoopproject;
}