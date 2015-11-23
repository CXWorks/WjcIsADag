package ui.common;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Created by Sissel on 2015/11/21.
 */
public class FormBridge {
    public Button ok_Btn;
    BasicFormController controller;

    @FXML
    public void initialize(){
        System.out.println("here");
        ok_Btn.requestFocus();
    }

    @FXML
    public void clear(ActionEvent actionEvent) {
        controller.clear(actionEvent);
        ok_Btn.requestFocus();
    }

    public void saveDraft(ActionEvent actionEvent) {
        controller.saveDraft(actionEvent);
    }

    public void commit(ActionEvent actionEvent) {
        controller.commit(actionEvent);
    }

    public void setController(BasicFormController controller) {
        this.controller = controller;
    }
}
