package ui.transportui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;

/**
 * Created by Sissel on 2015/11/23.
 * 这个类负责中转中心和营业厅的装车单，虽然文档上有一个区别，但是多的东西并无卵用，所以我合起来了
 */
public class LoadCarController {

    public TextField transitCarID_Field;
    public DatePicker date_Picker;
    public TextField monitor_Field;
    public TextField guard_Field;
    public Label date_errLabel;
    public Label fee_Label;
    public ListView orders_ListView;
    public ChoiceBox arrival_ChoiceBox;
    public ChoiceBox carID_ChoiceBox;

    public static Parent launch() throws IOException {
        return FXMLLoader.load(LoadCarController.class.getResource("loadCarForm.fxml"));
    }

    @FXML
    public void initialize(){
        // TODO init arrival_ChoiceBox


    }


    public void saveDraft(ActionEvent actionEvent) {
    }

    public void clear(ActionEvent actionEvent) {

    }

    public void sure(ActionEvent actionEvent) {

    }
}
