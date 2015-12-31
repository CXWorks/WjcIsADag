package ui.hallui;

import java.io.IOException;
import java.time.LocalDate;

import po.memberdata.SexEnum;
import po.orderdata.DeliverTypeEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Main;
import tool.time.TimeConvert;
import tool.ui.Enum2ObservableList;
import tool.ui.SimpleEnumProperty;
import ui.common.checkFormat.FormatCheckQueue;
import ui.common.checkFormat.field.CheckIsNullTasker;
import ui.common.checkFormat.field.CheckPhoneTasker;
import ui.informui.InformController;
import vo.managevo.staff.DriverVO;

public class DriverEditDialogController {

	public TextField ID_Field;
	public TextField name_Field;
	public TextField personID_Field;
	public TextField tel_Field;
	public TextField carUnit_Field;
	public DatePicker birth_Picker;
	public DatePicker licencePeriod_Picker;

	public ChoiceBox<SimpleEnumProperty<SexEnum>> sex_Box;

	public static Label age_Label;

	private SexEnum sexEnum = SexEnum.MAN;
	private DriverVO editVO =new DriverVO();
	public Stage stage;
	
	private FormatCheckQueue formatCheckQueue;
	
	TimeConvert timeconvert = new TimeConvert();

	public static DriverEditDialogController newDialog(DriverVO editVO) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(DriverEditDialogController.class.getResource("driverEditDialog.fxml"));
		Pane pane = loader.load();

		Stage stage = new Stage();
		stage.initOwner(Main.primaryStage);
		stage.setScene(new Scene(pane));

		DriverEditDialogController controller = (DriverEditDialogController)loader.getController();

		controller.editVO = editVO;
		controller.stage = stage;
		controller.init();


		return controller;

	}

    private void init() {

    	sexEnum=editVO.getSex();

    	sex_Box.setItems(
                Enum2ObservableList.transit(SexEnum.values())
        );
    	sex_Box.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> {
					sexEnum = newValue.getEnum();
                }
		);

    	ID_Field.setText(editVO.getID());
    	name_Field.setText(editVO.getName());
    	personID_Field.setText(editVO.getPersonID());
    	tel_Field.setText(editVO.getTel());
    	carUnit_Field.setText(editVO.getInstitutionID());
    	birth_Picker.setValue(timeconvert.convertCalendar(editVO.getBirth()));
    	licencePeriod_Picker.setValue(timeconvert.convertCalendar(editVO.getLicence_period()));
    	sex_Box.getSelectionModel().clearAndSelect(editVO.getSex().getNum());
    	
    	//init check
    	formatCheckQueue=new FormatCheckQueue();
    	formatCheckQueue.addTasker(
    			new CheckIsNullTasker(ID_Field),
    			new CheckIsNullTasker(carUnit_Field),
    			new CheckIsNullTasker(name_Field),
    			new CheckIsNullTasker(personID_Field),
    			new CheckPhoneTasker(tel_Field)
    			);
	}



	@SuppressWarnings("static-access")
	public void ok(ActionEvent actionEvent){
		//check
		if (!formatCheckQueue.startCheck()) {
			return;
		}
		//TODO solve sex and Picker
		editVO.setID(ID_Field.getText());
		editVO.setName(name_Field.getText());
		editVO.setSex(sexEnum);
		editVO.setBirth(timeconvert.convertDate(birth_Picker.getValue()));
		editVO.setPersonID(personID_Field.getText());
		editVO.setTel(tel_Field.getText());
		editVO.setLicence_period(timeconvert.convertDate(licencePeriod_Picker.getValue()));
		
		
		stage.close();
	}

	public void age(ActionEvent actionEvent){
		int age=LocalDate.now().getYear()-birth_Picker.getValue().getYear();
		age_Label.setText(age+"");
	}
	
	public void cancel(ActionEvent actionEvent){
		stage.close();
	}
}
