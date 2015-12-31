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
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import main.Main;
import tool.time.TimeConvert;
import ui.hallui.RevenueFormController;
import ui.informui.InformController;
import userinfo.UserInfo;
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

	private InformController informController;

	public static Parent launch() throws IOException {
		FXMLLoader loader = new FXMLLoader(CheckLogController.class.getResource("checkLog.fxml"));
		Pane pane = loader.load();
		CheckLogController controller = loader.getController();
		controller.informController = InformController.newInformController(pane);

		return controller.informController.stackPane;
	}

	@FXML
	public void exportTXT(ActionEvent actionEvent) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File(UserInfo.getWorkPath()));

		fileChooser.setInitialFileName("Log--" + TimeConvert.getDisplayDate(begin_DatePicker.getValue()) + "--"
				+ TimeConvert.getDisplayDate(end_DatePicker.getValue()) + ".txt");
		File file = fileChooser.showSaveDialog(Main.primaryStage);

		if (file != null) {
			logblService.exportToTXT(file.getAbsolutePath());
		}
	}

	@FXML
	public void search(ActionEvent actionEvent) {
		Calendar begin = TimeConvert.convertDate(begin_DatePicker.getValue());
		Calendar end = TimeConvert.convertDate(end_DatePicker.getValue());
		if (logVOs != null)
			logVOs.clear();
		else {
			logVOs = new ArrayList<>();
		}
		logVOs.addAll(logblService.search(keyword_Field.getText(), begin, end));
		log_TableView.getItems().clear();
		log_TableView.getItems().addAll(logVOs);
	}

	@FXML
	public void initialize() {
		time_TableColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty(TimeConvert.getDisplayDate(cellData.getValue().time)));
		operator_TableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().personID));
		description_TableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().info));
	}
}
