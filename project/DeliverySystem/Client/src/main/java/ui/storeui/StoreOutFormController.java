package ui.storeui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Map;

import bl.blService.storeblService.StoreInBLService;
import bl.blService.storeblService.StoreOutBLService;
import factory.FormFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import message.OperationMessage;
import model.store.StoreAreaCode;
import model.store.StoreLocation;
import po.transportdata.TransportationEnum;
import tool.time.TimeConvert;
import tool.ui.Enum2ObservableList;
import tool.ui.OrderVO2ColumnHelper;
import tool.ui.SimpleEnumProperty;
import tool.ui.TransitVO2ColumnHelper;
import ui.hallui.RevenueFormController;
import ui.informui.InformController;
import userinfo.UserInfo;
import vo.ordervo.OrderVO;
import vo.storevo.StoreInVO;
import vo.storevo.StoreOutVO;
import vo.transitvo.TransitVO;

/**
 * Created by Sissel on 2015/11/26.
 */
public class StoreOutFormController {

	public Label storeout_DatePicker;
	public DatePicker storeOut_DatePicker;
	public TextField orderID_Field;
	public TextField destination_Field;
	public ChoiceBox<SimpleEnumProperty<TransportationEnum>> LoadType_ChoiceBox;
	public TextField transitID_Field;
	public TableView<Map.Entry<String, String>> transit_TableView;
	public TableView<Map.Entry<String, String>> order_TableView;
	public TableColumn<Map.Entry<String, String>, String> key_Column_o;
	public TableColumn<Map.Entry<String, String>, String> value_Column_o;
	public TableColumn<Map.Entry<String, String>, String> key_Column_t;
	public TableColumn<Map.Entry<String, String>, String> value_Column_t;

	TransportationEnum tran = TransportationEnum.CAR;

	StoreOutBLService storeOutBLService = FormFactory.getStoreOutBLService();

	private InformController informController;

	public static Parent launch() throws IOException {
		FXMLLoader loader = new FXMLLoader(StoreOutFormController.class.getResource("storeOutForm.fxml"));
		Pane pane = loader.load();
		StoreOutFormController controller = loader.getController();
		controller.informController = InformController.newInformController(pane);

		return controller.informController.stackPane;
	}

	@FXML
	public void initialize() {
		// initialize the choice box
		LoadType_ChoiceBox.setItems(Enum2ObservableList.transit(TransportationEnum.values()));
		LoadType_ChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			tran = newValue.getEnum();
		});
		clear(null);

		orderID_Field.setOnAction(uselessParam -> {
			fillOrderTable();
		});

		OrderVO2ColumnHelper.setKeyColumn(key_Column_o);
		OrderVO2ColumnHelper.setValueColumn(value_Column_o);

		transitID_Field.setOnAction(uselessParam -> {
			fillTransitTable();
		});
		TransitVO2ColumnHelper.setKeyColumn(key_Column_t);
		TransitVO2ColumnHelper.setValueColumn(value_Column_t);
	}

	public void saveDraft(ActionEvent actionEvent) {
		storeOutBLService.saveDraft(generateVO(null));
	}

	private StoreOutVO generateVO(String formID) {
		Calendar calendar = TimeConvert.convertDate(storeOut_DatePicker.getValue());
		StoreOutVO vo = new StoreOutVO(formID, orderID_Field.getText(), calendar, destination_Field.getText(), tran,
				transitID_Field.getText(), UserInfo.getUserID());
		return vo;
	}

	public void clear(ActionEvent actionEvent) {
		storeOut_DatePicker.setValue(LocalDate.now());
		orderID_Field.clear();
		destination_Field.clear();
		transit_TableView.setItems(null);
		order_TableView.setItems(null);
		transitID_Field.clear();
		LoadType_ChoiceBox.setValue(LoadType_ChoiceBox.getItems().get(0));
	}

	public void commit(ActionEvent actionEvent) {
		OperationMessage msg = storeOutBLService.submit(generateVO(storeOutBLService.newID()));

		if (msg.operationResult) {
			System.out.println("commit successfully");
			clear(null);
		} else {
			System.out.println("commit fail: " + msg.getReason());
		}
	}

	private void fillOrderTable() {
		OrderVO orderVO = storeOutBLService.loadOrder(orderID_Field.getText());

		// OrderVO orderVO =
		// new OrderVO("11","程翔", "王嘉琛", "南京", "北京", "", "",
		// "18351890356", "13724456739", "3", "图书", "", "", "", null, null,
		// null,
		// DeliverTypeEnum.NORMAL, PackingEnum.BAG);

		order_TableView.setItems(FXCollections.observableArrayList(new OrderVO2ColumnHelper().VO2Entries(orderVO)));
	}

	private void fillTransitTable() {
		TransitVO transitVO = storeOutBLService.getTransportVO(transitID_Field.getText());
		transit_TableView
				.setItems(FXCollections.observableArrayList(new TransitVO2ColumnHelper().VO2Entries(transitVO)));
	}
}
