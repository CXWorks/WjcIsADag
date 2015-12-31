package ui.receiveui;

import bl.blService.receiveblService.ReceiveBLService;
import tool.time.TimeConvert;
import factory.FormFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import message.OperationMessage;
import po.receivedata.StateEnum;
import tool.ui.Enum2ObservableList;
import tool.ui.OrderVO2ColumnHelper;
import tool.ui.SimpleEnumProperty;
import tool.ui.VisibilityTool;
import ui.common.checkFormat.FormatCheckQueue;
import ui.common.checkFormat.date.CheckPreDateTasker;
import ui.common.checkFormat.field.CheckIsNullTasker;
import ui.common.checkFormat.field.CheckOrderTasker;
import ui.common.checkFormat.field.CheckTransitIDTasker;
import ui.informui.InformController;
import userinfo.UserInfo;
import vo.FormVO;
import vo.ordervo.OrderVO;
import vo.receivevo.ReceiveVO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collection;
import java.util.Map;

/**
 * Created by Sissel on 2015/11/21.
 */
public class ReceiveFormController {

	public DatePicker arrive_DatePicker;
	public TextField transitID_Field;
	public TextField departure_Field;
	public ChoiceBox<SimpleEnumProperty<StateEnum>> arriveState_Box;
	public TableView<Map.Entry<String, String>> order_Table;
	public TextField order_Field;
	public Label date_ErrLabel;
	public TableColumn<Map.Entry<String, String>, String> key_Column;
	public TableColumn<Map.Entry<String, String>, String> value_Column;
	public Button clear_Btn;
	public Button save_Btn;
	public Button commit_Btn;

	private StateEnum stateEnum = StateEnum.Complete;
    private FormatCheckQueue formatCheckQueueOrder;
    private FormatCheckQueue formatCheckQueueCommit;

	ReceiveBLService receiveBLService = FormFactory.getReceiveBLService();

	private InformController informController;

	public static ReceiveFormController launch() {
		try {
            FXMLLoader loader = new FXMLLoader(ReceiveFormController.class.getResource("ReceiveForm.fxml"));
            Pane pane = loader.load();
            ReceiveFormController controller = loader.getController();
            controller.informController = InformController.newInformController(pane);

            return controller;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

	public static Parent launchInNew() {
        ReceiveFormController controller = launch();
        return controller.informController.stackPane;
    }

    public static Parent launchInHistory(ReceiveVO receiveVO) {
        ReceiveFormController controller = launch();
        VisibilityTool.setInvisible(controller.clear_Btn, controller.commit_Btn, controller.save_Btn);
        controller.showDetail(receiveVO);
        return controller.informController.stackPane;
    }

    public static Parent launchInManagerEdit(ReceiveVO receiveVO, Collection<FormVO> formVOs) {
        ReceiveFormController controller = launch();
        controller.showDetail(receiveVO);
        controller.commit_Btn.setOnAction(
                event -> {
                    formVOs.remove(receiveVO);
                    formVOs.add(controller.generateVO(receiveVO.formID));
                }
        );
        return controller.informController.stackPane;
    }

    private void showDetail(ReceiveVO receiveVO){
        order_Field.setText(receiveVO.getOrderID());
        transitID_Field.setText(receiveVO.getTransitID());
        arrive_DatePicker.setValue(TimeConvert.convertCalendar(receiveVO.getDate()));
        departure_Field.setText(receiveVO.getDepature());
        arriveState_Box.setValue(new SimpleEnumProperty<>(receiveVO.getOrderState()));
        fillOrderTable();
    }

	@FXML
	public void initialize() {
		// initialize the choice box
		arriveState_Box.setItems(Enum2ObservableList.transit(StateEnum.values()));
		arriveState_Box.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            stateEnum = newValue.getEnum();
        });
		clear(null);

		OrderVO2ColumnHelper.setKeyColumn(key_Column);
		OrderVO2ColumnHelper.setValueColumn(value_Column);

        formatCheckQueueOrder = new FormatCheckQueue(new CheckOrderTasker(order_Field));
        formatCheckQueueCommit = new FormatCheckQueue(
                new CheckOrderTasker(order_Field),
                new CheckPreDateTasker(date_ErrLabel, arrive_DatePicker),
                new CheckTransitIDTasker(transitID_Field),
                new CheckIsNullTasker(departure_Field)
        );
	}

	public void commit(ActionEvent actionEvent) {
        if(!formatCheckQueueCommit.startCheck()){
            return;
        }

		OperationMessage msg = receiveBLService.submit(generateVO(receiveBLService.newID()));

		if (msg.operationResult) {
			System.out.println("commit successfully");
			clear(null);
		} else {
			System.out.println("commit fail: " + msg.getReason());
		}
	}

	public void clear(ActionEvent actionEvent) {
		arrive_DatePicker.setValue(LocalDate.now());
		transitID_Field.clear();
		departure_Field.clear();
		order_Field.clear();
		order_Table.getItems().clear();
		arriveState_Box.setValue(arriveState_Box.getItems().get(0));
	}

	public void saveDraft(ActionEvent actionEvent) {
		receiveBLService.saveDraft(generateVO(null));
	}

	private ReceiveVO generateVO(String formID) {
		Calendar calendar = TimeConvert.convertDate(arrive_DatePicker.getValue());
		return new ReceiveVO(formID, order_Field.getText(), transitID_Field.getText(), calendar,
				departure_Field.getText(), stateEnum, UserInfo.getUserID());
	}

	private void fillOrderTable() {
        if(!formatCheckQueueOrder.startCheck()){
            order_Table.getItems().clear();
            return;
        }
		OrderVO orderVO = receiveBLService.getOrderVO(order_Field.getText());
		order_Table.setItems(FXCollections.observableArrayList(new OrderVO2ColumnHelper().VO2Entries(orderVO)));
	}

}
