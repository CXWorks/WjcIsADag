package ui.orderui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

import bl.blService.orderblService.OrderBLService;
import bl.blService.receiveblService.ReceiveBLService;
import factory.FormFactory;
import message.OperationMessage;
import tool.ui.OrderVO2ColumnHelper;
import ui.financeui.CheckRevenueFormController;
import vo.ordervo.OrderVO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PoepleReceiveFormController {

	public TextField order_Field;
	public TextField name_Field;
	public DatePicker receive_DatePicker;
	public TableView<Map.Entry<String, String>> order_Table;
	public TableColumn<Map.Entry<String, String>, String> key_Column;
	public TableColumn<Map.Entry<String, String>, String> value_Column;

	OrderBLService obl = FormFactory.getOrderBLService();
	 ReceiveBLService receiveBLService = FormFactory.getReceiveBLService();
	public static Parent launch() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(CheckRevenueFormController.class.getResource("peopleReceiveForm.fxml"));
		return loader.load();
	}

	public void initialize(){
		order_Field.setOnAction(
				uselessParam->{
					fillOrderTable();
				}
				);
		OrderVO2ColumnHelper.setKeyColumn(key_Column); 
		OrderVO2ColumnHelper.setValueColumn(value_Column);
	}

	public void commit(ActionEvent actionEvent) {

		OrderVO ovo = generateOrderVO();
		OperationMessage msg = obl.submit(ovo);
        if(msg.operationResult){
            System.out.println("commit successfully");
            clear(null);
        }else{
            System.out.println("commit fail: " + msg.getReason());
        }

	}

	public OrderVO generateOrderVO() {
		//TODO
		return null;
	}

	public void clear(ActionEvent actionEvent) {
		receive_DatePicker.setValue(LocalDate.now());
		order_Field.clear();
		name_Field.clear();
        order_Table.setItems(null);
	}

	public void saveDraft(ActionEvent actionEvent) {
		//TODO
	}
	
	private void fillOrderTable(){
		OrderVO orderVO = receiveBLService.getOrderVO(order_Field.getText());
		order_Table.setItems(FXCollections.observableArrayList(new OrderVO2ColumnHelper().VO2Entries(orderVO)));
	}


}
