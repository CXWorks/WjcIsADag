package ui.financeui;

import bl.blService.logblService.LogblService;
import factory.LogBLFactory;
import factory.LoginFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import tool.time.TimeConvert;
import vo.systemvo.LogVO;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Sissel on 2015/11/27.
 */
public class CheckLogController {
    private List<LogVO> logVOs;

    public TableView<LogVO> log_TableView;
    public DatePicker begin_DatePicker;
    public DatePicker end_DatePicker;
    public TextField keyword_Field;
    public TableColumn<LogVO, String> time_TableColumn;
    public TableColumn<LogVO, String> operator_TableColumn;
    public TableColumn<LogVO, String> description_TableColumn;

    private LogblService logblService = LogBLFactory.getLogblService();

    public static Parent launch() throws IOException {
        return FXMLLoader.load(CheckLogController.class.getResource("checkLog.fxml"));
    }

    @FXML
    public void exportTXT(ActionEvent actionEvent) {
        // TODO file browser
        logblService.exportToTXT("path");
    }

    @FXML
    public void search(ActionEvent actionEvent) {
        Calendar begin = TimeConvert.convertDate(begin_DatePicker.getValue());
        Calendar end = TimeConvert.convertDate(end_DatePicker.getValue());

        logVOs.clear();
        logVOs.addAll(logblService.dateSearch(begin, end));
        log_TableView.getItems().clear();
        log_TableView.getItems().addAll(logVOs);
    }

    @FXML
    public void initialize(){
        time_TableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(TimeConvert.getDisplayDate(cellData.getValue().time))
        );
        operator_TableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().personID)
        );
        description_TableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().info)
        );
    }
}
