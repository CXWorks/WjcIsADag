package ui.hallui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import message.OperationMessage;
import factory.FinanceBLFactory;
import factory.FormFactory;
import bl.blService.deliverblService.DeliverBLService;
import bl.blService.financeblService.RevenueBLService;
import po.orderdata.DeliverTypeEnum;
import tool.time.TimeConvert;
import tool.ui.SimpleEnumProperty;
import ui.accountui.ManageAccountController;
import userinfo.UserInfo;
import vo.financevo.BankAccountVO;
import vo.financevo.PaymentVO;
import vo.financevo.RevenueVO;
import vo.managevo.staff.StaffVO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;

/**
 * Created by Sissel on 2015/11/27.
 */
public class RevenueFormController {
    public TableView<Revenue> revenues_TableView;
    public TableColumn<Revenue,String> money_TableColumn;
    public TableColumn<Revenue,String> order_TableColumn;
    public DatePicker revenue_DatePicker;
    public TextField money_Field;
    public TextField order_Field;

    public Label total_Label;
    public ChoiceBox<String> deliver_ChoiceBox;

    private RevenueBLService revenueBLService= FinanceBLFactory.getRevenueBLService();
    DeliverBLService deliverBLService = FormFactory.getDeliverBLService();

    String institutionID=UserInfo.getInstitutionID();
	ArrayList<String> postmans;
	ArrayList<String> orderIDs=new ArrayList<String>();
    int money=0;

	public static Parent launch() throws IOException {
        return FXMLLoader.load(RevenueFormController.class.getResource("revenueForm.fxml"));
    }

	@FXML
	public void initialize(){
		postmans= deliverBLService.getPostman(institutionID);
		deliver_ChoiceBox.setItems(FXCollections.observableArrayList(postmans));
//		deliver_ChoiceBox.getItems().get(0);
		revenue_DatePicker.setValue(LocalDate.now());
	}


    public void add(ActionEvent actionEvent) {
    	orderIDs.add(order_Field.getText());
    	money+=Integer.parseInt(money_Field.getText());
    	total_Label.setText(money+"");

    	revenues_TableView.getItems().add(new Revenue(order_Field.getText(),money_Field.getText()));
    	money_TableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getMoney())
                );
    	order_TableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getOrder())
                );

    	order_Field.clear();
    	money_Field.clear();
    	}

    public void saveDraft(ActionEvent actionEvent) {
    	revenueBLService.saveDraft(generateRevenueVO(null));
    }

    public void commit(ActionEvent actionEvent) {
    	RevenueVO revenueVO=generateRevenueVO(revenueBLService.getNewRevenueID(Calendar.getInstance()));
    	OperationMessage msg = revenueBLService.submit(revenueVO);
    	clear(null);
        if(msg.operationResult){
            System.out.println("commit successfully");
            clear(null);
        }else{
            System.out.println("commit fail: " + msg.getReason());
        }
    }

    private RevenueVO generateRevenueVO(String formID){
    	 Calendar calendar = TimeConvert.convertDate(revenue_DatePicker.getValue());
        return new RevenueVO(
                formID,calendar,total_Label.getText(),
                deliver_ChoiceBox.getValue().toString(),institutionID,orderIDs,UserInfo.getUserID()
        );

    }


    public void clear(ActionEvent actionEvent) {
    	revenue_DatePicker.setValue(LocalDate.now());
    	money_Field.clear();
    	order_Field.clear();
    	deliver_ChoiceBox.setValue(deliver_ChoiceBox.getItems().get(0));
    	orderIDs.clear();

    	revenues_TableView.getItems().clear();
    	money=0;
    	total_Label.setText(money+"");
    }




}
