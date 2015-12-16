package ui.storeui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Map;

import bl.blService.storeblService.StoreInBLService;
import factory.FormFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import message.OperationMessage;
import model.store.StoreAreaCode;
import model.store.StoreLocation;
import po.receivedata.StateEnum;
import tool.time.TimeConvert;
import tool.ui.Enum2ObservableList;
import tool.ui.OrderVO2ColumnHelper;
import tool.ui.SimpleEnumProperty;
import userinfo.UserInfo;
import vo.ordervo.OrderVO;
import vo.storevo.StoreInVO;

/**
 * Created by Sissel on 2015/11/26.
 */
public class StoreInFormController {
	public Label storein_DatePicker;
	public DatePicker storeIn_DatePicker;
	public TextField orderID_Field;
	public TextField destination_Field;
	public TableView<Map.Entry<String, String>> order_TableView;
	public TextField row_Field;
	public TextField shelf_Field;
	public TextField position_Field;
	public ChoiceBox<SimpleEnumProperty<StoreAreaCode>> area_ChoiceBox;
	public TableColumn<Map.Entry<String, String>, String> key_Column;
	public TableColumn<Map.Entry<String, String>, String> value_Column;

	private StoreAreaCode area = StoreAreaCode.FLEX;

	private StoreInBLService storeInBLService = FormFactory.getStoreInBLService();

	public static Parent launch() throws IOException {

		FXMLLoader contentLoader = new FXMLLoader();
		contentLoader.setLocation(StoreInFormController.class.getResource("storeInForm.fxml"));
		return contentLoader.load();
	}

	@FXML
	public void initialize() {
		// initialize the choice box
		area_ChoiceBox.setItems(Enum2ObservableList.transit(StoreAreaCode.values()));
		area_ChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			area = newValue.getEnum();
		});
		clear(null);

		orderID_Field.setOnAction(uselessParam -> {
			fillOrderTable();
		});

		OrderVO2ColumnHelper.setKeyColumn(key_Column);
		OrderVO2ColumnHelper.setValueColumn(value_Column);
	}

	public void saveDraft(ActionEvent actionEvent) {
		storeInBLService.saveDraft(generateVO(null));
	}

	public void clear(ActionEvent actionEvent) {
		storeIn_DatePicker.setValue(LocalDate.now());
		orderID_Field.clear();
		destination_Field.clear();
		order_TableView.setItems(null);
		row_Field.clear();
		shelf_Field.clear();
		position_Field.clear();
		area_ChoiceBox.setValue(area_ChoiceBox.getItems().get(0));
	}

	public void commit(ActionEvent actionEvent) {
		OperationMessage msg = storeInBLService.submit(generateVO(storeInBLService.newID()));

		if (msg.operationResult) {
			System.out.println("commit successfully");
			clear(null);
		} else {
			System.out.println("commit fail: " + msg.getReason());
		}
	}

	private StoreInVO generateVO(String formID) {
		Calendar calendar = TimeConvert.convertDate(storeIn_DatePicker.getValue());
		StoreLocation loc = new StoreLocation(area, Integer.parseInt(row_Field.getText()),
				Integer.parseInt(shelf_Field.getText()), Integer.parseInt(position_Field.getText()),
				orderID_Field.getText());
		StoreInVO vo = new StoreInVO(formID, orderID_Field.getText(), calendar,
				destination_Field.getText(), loc,UserInfo.getUserID());
		vo.setCreaterID(UserInfo.getUserID());
		return vo;
	}

	public void fillPosition(ActionEvent actionEvent) {
		StoreLocation location = storeInBLService.getAvailableLocation(area);
		row_Field.setText("" + location.getRow());
		shelf_Field.setText("" + location.getShelf());
		position_Field.setText("" + location.getPosition());
	}

	private void fillOrderTable() {
		OrderVO orderVO = storeInBLService.loadOrder(orderID_Field.getText());

		// OrderVO orderVO =
		// new OrderVO("11","程翔", "王嘉琛", "南京", "北京", "", "",
		// "18351890356", "13724456739", "3", "图书", "", "", "", null, null,
		// null,
		// DeliverTypeEnum.NORMAL, PackingEnum.BAG);

		order_TableView.setItems(FXCollections.observableArrayList(new OrderVO2ColumnHelper().VO2Entries(orderVO)));
	}
}
