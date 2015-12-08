package ui.orderui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import bl.blService.deliverblService.CheckDeliverForm;
import bl.blService.receiveblService.ReceiveBLService;
import factory.DeliverFactory;
import factory.FormFactory;
import userinfo.UserInfo;
import vo.ordervo.OrderVO;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class PeopleReceiveFormController {

	public TextField name_Field;
	public DatePicker receive_DatePicker;
	public TableView<OrderVO> order_TableView;
	public TableColumn<OrderVO,String> id_Column;
	public TableColumn<OrderVO,String> address_Column;
	public TableColumn<OrderVO,String> name_Column;

	public TextField id_Field;

	private OrderVO selected = null;
	private List<OrderVO> orderVOs;

	CheckDeliverForm checkDeliver = DeliverFactory.getCheckDeliverForm();
	//	ReceiveBLService receiveBLService = FormFactory.getReceiveBLService();

	public static Parent launch() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(PeopleReceiveFormController.class.getResource("peopleReceiveForm.fxml"));
		Pane pane=loader.load();
		return pane;
	}

	@FXML
	public void initialize(){
		id_Column.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getFormID())
		);
		address_Column.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getAddressTo())
        );
		name_Column.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getNameTo())
        );
		order_TableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    name_Field.clear();
                    receive_DatePicker.setValue(LocalDate.now());
                    selected = newValue;
                }
        );
	}

	public void search(ActionEvent actionEvent){
		String id = id_Field.getText();
        if(id.isEmpty()){
            orderVOs = checkDeliver.getDeliverOrder(UserInfo.getUserID());
            order_TableView.getItems().clear();
            order_TableView.getItems().addAll(orderVOs);
        }
	}


	public void commit(ActionEvent actionEvent) {

	}

	public void clear(ActionEvent actionEvent) {
		receive_DatePicker.setValue(LocalDate.now());
		name_Field.clear();
	}

}
