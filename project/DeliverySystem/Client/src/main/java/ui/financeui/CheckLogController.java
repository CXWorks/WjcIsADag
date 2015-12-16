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
import javafx.stage.FileChooser;
import main.Main;
import tool.time.TimeConvert;
import vo.systemvo.LogVO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Sissel on 2015/11/27.
 */
public class CheckLogController {
    private List<LogVO> logVOs = new ArrayList<>();

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
        // TODO 记下用户上次存文件的位置
        FileChooser fileChooser = new FileChooser();
        //fileChooser.setInitialDirectory(new File("g:/develop"));
        
        fileChooser.setInitialFileName("Log--"
                + TimeConvert.getDisplayDate(begin_DatePicker.getValue())
                + "--"
                + TimeConvert.getDisplayDate(end_DatePicker.getValue())
                +".txt");
        File file = fileChooser.showSaveDialog(Main.primaryStage);

        if(file != null){
            logblService.exportToTXT(file.getAbsolutePath());
        }
    }

    @FXML
    public void search(ActionEvent actionEvent) {
        Calendar begin = TimeConvert.convertDate(begin_DatePicker.getValue());
        Calendar end = TimeConvert.convertDate(end_DatePicker.getValue());
        if(logVOs!=null)
        	logVOs.clear();
        else {
			logVOs = new ArrayList<>();
		}
        logVOs.addAll(logblService.search(keyword_Field.getText(), begin, end));
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
