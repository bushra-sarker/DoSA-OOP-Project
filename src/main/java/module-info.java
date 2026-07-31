module c213.dosaoopproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;

    opens c213.dosaoopproject to javafx.fxml;
    opens c213.dosaoopproject.fahmida to javafx.fxml;

    exports c213.dosaoopproject;
}