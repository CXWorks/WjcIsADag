package ui.financeui;

import bl.tool.time.TimeConvert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Calendar;

/**
 * Created by Sissel on 2015/11/23.
 */
public class CheckRevenueFormController {

    public Label sum_Label;
    public TableView form_Table;
    public DatePicker revenue_DatePicker;
    public TextField hall_textField;
    public Button search_Btn;

    @FXML
    public void initialize(){

    }

    public void search(ActionEvent actionEvent) {
        Calendar calendar = TimeConvert.convertDate(revenue_DatePicker.getValue());
        // TODO check
        String hallID = hall_textField.getText();
        // TODO check


    }
}
