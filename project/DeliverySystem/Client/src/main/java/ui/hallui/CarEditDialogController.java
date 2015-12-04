package ui.hallui;

import java.io.IOException;

import main.Main;
import vo.managevo.car.CarVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class CarEditDialogController {

	public TextField carID_Field;
	public TextField nameID_Field;
	public TextField useTime_Field;

	public static Label engineID_Label;
	public static Label chassisID_Label;
	public static Label buyTime_Label;

	private CarVO editVO =new CarVO();
	public Stage stage;

	public static CarEditDialogController newDialog(CarVO editVO) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(CarEditDialogController.class.getResource("carEditDialog.fxml"));
		Pane pane = loader.load();

		Stage stage = new Stage();
		stage.initOwner(Main.primaryStage);
		stage.setScene(new Scene(pane));

		CarEditDialogController controller = (CarEditDialogController)loader.getController();
		controller.editVO = editVO;
		controller.stage = stage;
		
//		engineID_Label.setText(editVO.getEngineID());
//		chassisID_Label.setText(editVO.getChassisID());
//		buyTime_Label.setText(editVO.getBuyTime().toString());
//		
		return controller;
		
	}



	public void ok(ActionEvent actionEvent){
		
		editVO.setCarID(carID_Field.getText());
		editVO.setNameID(nameID_Field.getText());
		//editVO.setUseTime(useTime_Field.getText());
		stage.close();
		
		
	}

	public void cancel(ActionEvent actionEvent){
		stage.close();
	}
}
