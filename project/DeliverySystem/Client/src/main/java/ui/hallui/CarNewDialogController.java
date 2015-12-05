package ui.hallui;

import java.io.IOException;

import main.Main;
import vo.managevo.car.CarVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class CarNewDialogController {

	public TextField carID_Field;
	public TextField nameID_Field;
	public static TextField engineID_Field;
	public static TextField chassisID_Field;

	public DatePicker useTime_Picker;
	public static DatePicker buyTime_Picker;

	private CarVO editVO =new CarVO();
	public Stage stage;

	public static CarNewDialogController newDialog(CarVO editVO) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(CarNewDialogController.class.getResource("carNewDialog.fxml"));
		Pane pane = loader.load();

		Stage stage = new Stage();
		stage.initOwner(Main.primaryStage);
		stage.setScene(new Scene(pane));

		CarNewDialogController controller = (CarNewDialogController)loader.getController();
		controller.editVO = editVO;
		controller.stage = stage;
		

		return controller;
		
	}



	public void ok(ActionEvent actionEvent){
		
		editVO.setCarID(carID_Field.getText());
		editVO.setNameID(nameID_Field.getText());
		editVO.setChassisID(chassisID_Field.getText());
		editVO.setEngineID(engineID_Field.getText());
//		editVO.setUseTime(useTime_Picker.getValue());
//		editVO.setBuyTime(buyTime_Picker.getValue());
		
		stage.close();
		
		
	}

	public void cancel(ActionEvent actionEvent){
		stage.close();
	}
}
