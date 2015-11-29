package ui.financeui;

import bl.blService.logblService.LogblService;
import factory.LogBLFactory;
import factory.LoginFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Created by Sissel on 2015/11/27.
 */
public class CheckLogController {
    public TableView log_TableView;
    public DatePicker begin_DatePicker;
    public DatePicker end_DatePicker;
    public TextField keyword_Field;
    public TableColumn time_TableColumn;
    public TableColumn operator_TableColumn;
    public TableColumn description_TableColumn;

    private LogblService logblService = LogBLFactory.getLogblService();

    public static Parent launch() throws IOException {
        return FXMLLoader.load(CheckLogController.class.getResource("checkLog.fxml"));
    }

    @FXML
    public void initialize(){
        
    }

    @FXML
    public void exportTXT(ActionEvent actionEvent) {
    }

    @FXML
    public void search(ActionEvent actionEvent) {

    }
}
