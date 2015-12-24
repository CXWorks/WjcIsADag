package ui.deliverui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.stream.Collectors;

import message.OperationMessage;
import bl.blService.deliverblService.DeliverBLService;
import factory.FormFactory;
import tool.time.TimeConvert;
import tool.ui.OrderVO2ColumnHelper;
import ui.hallui.RevenueFormController;
import ui.informui.InformController;
import userinfo.UserInfo;
import util.R.string;
import vo.delivervo.DeliverVO;
import vo.ordervo.OrderVO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class deliverController {

	public TextField id_Search_Field;

	public ListView<String> ids_ListView;

	public TableView<Map.Entry<String, String>> info_TableView;
	public TableColumn<Map.Entry<String, String>, String> key_Column;
	public TableColumn<Map.Entry<String, String>, String> value_Column;

	public TextField id_Field;
	public DatePicker date_DatePicker;
	public ChoiceBox<String> postman_Box;

	private String idToSend = "";
	private String postman = new String();
	DeliverBLService deliverBLService = FormFactory.getDeliverBLService();

	ArrayList<String> toSend = deliverBLService.getUnhandledOrderID(UserInfo.getInstitutionID());
	private ArrayList<String> alreadySend = new ArrayList<String>();
	ArrayList<String> postmans = deliverBLService.getPostman(UserInfo.getInstitutionID());

	// ArrayList<String> toSend = new ArrayList<String>();
	// ArrayList<String> postmans = new ArrayList<String>();

	private InformController informController;

	public static Parent launch() throws IOException {
		FXMLLoader loader = new FXMLLoader(deliverController.class.getResource("deliver.fxml"));
		Pane pane = loader.load();
		deliverController controller = loader.getController();
		controller.informController = InformController.newInformController(pane);

		return controller.informController.stackPane;
	}

	@FXML
	public void initialize() {
		// toSend.add("123"); toSend.add("456");toSend.add("789");
		// postmans.add("wjc");postmans.add("cx");

		postman_Box.setItems(FXCollections.observableArrayList(postmans));
		postman_Box.setValue(postman_Box.getItems().get(0));
		postman_Box.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			postman = newValue.toString();
		});
		// clear(null);
		date_DatePicker.setValue(LocalDate.now());
		ids_ListView.setItems(FXCollections.observableArrayList(toSend));
		ids_ListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			// TODO test
			System.out.println("selected " + newValue.toString());
			idToSend = newValue;
			id_Field.setText(newValue.toString());
			fillOrderTable();
		});
		OrderVO2ColumnHelper.setKeyColumn(key_Column);
		OrderVO2ColumnHelper.setValueColumn(value_Column);
	}

	private void fillOrderTable() {
		OrderVO orderVO = deliverBLService.getOrderVO(idToSend);

		// OrderVO orderVO =
		// new OrderVO("11","程翔", "王嘉琛", "南京", "北京", "", "",
		// "18351890356", "13724456739", "3", "图书", "", "", "", null, null,
		// null,
		// DeliverTypeEnum.NORMAL, PackingEnum.BAG);

		info_TableView.setItems(FXCollections.observableArrayList(new OrderVO2ColumnHelper().VO2Entries(orderVO)));
	}

	public void refresh(ActionEvent actionEvent) {

		ids_ListView.getItems().clear();
		toSend = deliverBLService.getUnhandledOrderID(UserInfo.getInstitutionID());
		toSend = new ArrayList<String>(
		toSend.stream().filter(string ->
			!alreadySend.contains(string)
		).collect(Collectors.toList()));
		ids_ListView.setItems(FXCollections.observableArrayList(toSend));
	}

	public void search(ActionEvent actionEvent) {
		String filter = id_Search_Field.getText();

		OrderVO orderVO = deliverBLService.getOrderVO(filter);
		info_TableView.setItems(FXCollections.observableArrayList(new OrderVO2ColumnHelper().VO2Entries(orderVO)));

		ids_ListView.setItems(FXCollections.observableArrayList(filter));

	}

	public void commit(ActionEvent actionEvent) {
		DeliverVO vo = generateVO(deliverBLService.newID());
		OperationMessage msg = deliverBLService.submit(vo);
		alreadySend.add(vo.getOrderID());
		if (msg.operationResult) {
			System.out.println("commit successfully");
			clear(null);
			refresh(null);
		} else {
			System.out.println("commit fail: " + msg.getReason());
		}
	}

	private DeliverVO generateVO(String formID) {
		Calendar calendar = TimeConvert.convertDate(date_DatePicker.getValue());
		return new DeliverVO(formID, id_Field.getText(), calendar, postman_Box.getValue().toString(),
				UserInfo.getUserID());
	}

	public void clear(ActionEvent actionEvent) {
		date_DatePicker.setValue(LocalDate.now());
		id_Field.clear();
		postman_Box.setValue(postman_Box.getItems().get(0));
	}

	public void saveDraft(ActionEvent actionEvent) {
		deliverBLService.saveDraft(generateVO(null));
		clear(null);
	}

}
