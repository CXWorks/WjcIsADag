package ui.storeui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collection;
import java.util.Map;

import bl.blService.examineblService.ExamineblManageService;
import bl.blService.storeblService.StoreInBLService;
import bl.blService.storeblService.StoreOutBLService;
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
import po.transportdata.TransportationEnum;
import tool.time.TimeConvert;
import tool.ui.*;
import ui.common.checkFormat.FormatCheckQueue;
import ui.common.checkFormat.date.CheckPreDateTasker;
import ui.common.checkFormat.field.CheckIsNullTasker;
import ui.common.checkFormat.field.CheckOrderTasker;
import ui.common.checkFormat.field.CheckTransitIDTasker;
import ui.hallui.RevenueFormController;
import ui.informui.InformController;
import userinfo.UserInfo;
import util.R;
import vo.FormVO;
import vo.ordervo.OrderVO;
import vo.ordervo.PredictVO;
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
	public Button save_Btn;
	public Button clear_Btn;
	public Button commit_Btn;
    public Label dateErr_Label;

    private TransportationEnum tran = TransportationEnum.CAR;
	private StoreOutBLService storeOutBLService = FormFactory.getStoreOutBLService();
	private InformController informController;
    private FormatCheckQueue formatCheckQueueOrder;
    private FormatCheckQueue formatCheckQueueTrans;
    private FormatCheckQueue formatCheckQueueCommit;

	public static StoreOutFormController launch() {
		try {
            FXMLLoader loader = new FXMLLoader(StoreOutFormController.class.getResource("storeOutForm.fxml"));
            Pane pane = loader.load();
            StoreOutFormController controller = loader.getController();
            controller.informController = InformController.newInformController(pane);

            return controller;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
	}

	public static Parent launchInNew() {
        StoreOutFormController controller = launch();
        return controller.informController.stackPane;
    }

    public static Parent launchInHistory(StoreOutVO storeOutVO) {
        StoreOutFormController controller = launch();
        VisibilityTool.setInvisible(controller.clear_Btn, controller.commit_Btn, controller.save_Btn);
        controller.showDetail(storeOutVO);
        return controller.informController.stackPane;
    }

    public static Parent launchInManagerEdit(StoreOutVO storeOutVO) {
        StoreOutFormController controller = launch();
        controller.showDetail(storeOutVO);
        controller.commit_Btn.setOnAction(
                event -> {
                    ExamineblManageService service = ExamineFactory.getExamineblManageService();
                    service.modifyForm(controller.generateVO(storeOutVO.formID));
                }
        );
        return controller.informController.stackPane;
    }

    private void showDetail(StoreOutVO storeOutVO) {
        orderID_Field.setText(storeOutVO.getOrderID());
        transitID_Field.setText(storeOutVO.transID);
        storeOut_DatePicker.setValue(TimeConvert.convertCalendar(storeOutVO.getDate()));
        destination_Field.setText(storeOutVO.getDestination());
        LoadType_ChoiceBox.setValue(new SimpleEnumProperty<>(storeOutVO.transportation));
        fillOrderTable();
        fillTransitTable();
    }

    @FXML
	public void initialize() {
		// initialize the choice box
		LoadType_ChoiceBox.setItems(Enum2ObservableList.transit(TransportationEnum.values()));
		LoadType_ChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            tran = newValue.getEnum();
        });
		clear(null);

		OrderVO2ColumnHelper.setKeyColumn(key_Column_o);
		OrderVO2ColumnHelper.setValueColumn(value_Column_o);

		TransitVO2ColumnHelper.setKeyColumn(key_Column_t);
		TransitVO2ColumnHelper.setValueColumn(value_Column_t);

        formatCheckQueueOrder = new FormatCheckQueue(new CheckOrderTasker(orderID_Field));
        formatCheckQueueTrans = new FormatCheckQueue(new CheckTransitIDTasker(transitID_Field));
        formatCheckQueueCommit = new FormatCheckQueue(
                new CheckTransitIDTasker(transitID_Field),
                new CheckOrderTasker(orderID_Field),
                new CheckPreDateTasker(dateErr_Label, storeOut_DatePicker),
                new CheckIsNullTasker(destination_Field)
        );
	}

	public void saveDraft(ActionEvent actionEvent) {
		OperationMessage msg = storeOutBLService.saveDraft(generateVO(null));
		informController.inform(msg, R.string.SaveDraftSuccess);
	}

	public void loadDraft(ActionEvent actionEvent) {
		StoreOutVO vo = storeOutBLService.loadDraft();
        if(vo != null){
            this.showDetail(vo);
        }else {
            informController.inform(R.string.LoadDraftFail);
        }
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
        if(!formatCheckQueueCommit.startCheck()){
            return;
        }
		OperationMessage msg = storeOutBLService.submit(generateVO(storeOutBLService.newID()));

		if (msg.operationResult) {
			clear(null);
		}
        informController.inform(msg, "提交出库单成功");
	}

	public void fillOrderTable() {
        if(!formatCheckQueueOrder.startCheck()){
            return;
        }
		OrderVO orderVO = storeOutBLService.loadOrder(orderID_Field.getText());
		order_TableView.setItems(FXCollections.observableArrayList(new OrderVO2ColumnHelper().VO2Entries(orderVO)));
	}

	public void fillTransitTable() {
        if(!formatCheckQueueTrans.startCheck()){
            return;
        }
		TransitVO transitVO = storeOutBLService.getTransportVO(transitID_Field.getText());
		transit_TableView
				.setItems(FXCollections.observableArrayList(new TransitVO2ColumnHelper().VO2Entries(transitVO)));
	}
}
