package ui.financeui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.io.IOException;

/**
 * Created by Sissel on 2015/11/27.
 */
public class CheckInitInfoController {


    public TableView info_TableView;
    public Label systemstate_Label;
    public ChoiceBox InitType_ChoiceBox;

    public static Parent launch() throws IOException {
        return FXMLLoader.load(CheckInitInfoController.class.getResource("checkInitInfo.fxml"));
    }

    public void applyForInitialization(ActionEvent actionEvent) {
    }
}
