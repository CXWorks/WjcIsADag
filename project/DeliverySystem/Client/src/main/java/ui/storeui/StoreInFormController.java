package ui.storeui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collection;
import java.util.Map;

import bl.blService.examineblService.ExamineblManageService;
import bl.blService.storeblService.StoreInBLService;
import factory.ExamineFactory;
import factory.FormFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import message.OperationMessage;
import model.store.StoreAreaCode;
import model.store.StoreLocation;
import tool.time.TimeConvert;
import tool.ui.Enum2ObservableList;
import tool.ui.OrderVO2ColumnHelper;
import tool.ui.SimpleEnumProperty;
import tool.ui.VisibilityTool;
import ui.common.checkFormat.FormatCheckQueue;
import ui.common.checkFormat.date.CheckPreDateTasker;
import ui.common.checkFormat.field.CheckIsNullTasker;
import ui.common.checkFormat.field.CheckOrderTasker;
import ui.informui.InformController;
import userinfo.UserInfo;
import util.R;
import vo.FormVO;
import vo.ordervo.OrderVO;
import vo.storevo.StoreInVO;

/**
 * Created by Sissel on 2015/11/26.
 */
public class StoreInFormController {
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
	public Button fillPos_Btn;
	public Button save_Btn;
	public Button clear_Btn;
	public Button commit_Btns;
    public Label dateErr_Label;

    private StoreAreaCode area = StoreAreaCode.FLEX;
	private StoreInBLService storeInBLService = FormFactory.getStoreInBLService();
	private InformController informController;
    private FormatCheckQueue formatCheckQueueCommit;
    private FormatCheckQueue formatCheckQueueOrder;

	public static StoreInFormController launch() {
        try {
            FXMLLoader loader = new FXMLLoader(StoreInFormController.class.getResource("storeInForm.fxml"));
            Pane pane = loader.load();
            StoreInFormController controller = loader.getController();
            controller.informController = InformController.newInformController(pane);

            return controller;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
	}

	public static Parent launchInNew(){
        StoreInFormController controller = launch();
        return controller.informController.stackPane;
    }

    public static Parent launchInHistory(StoreInVO storeInVO){
        StoreInFormController controller = launch();
        VisibilityTool.setInvisible
                (controller.fillPos_Btn, controller.save_Btn, controller.clear_Btn, controller.commit_Btns);
        controller.showDetail(storeInVO);
        return controller.informController.stackPane;
    }

    public static Parent launchInManagerEdit(StoreInVO storeInVO){
        StoreInFormController controller = launch();
        controller.showDetail(storeInVO);
        controller.commit_Btns.setOnAction(
                event -> {
                    ExamineblManageService service = ExamineFactory.getExamineblManageService();
                    service.modifyForm(controller.generateVO(storeInVO.formID));
                }
        );
        return controller.informController.stackPane;
    }

    private void showDetail(StoreInVO storeInVO){
        orderID_Field.setText(storeInVO.getOrderID());
        storeIn_DatePicker.setValue(TimeConvert.convertCalendar(storeInVO.getDate()));
        destination_Field.setText(storeInVO.getDestination());
        StoreLocation storeLocation = storeInVO.getLocation();
        area_ChoiceBox.setValue(new SimpleEnumProperty<>(storeLocation.getArea()));
        row_Field.setText(storeLocation.getRow()+"");
        shelf_Field.setText(storeLocation.getShelf()+"");
        position_Field.setText(storeLocation.getPosition() + "");
        fillOrderTable();
    }

	@FXML
	public void initialize() {
		// initialize the choice box
		area_ChoiceBox.setItems(Enum2ObservableList.transit(StoreAreaCode.values()));
		area_ChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            area = newValue.getEnum();
        });
		clear(null);

		OrderVO2ColumnHelper.setKeyColumn(key_Column);
		OrderVO2ColumnHelper.setValueColumn(value_Column);

        formatCheckQueueOrder = new FormatCheckQueue(new CheckOrderTasker(orderID_Field));
        formatCheckQueueCommit = new FormatCheckQueue(
                new CheckOrderTasker(orderID_Field),
                new CheckPreDateTasker(dateErr_Label, storeIn_DatePicker),
                new CheckIsNullTasker(destination_Field),
                new CheckIsNullTasker(row_Field),
                new CheckIsNullTasker(shelf_Field),
                new CheckIsNullTasker(position_Field)
        );

	}

	public void saveDraft(ActionEvent actionEvent) {
		OperationMessage msg = storeInBLService.saveDraft(generateVO(null));
        informController.inform(msg, R.string.SaveDraftSuccess);
	}

	public void loadDraft(ActionEvent actionEvent) {
		StoreInVO vo = storeInBLService.loadDraft();
        if(vo != null){
            this.showDetail(vo);
        }else {
            informController.inform(R.string.LoadDraftFail);
        }
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
        if(!formatCheckQueueCommit.startCheck()){
            return;
        }
		OperationMessage msg = storeInBLService.submit(generateVO(storeInBLService.newID()));
        if(msg.operationResult){
            clear(null);
        }
		informController.inform(msg, "提交入库单成功");
	}

	private StoreInVO generateVO(String formID) {
		Calendar calendar = TimeConvert.convertDate(storeIn_DatePicker.getValue());
		StoreLocation loc = new StoreLocation(area, Integer.parseInt(row_Field.getText()),
				Integer.parseInt(shelf_Field.getText()), Integer.parseInt(position_Field.getText()),
				orderID_Field.getText());
		StoreInVO vo = new StoreInVO(formID, orderID_Field.getText(), calendar, destination_Field.getText(), loc,
				UserInfo.getUserID());
		vo.setCreaterID(UserInfo.getUserID());
		return vo;
	}

	public void fillPosition(ActionEvent actionEvent) {
		StoreLocation location = storeInBLService.getAvailableLocation(area);
		if (location == null) {
			informController.inform("该区域无空缺位置");
			System.out.println("no available location");
			return;
		}
		row_Field.setText("" + location.getRow());
		shelf_Field.setText("" + location.getShelf());
		position_Field.setText("" + location.getPosition());
	}

	public void fillOrderTable() {
        if(!formatCheckQueueOrder.startCheck()){
            order_TableView.getItems().clear();
            return;
        }

		OrderVO orderVO = storeInBLService.loadOrder(orderID_Field.getText());
		order_TableView.setItems(FXCollections.observableArrayList(new OrderVO2ColumnHelper().VO2Entries(orderVO)));
	}
}
