package ui.common;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Created by Sissel on 2015/11/21.
 */
public class FormBridge {
    BasicFormController controller;

    @FXML
    public void clear(ActionEvent actionEvent) {
        controller.clear(actionEvent);
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
