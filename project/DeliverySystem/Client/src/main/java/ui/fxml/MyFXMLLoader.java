package ui.fxml;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * Created by Sissel on 2015/12/5.
 */
public class MyFXMLLoader {

    public static Pane load(String fileName) throws IOException {
        return FXMLLoader.load(MyFXMLLoader.class.getResource(fileName));
    }

}
