package commonClass.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class SubViewSwitcher {

//     Loads an FXML file and replaces the content of the specified StackPane container.
//     container The StackPane host where the view should be displayed.
//     fxmlPath  The absolute path to the subview FXML resource.

    public static <T> T loadSubView(StackPane container, String fxmlPath) {
        if (container == null) {
            System.err.println("SubViewSwitcher Error: Container StackPane is null.");
            return null;
        }

        try {
            FXMLLoader loader = new FXMLLoader(SubViewSwitcher.class.getResource(fxmlPath));
            Parent view = loader.load();

            container.getChildren().clear();
            container.getChildren().add(view);

            return loader.getController();
        } catch (IOException e) {
            System.err.println("SubViewSwitcher Error: Could not load FXML at path " + fxmlPath);
            e.printStackTrace();
            return null;
        }
    }


    public static void clearContainer(StackPane container) {
        if (container != null) {
            container.getChildren().clear();
        }
    }
}