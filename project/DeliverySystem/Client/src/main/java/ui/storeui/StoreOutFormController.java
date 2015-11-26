package ui.storeui;

import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Created by Sissel on 2015/11/26.
 */
public class StoreOutFormController {

    public Label storeout_DatePicker;
    public TextField orderID_Field;
    public TextField destination_Field;
    public ChoiceBox packType_ChoiceBox;
    public TextField transitID_Field;
    public TableView transit_TableView;
    public TableView order_TableView;

    public void saveDraft(ActionEvent actionEvent) {
    }

    public void clear(ActionEvent actionEvent) {
    }

    public void commit(ActionEvent actionEvent) {
    }
}
