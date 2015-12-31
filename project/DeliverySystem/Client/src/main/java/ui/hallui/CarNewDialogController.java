package ui.hallui;

import java.io.IOException;

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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CarNewDialogController {

	public TextField carID_Field;
	public TextField nameID_Field;
	public TextField engineID_Field;
	public TextField chassisID_Field;

	public DatePicker useTime_Picker;
	public DatePicker buyTime_Picker;

	private CarVO editVO = new CarVO();
	public Stage stage;
	
	private FormatCheckQueue formatCheckQueue;

	TimeConvert timeconvert = new TimeConvert();

	public static CarNewDialogController newDialog(CarVO editVO) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(CarNewDialogController.class.getResource("carNewDialog.fxml"));
		Pane pane = loader.load();

		Stage stage = new Stage();
		stage.initOwner(Main.primaryStage);
		stage.setScene(new Scene(pane));

		CarNewDialogController controller = (CarNewDialogController) loader.getController();

		controller.editVO = editVO;
		controller.stage = stage;
		controller.intit();
		

		return controller;

	}
	private void intit(){
		//init check
		formatCheckQueue=new FormatCheckQueue();
		formatCheckQueue.addTasker(
				new CheckCarIDTasker (carID_Field),
				new CheckIsNullTasker(chassisID_Field),
				new CheckIsNullTasker(engineID_Field),
				new CheckIsNullTasker(nameID_Field)
				);
	}
	

	@SuppressWarnings("static-access")
	public void ok(ActionEvent actionEvent) {
		//check
		if (!formatCheckQueue.startCheck()) {
			return;
		}

		editVO.setCarID(carID_Field.getText());
		editVO.setNameID(nameID_Field.getText());
		editVO.setChassisID(chassisID_Field.getText());
		editVO.setEngineID(engineID_Field.getText());
		editVO.setUseTime(timeconvert.convertDate(useTime_Picker.getValue()));
		editVO.setBuyTime(timeconvert.convertDate(buyTime_Picker.getValue()));

		stage.close();

	}

	public void cancel(ActionEvent actionEvent) {
		stage.close();
	}
}
