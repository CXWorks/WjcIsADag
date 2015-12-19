package ui.informui;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import main.Main;

import java.io.IOException;

/**
 * Created by Sissel on 2015/12/19.
 */
public class InformController {
    public Label content_Label;

    public static void newInform(String content) throws IOException {
        FXMLLoader loader = new FXMLLoader(InformController.class.getResource("inform.fxml"));
        Pane pane = loader.load();
        InformController controller = loader.getController();
        controller.content_Label.setText(content);


    }
}
