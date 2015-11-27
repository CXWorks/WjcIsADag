package ui.financeui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;

import java.io.IOException;

/**
 * Created by Sissel on 2015/11/27.
 */
public class NewInitController {
    public Label systemstate_Label;

    public static Parent launch() throws IOException {
        return FXMLLoader.load(NewInitController.class.getResource("newInit.fxml"));
    }

    public void manageStore(ActionEvent actionEvent) {
    }

    public void manageAccount(ActionEvent actionEvent) {
    }

    public void manageInstitution(ActionEvent actionEvent) {
    }

    public void manageCars(ActionEvent actionEvent) {
    }

    public void manageStaff(ActionEvent actionEvent) {
    }

    public void saveDraft(ActionEvent actionEvent) {
    }

    public void clear(ActionEvent actionEvent) {
    }

    public void commit(ActionEvent actionEvent) {

    }
}
