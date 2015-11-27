package ui.transportui;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

/**
 * Created by Sissel on 2015/11/27.
 */
public class TransitFormController {
    public Label transNumber_Label;
    public Label cargo_Label;
    public ChoiceBox transitType_ChoiceBox;
    public DatePicker transit_DatePicker;
    public TextField departure_Field;
    public TextField arrival_Field;
    public TextField supervisor_Field;
    public TextField transNumber_Field;
    public TextField cargo_Field;
    public TableView orders_TableView;
    public TableColumn order_TableColumn;
    public Label fee_Label;

    public void saveDraft(ActionEvent actionEvent) {
    }

    public void clear(ActionEvent actionEvent) {
    }

    public void commit(ActionEvent actionEvent) {

    }
}
