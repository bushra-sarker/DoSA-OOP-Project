module c213.dosaoopproject {
    requires javafx.controls;
    requires javafx.fxml;

    opens c213.dosaoopproject to javafx.fxml;
    exports c213.dosaoopproject;

    opens c213.dosaoopproject.esha.controller to javafx.fxml;
    exports c213.dosaoopproject.esha.controller;

    opens c213.dosaoopproject.esha.model to javafx.fxml;
    exports c213.dosaoopproject.esha.model;

    exports bushra;
    exports commonClass;
}