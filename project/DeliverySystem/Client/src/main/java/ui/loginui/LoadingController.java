package ui.loginui;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import ui.css.pic.ForImage;

import java.io.IOException;

/**
 * Created by Sissel on 2016/1/4.
 */
public class LoadingController {
    public ImageView gif_ImageView;

    public static Pane launch() {
        FXMLLoader loader = new FXMLLoader(LoadingController.class.getResource("loading.fxml"));
        try {
            Pane pane = loader.load();
            LoadingController controller = loader.getController();
            controller.gif_ImageView.setImage(new Image(ForImage.class.getResource("loading.gif").toString()));
            return pane;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
