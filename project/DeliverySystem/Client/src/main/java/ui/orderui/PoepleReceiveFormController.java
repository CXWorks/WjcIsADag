package ui.orderui;

import java.io.IOException;
import java.util.Calendar;

import bl.blService.deliverblService.DeliverBLService;
import bl.blService.orderblService.OrderBLService;
import factory.FormFactory;
import message.OperationMessage;
import po.orderdata.DeliverTypeEnum;
import po.orderdata.PackingEnum;
import ui.common.FormBridge;
import ui.financeui.CheckRevenueFormController;
import ui.receiveui.HallReceiveFormController;
import userinfo.Services;
import vo.delivervo.DeliverVO;
import vo.financevo.RevenueVO;
import vo.ordervo.OrderVO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class PoepleReceiveFormController {

	public TextField id_Field;
	public TextField name_Field;
	public DatePicker receive_DatePicker;
	

	public Label nameFrom;
	public Label nameTo;
	public Label addressFrom;
	public Label addressTo;
	public Label unitFrom;
	public Label unitTo;
	public Label phoneNumFrom;
	public Label phoneNumTo;
	public Label telNumFrom;
	public Label telNumTo;
	public Label goodsNum;
	public Label goodsName;
	public Label weight;
	public Label volume;
	public Label goodsType;
	public Label money;
	public Label type;
	public Label pack;
	
	DeliverBLService dbl = FormFactory.getDeliverBLService();
    public static Parent launch() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CheckRevenueFormController.class.getResource("peopleReceiveForm.fxml"));
        return loader.load();
    }

    public void initialize(){


    }
	
    public void commit(ActionEvent actionEvent) {

		DeliverVO dvo = generateDeliverVO();

		OperationMessage msg = dbl.submit(dvo);




	}

	public DeliverVO generateDeliverVO() {
		return null;
	}

	public void clear(ActionEvent actionEvent) {

	}

	public void saveDraft(ActionEvent actionEvent) {

	}
}
