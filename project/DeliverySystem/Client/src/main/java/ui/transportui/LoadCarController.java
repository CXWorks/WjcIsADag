package ui.transportui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import tool.time.TimeConvert;
import tool.ui.SimpleEnumProperty;
import tool.ui.VisibilityTool;
import ui.hallui.RevenueFormController;
import ui.informui.InformController;
import userinfo.UserInfo;
import vo.FormVO;
import vo.managevo.car.CarVO;
import vo.transitvo.CenterOutVO;
import vo.transitvo.LoadVO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import po.transportdata.TransportationEnum;
import message.OperationMessage;
import bl.blService.transportblService.TransportCenterBLService;
import bl.blService.transportblService.TransportHallBLService;
import factory.FormFactory;

/**
 * Created by Sissel on 2015/11/23.
 * 这个类负责中转中心和营业厅的装车单，虽然文档上有一个区别，但是多的东西并无卵用，所以我合起来了
 */
public class LoadCarController {

	public DatePicker date_Picker;
	public TextField monitor_Field;
	public TextField guard_Field;
	public Label date_errLabel;
	public Label fee_Label;
	public ListView<String> orders_ListView;
	public TextField id_Field;
	public ChoiceBox<String> arrival_ChoiceBox;
	public ChoiceBox<String> carID_ChoiceBox;
    public Button save_Btn;
    public Button clear_Btn;
    public Button commit_Btn;
    public Button add_Btn;

    ArrayList<String> ids = new ArrayList<String>();

	TransportHallBLService transportHallBLService = FormFactory.getTransportHallBLService();

	ArrayList<String> arrivals = transportHallBLService.getLocation(UserInfo.getInstitutionID());
	ArrayList<String> cars = transportHallBLService.getCars(UserInfo.getInstitutionID());

	private InformController informController;

	public static LoadCarController launch() {
		try {
            FXMLLoader loader = new FXMLLoader(LoadCarController.class.getResource("loadCarForm.fxml"));
            Pane pane = loader.load();
            LoadCarController controller = loader.getController();
            controller.informController = InformController.newInformController(pane);

            return controller;
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return null;
    }

	public static Parent launchInNew() {
		LoadCarController controller = launch();
		return controller.informController.stackPane;
	}

    public static Parent launchInHistory(LoadVO loadVO) {
        LoadCarController controller = launch();
        VisibilityTool.setInvisible(controller.add_Btn, controller.id_Field,
                controller.save_Btn, controller.clear_Btn, controller.commit_Btn);
        controller.showDetail(loadVO);
        return controller.informController.stackPane;
    }

    public static Parent launchInManagerEdit(LoadVO loadVO, Collection<FormVO> formVOs) {
        LoadCarController controller = launch();
        controller.showDetail(loadVO);
        controller.commit_Btn.setOnAction(
                event -> {
                    formVOs.remove(loadVO);
                    formVOs.add(controller.generateVO(loadVO.formID));
                }
        );
        return controller.informController.stackPane;
    }

    private void showDetail(LoadVO loadVO) {
        date_Picker.setValue(TimeConvert.convertCalendar(loadVO.date));
        arrival_ChoiceBox.setValue(loadVO.getPlaceTo());
        monitor_Field.setText(loadVO.getPeopleSee());
        guard_Field.setText(loadVO.peopletransport);
        // TODO carID carID_ChoiceBox
    }

    @FXML
	public void initialize() {
		arrival_ChoiceBox.setItems(FXCollections.observableArrayList(arrivals));
		carID_ChoiceBox.setItems(FXCollections.observableArrayList(cars));
		System.out.println(transportHallBLService.newID());
		clear(null);
	}

	public void add(ActionEvent actionEvent) {
		String orderID = id_Field.getText();
		System.out.println("add" + orderID);
		ids.add(orderID);
		orders_ListView.getItems().add(orderID);
		id_Field.clear();
	}

	public void saveDraft(ActionEvent actionEvent) {
		transportHallBLService.saveDraft(generateVO(null));
	}

	public void clear(ActionEvent actionEvent) {
		date_Picker.setValue(LocalDate.now());
		arrival_ChoiceBox.setValue(arrival_ChoiceBox.getItems().get(0));
		monitor_Field.clear();
		guard_Field.clear();
		orders_ListView.getItems().clear();
	}

	private LoadVO generateVO(String formID) {
		Calendar calendar = TimeConvert.convertDate(date_Picker.getValue());
		return new LoadVO(formID, carID_ChoiceBox.getValue().toString(), guard_Field.getText(), fee_Label.getText(),
				calendar, transportHallBLService.newID(), arrival_ChoiceBox.getValue().toString(),
				monitor_Field.getText(), ids, UserInfo.getUserID());
	}

	public void commit(ActionEvent actionEvent) {

		OperationMessage msg = transportHallBLService.submit(generateVO(transportHallBLService.newID()));

		if (msg.operationResult) {
			System.out.println("commit successfully");
			clear(null);
		} else {
			System.out.println("commit fail: " + msg.getReason());
		}
	}
}
