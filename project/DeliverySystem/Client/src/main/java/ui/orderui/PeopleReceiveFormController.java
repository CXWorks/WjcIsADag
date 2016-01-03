package ui.orderui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import bl.blService.deliverblService.CheckDeliverForm;
import bl.blService.receiveblService.ReceiveBLService;
import factory.DeliverFactory;
import factory.FormFactory;
import javafx.scene.control.*;
import tool.time.TimeConvert;
import ui.common.checkFormat.FormatCheckQueue;
import ui.common.checkFormat.date.CheckPreDateTasker;
import ui.common.checkFormat.field.CheckIsNullTasker;
import ui.common.checkFormat.field.CheckOrderTasker;
import ui.hallui.RevenueFormController;
import ui.informui.InformController;
import userinfo.UserInfo;
import vo.delivervo.DeliverVO;
import vo.ordervo.OrderVO;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class PeopleReceiveFormController {

	public TextField name_Field;
	public DatePicker receive_DatePicker;
	public TableView<OrderVO> order_TableView;
	public TableColumn<OrderVO, String> id_Column;
	public TableColumn<OrderVO, String> address_Column;
	public TableColumn<OrderVO, String> name_Column;

	public TextField id_Field;
    public Label dateErr_Label;
    private String id;

    private OrderVO selected = null;
	private List<OrderVO> orderVOs;

	private InformController informController;
    private FormatCheckQueue formatCheckQueueSearch;
	private FormatCheckQueue formatCheckQueueCommit;

	CheckDeliverForm checkDeliver = DeliverFactory.getCheckDeliverForm();
	// ReceiveBLService receiveBLService = FormFactory.getReceiveBLService();

	public static Parent launch() throws IOException {
		FXMLLoader loader = new FXMLLoader(PeopleReceiveFormController.class.getResource("peopleReceiveForm.fxml"));
		Pane pane = loader.load();
		PeopleReceiveFormController controller = loader.getController();
		controller.informController = InformController.newInformController(pane);

		return controller.informController.stackPane;
	}

	@FXML
	public void initialize() {
		id_Column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFormID()));
		address_Column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddressTo()));
		name_Column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNameTo()));
		order_TableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            name_Field.clear();
            receive_DatePicker.setValue(LocalDate.now());
            selected = newValue;
            id=newValue.formID;
        });

        formatCheckQueueSearch = new FormatCheckQueue(new CheckOrderTasker(id_Field));
        formatCheckQueueCommit = new FormatCheckQueue(
                new CheckIsNullTasker(name_Field),
                new CheckPreDateTasker(dateErr_Label, receive_DatePicker)
        );
        
        
	}


	public void refresh(ActionEvent actionEvent) {
		orderVOs = checkDeliver.getDeliverOrder(UserInfo.getUserID());
		order_TableView.getItems().clear();
		order_TableView.getItems().addAll(orderVOs);
	}

	public void commit(ActionEvent actionEvent) {
        if(!formatCheckQueueCommit.startCheck()){
            return;
        }

		DeliverVO deliverVO = new DeliverVO(null, selected.formID,
				TimeConvert.convertDate(receive_DatePicker.getValue()), id, UserInfo.getUserID());
		checkDeliver.finishDelivery(deliverVO);
	}

	public void clear(ActionEvent actionEvent) {
		receive_DatePicker.setValue(LocalDate.now());
		name_Field.clear();
	}

}
