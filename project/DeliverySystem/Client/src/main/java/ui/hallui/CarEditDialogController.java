package ui.hallui;

import java.io.IOException;
import java.time.LocalDate;

import main.Main;
import tool.time.TimeConvert;
import ui.common.checkFormat.FormatCheckQueue;
import ui.common.checkFormat.field.CheckCarIDTasker;
import ui.common.checkFormat.field.CheckIsNullTasker;
import ui.informui.InformController;
import vo.managevo.car.CarVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CarEditDialogController {

	public  TextField carID_Field;
	public  TextField nameID_Field;
	public  Label engineID_Label;
	public  Label chassisID_Label;
	public  Label buyTime_Label;
	public  DatePicker useTime_Picker;

	TimeConvert timeconvert = new TimeConvert();
	private CarVO editVO = new CarVO();
	public Stage stage;
	
	private FormatCheckQueue formatCheckQueue;


	public static CarEditDialogController newDialog(CarVO editVO) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(CarEditDialogController.class.getResource("carEditDialog.fxml"));
		Pane pane = loader.load();

		Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
		stage.initOwner(Main.primaryStage);
		stage.setScene(new Scene(pane));

		CarEditDialogController controller = (CarEditDialogController) loader.getController();

		controller.editVO = editVO;
		controller.stage = stage;
		controller.init();

		return controller;

	}

	private void init() {
		carID_Field.setText(editVO.getCarID());
		nameID_Field.setText(editVO.getNameID());
		engineID_Label.setText(editVO.getEngineID());
		chassisID_Label.setText(editVO.getChassisID());
		buyTime_Label.setText(timeconvert.getDisplayDate(editVO.getBuyTime()));
		useTime_Picker.setValue(timeconvert.convertCalendar(editVO.getUseTime()));
		//init check
		formatCheckQueue=new FormatCheckQueue();
		formatCheckQueue.addTasker(new CheckIsNullTasker(nameID_Field),
				new CheckCarIDTasker( carID_Field));

	}

	@SuppressWarnings("static-access")
	public void ok(ActionEvent actionEvent) {
		//check
		if (!formatCheckQueue.startCheck()) {
			return;
		}

		editVO.setCarID(carID_Field.getText());
		editVO.setNameID(nameID_Field.getText());
		editVO.setUseTime(timeconvert.convertDate(useTime_Picker.getValue()));
		stage.close();

	}

	public void cancel(ActionEvent actionEvent) {
		stage.close();
	}
}
