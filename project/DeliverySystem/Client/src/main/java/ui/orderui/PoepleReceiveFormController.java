package ui.orderui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import bl.blService.deliverblService.CheckDeliverForm;
import bl.blService.orderblService.OrderBLService;
import bl.blService.receiveblService.ReceiveBLService;
import factory.DeliverFactory;
import factory.FormFactory;
import message.OperationMessage;
import tool.ui.OrderVO2ColumnHelper;
import ui.financeui.CheckRevenueFormController;
import userinfo.UserInfo;
import vo.delivervo.DeliverVO;
import vo.managevo.car.CarVO;
import vo.ordervo.OrderVO;
import vo.receivevo.ReceiveVO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class PoepleReceiveFormController {

	public TextField name_Field;
	public DatePicker receive_DatePicker;
	public TableView<OrderVOCheckItem> order_TableView;
	public TableColumn<OrderVOCheckItem,String> id_Column;
	public TableColumn<OrderVOCheckItem,String> address_Column;
	public TableColumn<OrderVOCheckItem,String> name_Column;

	public TextField id_Field;

	ReceiveBLService receivebl=FormFactory.getReceiveBLService();
	CheckDeliverForm checkDeliver=DeliverFactory.getCheckDeliverForm();
	private OrderVO selected = null;
//			new OrderVO(null, null, null, 
//			null, null, null, null, null, null, null, 
//			null, null, null, null, null, null, null, null, null);
	private OrderVOCheckItem orderVoCheckItem = new OrderVOCheckItem(selected);
	private List<OrderVO> orders=checkDeliver.getDeliverOrder(UserInfo.getUserID());



	public static Parent launch() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(PoepleReceiveFormController.class.getResource("peopleReceiveForm.fxml"));
		Pane pane=loader.load();
		return pane;
	}

	@FXML
	public void initialize(){
		id_Column.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getVo().getFormID())
				);
		address_Column.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getVo().getAddressTo())
				);
		name_Column.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getVo().getNameTo())
				);
		order_TableView.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> {
					// TODO test
					System.out.println("selected " + newValue.getVo());
					selected=newValue.getVo();
					orderVoCheckItem=newValue;
					name_Field.setText(selected.getReceivePeople());
					receive_DatePicker.setValue(LocalDate.parse(selected.getReceiveDate().toString()));

					}
	
				);



		receive_DatePicker.setValue(LocalDate.now());
	}

	public void search(ActionEvent actionEvent){
		//TODO
		String id = id_Field.getText();
		OrderVO result=receivebl.getOrderVO(id);
	//	order_TableView.setFocusModel();
	}


	public void commit(ActionEvent actionEvent) {

		OrderVO selected =  order_TableView.getSelectionModel().getSelectedItem().getVo();
		DeliverVO dvo = null;
		OperationMessage msg = checkDeliver.finishDelivery(generateDeliverVO(dvo));
		if(msg.operationResult){
			System.out.println("commit successfully");
			clear(null);
		}else{
			System.out.println("commit fail: " + msg.getReason());
		}

	}

	public DeliverVO generateDeliverVO(DeliverVO ovo) {

		return new DeliverVO(name_Field.getText(),receive_DatePicker.getValue(),true);
	}

	public void clear(ActionEvent actionEvent) {
		receive_DatePicker.setValue(LocalDate.now());
		name_Field.clear();
	}






}
