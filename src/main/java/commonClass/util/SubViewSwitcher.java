package commonClass.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import java.io.IOException;

public class SubViewSwitcher {

//     Swaps the content inside a container Pane: (e.g., center Pane of main layout).
//     container The target Pane: (VBox, StackPane, BorderPane center, etc.)
//     Full path to resource: e.g., "/c213/dosaoopproject/Bushra/U08/U08G8_feedbackAnalytics.fxml"
//     controller attached to the loaded view (optional)

    public static Object loadSubView(Pane container, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(SubViewSwitcher.class.getResource(fxmlPath));
            Node subView = loader.load();
            container.getChildren().clear();
            container.getChildren().add(subView);
            return loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
            AlertUtil.showError("UI Navigation Error", "Unable to load sub-view: " + fxmlPath);
            return null;
        }
    }
}