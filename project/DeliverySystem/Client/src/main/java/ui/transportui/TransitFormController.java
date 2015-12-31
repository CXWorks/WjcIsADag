package ui.transportui;

import java.io.IOException;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import bl.blService.examineblService.ExamineblManageService;
import factory.ExamineFactory;
import message.OperationMessage;
import factory.FormFactory;
import bl.blService.transportblService.TransportCenterBLService;
import po.orderdata.DeliverTypeEnum;
import po.receivedata.StateEnum;
import po.transportdata.TransportationEnum;
import tool.time.TimeConvert;
import tool.ui.Enum2ObservableList;
import tool.ui.SimpleEnumProperty;
import tool.ui.VisibilityTool;
import ui.common.checkFormat.FormatCheckQueue;
import ui.common.checkFormat.date.CheckPreDateTasker;
import ui.common.checkFormat.field.CheckIsNullTasker;
import ui.common.checkFormat.field.CheckOrderTasker;
import ui.hallui.RevenueFormController;
import ui.informui.InformController;
import ui.receiveui.ReceiveFormController;
import userinfo.UserInfo;
import vo.FormVO;
import vo.managevo.institution.CenterVO;
import vo.managevo.institution.InstitutionVO;
import vo.receivevo.ReceiveVO;
import vo.transitvo.CenterOutVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import vo.transitvo.TransitVO;

/**
 * Created by Sissel on 2015/11/27.
 */
public class TransitFormController {
	public Label transNumber_Label;
	public Label cargo_Label;
	public ChoiceBox<SimpleEnumProperty<TransportationEnum>> transitType_ChoiceBox;
	public DatePicker transit_DatePicker;
	public TextField departure_Field;
	public ChoiceBox<String> arrival_Box;
	public TextField supervisor_Field;
	public TextField transNumber_Field;
	public TextField cargo_Field;
	public ListView<String> orders_ListView;
	public Label fee_Label;
	public TextField id_Field;
    public Button save_Btn;
    public Button clear_Btn;
    public Button commit_Btn;
    public Button add_Btn;
    public Label dateErr_Label;

    private int fee = 0;
	private ArrayList<String> ids = new ArrayList<String>();
	private TransportationEnum transitEnum = TransportationEnum.TRAIN;
	private TransportCenterBLService transportCenterBLService = FormFactory.getTransportCenterBLService();
	private ArrayList<String> arrivals = transportCenterBLService.getLocation(UserInfo.getInstitutionID());
	private InformController informController;
    private FormatCheckQueue formatCheckQueueAddOrder;
    private FormatCheckQueue formatCheckQueueCommit;

	public static TransitFormController launch() {
		try {
            FXMLLoader loader = new FXMLLoader(TransitFormController.class.getResource("transitForm.fxml"));
            Pane pane = loader.load();
            TransitFormController controller = loader.getController();
            controller.informController = InformController.newInformController(pane);

            return controller;
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return null;
    }

	public static Parent launchInNew(){
        TransitFormController controller = launch();
        return controller.informController.stackPane;
    }

    public static Parent launchInHistory(CenterOutVO transitVO){
        TransitFormController controller = launch();
        VisibilityTool.setInvisible(controller.id_Field, controller.add_Btn,
                controller.save_Btn, controller.clear_Btn, controller.commit_Btn);
        controller.showDetail(transitVO);
        return controller.informController.stackPane;
    }

    public static Parent launchInManagerEdit(CenterOutVO transitVO){
        TransitFormController controller = launch();
        controller.showDetail(transitVO);
        controller.commit_Btn.setOnAction(
                event -> {
                    ExamineblManageService service = ExamineFactory.getExamineblManageService();
                    service.modifyForm(controller.generateVO(transitVO.formID));
                }
        );
        return controller.informController.stackPane;
    }

    private void showDetail(CenterOutVO transitVO) {
        transitType_ChoiceBox.setValue(new SimpleEnumProperty<>(transitVO.transitState));
        transit_DatePicker.setValue(TimeConvert.convertCalendar(transitVO.date));
        departure_Field.setText(transitVO.placeFrom);
        arrival_Box.setValue(transitVO.getPlaceTo());
        supervisor_Field.setText(transitVO.getPeopleSee());
        transNumber_Field.setText(transitVO.getNumberOfIndex());
        cargo_Field.setText(transitVO.shelfNum);
        orders_ListView.getItems().clear();
        orders_ListView.getItems().addAll(transitVO.getIDs());
    }

    private CenterOutVO generateVO(String formID) {
        Calendar calendar = TimeConvert.convertDate(transit_DatePicker.getValue());
        return new CenterOutVO(formID, departure_Field.getText(), transportCenterBLService.newTransID(UserInfo.getInstitutionID()), cargo_Field.getText(),
                calendar, fee_Label.getText(), arrival_Box.getValue(), supervisor_Field.getText(), ids, transitEnum,
                UserInfo.getUserID(), transNumber_Field.getText());
    }

    @FXML
	public void initialize() {
		transit_DatePicker.setValue(LocalDate.now());
		departure_Field.setText(UserInfo.getInstitutionID());
		// initialize the choice box
		transitType_ChoiceBox.setItems(Enum2ObservableList.transit(TransportationEnum.values()));
		transitType_ChoiceBox.setValue(transitType_ChoiceBox.getItems().get(0));
		transitType_ChoiceBox.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> {
                    transitEnum = newValue.getEnum();
                    if (transitEnum == TransportationEnum.TRAIN) {
                        transNumber_Label.setText("车次号");
                        cargo_Label.setText("车厢号");
                    }
                    if (transitEnum == TransportationEnum.PLANE) {
                        transNumber_Label.setText("航班号");
                        cargo_Label.setText("货柜号");
                    }
                    if (transitEnum == TransportationEnum.CAR) {
                        transNumber_Label.setText("车次号");
                        cargo_Label.setText("押运员");
                    }
                });

		arrival_Box.setItems(FXCollections.observableArrayList(arrivals));
		if(arrival_Box.getItems().size() != 0){
			arrival_Box.setValue(arrival_Box.getItems().get(0));
		}

        formatCheckQueueAddOrder = new FormatCheckQueue(
                new CheckOrderTasker(id_Field)
        );
        formatCheckQueueCommit = new FormatCheckQueue(
                new CheckOrderTasker(id_Field),
                new CheckPreDateTasker(dateErr_Label, transit_DatePicker),
                new CheckIsNullTasker(departure_Field),
                new CheckIsNullTasker(supervisor_Field),
                new CheckIsNullTasker(transNumber_Field),
                new CheckIsNullTasker(cargo_Field)
        );
	}

	public void add(ActionEvent actionEvent) {
        if(!formatCheckQueueAddOrder.startCheck()){
            return;
        }

		String a = id_Field.getText();
		System.out.println("add" + a);
		ids.add(a);
		orders_ListView.getItems().add(a);
		id_Field.clear();
	}

	public void saveDraft(ActionEvent actionEvent) {
		transportCenterBLService.saveDraft(generateVO(null));
		clear(null);

	}

	public void loadDraft(ActionEvent actionEvent) {
		this.showDetail(transportCenterBLService.loadDraft());
	}

	public void clear(ActionEvent actionEvent) {
		transit_DatePicker.setValue(LocalDate.now());
		transitType_ChoiceBox.setValue(transitType_ChoiceBox.getItems().get(0));
		departure_Field.clear();
		if(arrival_Box.getItems().size() != 0){
			arrival_Box.setValue(arrival_Box.getItems().get(0));
		}

		supervisor_Field.clear();
		transNumber_Field.clear();
		cargo_Field.clear();
		id_Field.clear();
		ids.clear();
		orders_ListView.getItems().clear();
	}

	public void commit(ActionEvent actionEvent) {
        if(!formatCheckQueueCommit.startCheck()){
            return;
        }
		OperationMessage msg = transportCenterBLService.submit(generateVO(transportCenterBLService.newID()));

		ids.clear();
		// orders_ListView=new ListView<>();
		orders_ListView.getItems().clear();
		if (msg.operationResult) {
			System.out.println("commit successfully");
			clear(null);
		} else {
			System.out.println("commit fail: " + msg.getReason());
		}
	}
}
