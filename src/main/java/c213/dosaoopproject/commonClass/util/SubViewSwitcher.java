package c213.dosaoopproject.commonClass.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class SubViewSwitcher {
    public static void loadSubView(AnchorPane contentArea, String fxmlPath) {
        try {
            Node node = FXMLLoader.load(SubViewSwitcher.class.getResource(fxmlPath));
            contentArea.getChildren().clear();
            contentArea.getChildren().add(node);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}