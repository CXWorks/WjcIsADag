package ui.transportui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import message.OperationMessage;
import factory.FormFactory;
import bl.blService.transportblService.TransportCenterBLService;
import po.orderdata.DeliverTypeEnum;
import po.receivedata.StateEnum;
import po.transportdata.TransportationEnum;
import tool.time.TimeConvert;
import tool.ui.Enum2ObservableList;
import tool.ui.SimpleEnumProperty;
import ui.hallui.RevenueFormController;
import ui.informui.InformController;
import ui.receiveui.ReceiveFormController;
import userinfo.UserInfo;
import vo.managevo.institution.InstitutionVO;
import vo.receivevo.ReceiveVO;
import vo.transitvo.CenterOutVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

/**
 * Created by Sissel on 2015/11/27.
 */
public class TransitFormController {
	public Label transNumber_Label;
	public Label cargo_Label;
	public ChoiceBox<SimpleEnumProperty<TransportationEnum>> transitType_ChoiceBox;
	public DatePicker transit_DatePicker;
	public TextField departure_Field;
	public ChoiceBox<String> arrival_Box;
	public TextField supervisor_Field;
	public TextField transNumber_Field;
	public TextField cargo_Field;
	public ListView<String> orders_ListView;
	public Label fee_Label;
	public TextField id_Field;

	int fee = 0;
	ArrayList<String> ids = new ArrayList<String>();

	TransportationEnum transitEnum = TransportationEnum.TRAIN;
	TransportCenterBLService transportCenterBLService = FormFactory.getTransportCenterBLService();

	ArrayList<String> arrivals = transportCenterBLService.getLocation(UserInfo.getInstitutionID());

	private InformController informController;

	public static Parent launch() throws IOException {
		FXMLLoader loader = new FXMLLoader(TransitFormController.class.getResource("transitForm.fxml"));
		Pane pane = loader.load();
		TransitFormController controller = loader.getController();
		controller.informController = InformController.newInformController(pane);

		return controller.informController.stackPane;
	}

	@FXML
	public void initialize() {
		transit_DatePicker.setValue(LocalDate.now());
		departure_Field.setText(UserInfo.getInstitutionID());
		// initialize the choice box
		transitType_ChoiceBox.setItems(Enum2ObservableList.transit(TransportationEnum.values()));
		transitType_ChoiceBox.setValue(transitType_ChoiceBox.getItems().get(0));
		transitType_ChoiceBox.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> {
					transitEnum = newValue.getEnum();
					if(transitEnum==TransportationEnum.TRAIN){
						transNumber_Label.setText("车次号");
						cargo_Label.setText("车厢号");
					}
					if(transitEnum==TransportationEnum.PLANE){
						transNumber_Label.setText("航班号");
						cargo_Label.setText("货柜号");
					}
					if(transitEnum==TransportationEnum.CAR){
						transNumber_Label.setText("车次号");
						cargo_Label.setText("押运员");
					}
				});

		arrival_Box.setItems(FXCollections.observableArrayList(arrivals));
		if(arrival_Box.getItems().size() != 0){
			arrival_Box.setValue(arrival_Box.getItems().get(0));
		}
	}

	public void add(ActionEvent actionEvent) {
		String a = id_Field.getText();
		System.out.println("add" + a);
		ids.add(a);
		orders_ListView.getItems().add(a);
		id_Field.clear();
		// fee+=
	}

	public void saveDraft(ActionEvent actionEvent) {
		transportCenterBLService.saveDraft(generateVO(null));
		clear(null);

	}

	public void clear(ActionEvent actionEvent) {
		transit_DatePicker.setValue(LocalDate.now());
		transitType_ChoiceBox.setValue(transitType_ChoiceBox.getItems().get(0));
		departure_Field.clear();
		if(arrival_Box.getItems().size() != 0){
			arrival_Box.setValue(arrival_Box.getItems().get(0));
		}

		supervisor_Field.clear();
		transNumber_Field.clear();
		cargo_Field.clear();
		id_Field.clear();
		ids.clear();
		orders_ListView.getItems().clear();
	}

	private CenterOutVO generateVO(String formID) {
		Calendar calendar = TimeConvert.convertDate(transit_DatePicker.getValue());
		return new CenterOutVO(formID, departure_Field.getText(), transNumber_Field.getText(), cargo_Field.getText(),
				calendar, fee_Label.getText(), arrival_Box.getValue(), supervisor_Field.getText(), ids, transitEnum,
				UserInfo.getUserID());
	}

	public void commit(ActionEvent actionEvent) {
		OperationMessage msg = transportCenterBLService.submit(generateVO(transportCenterBLService.newID()));
		ids.clear();
		// orders_ListView=new ListView<>();
		orders_ListView.getItems().clear();
		if (msg.operationResult) {
			System.out.println("commit successfully");
			clear(null);
		} else {
			System.out.println("commit fail: " + msg.getReason());
		}
	}
}
