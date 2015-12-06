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
    public TableView<RevenueVO> revenues_TableView;
    public TableColumn<RevenueVO,String> date_TableColumn;
    public TableColumn<RevenueVO,String> money_TableColumn;
    public TableColumn<RevenueVO,String> deliver_TableColumn;
    public TableColumn<RevenueVO,String> order_TableColumn;
    public DatePicker revenue_DatePicker;
    public TextField money_Field;
    public TextField order_Field;
    
    public Label total_Label;
    private RevenueBLService revenueBLService= FinanceBLFactory.getRevenueBLService();
    DeliverBLService deliverBLService = FormFactory.getDeliverBLService();
    public ChoiceBox<String> deliver_ChoiceBox;
    
	ArrayList<String> postmans= deliverBLService.getPostman(UserInfo.getInstitutionID());
    private RevenueVO  revenueVO = new RevenueVO("");
    
    public String institutionID=UserInfo.getInstitutionID();
    
   
    
	public static Parent launch() throws IOException {
        return FXMLLoader.load(RevenueFormController.class.getResource("revenueForm.fxml"));
    }

	@FXML
	public void initialize(){
		deliver_ChoiceBox.setItems(FXCollections.observableArrayList(postmans));
		revenue_DatePicker.setValue(LocalDate.now());
	}
	
	
	
    public void add(ActionEvent actionEvent) {
    	date_TableColumn.setCellValueFactory(
                cellData->new SimpleStringProperty(cellData.getValue().getDate().toString())
        );
	money_TableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getAmount())
        );
	deliver_TableColumn.setCellValueFactory(
            cellData -> new SimpleStringProperty(cellData.getValue().getDeliverName())
    );
	order_TableColumn.setCellValueFactory(
            cellData -> new SimpleStringProperty(cellData.getValue().getOrderIDs().toString())
    );
	revenues_TableView.setItems(
                FXCollections.observableArrayList(

                )
        );
	revenues_TableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    System.out.println("selected " + newValue.formID);
                    
                   // RevenueVO = newValue;
                }
        );
    }

    public void saveDraft(ActionEvent actionEvent) {
    	revenueBLService.saveDraft(generateRevenueVO(null));
    }

    public void commit(ActionEvent actionEvent) {
    	OperationMessage msg = revenueBLService.submit(generateRevenueVO(revenueBLService.newID()));

        if(msg.operationResult){
            System.out.println("commit successfully");
            clear(null);
        }else{
            System.out.println("commit fail: " + msg.getReason());
        }
    }

    private RevenueVO generateRevenueVO(String formID){
    	 Calendar calendar = TimeConvert.convertDate(revenue_DatePicker.getValue());
    	 return null;
//        return new RevenueVO(
//                formID,calendar,money_Field.getText(),
//                deliver_ChoiceBox.getValue().toString(),institutionID,order_Field.getText()
//        );
    }
    
    
    public void clear(ActionEvent actionEvent) {
    	revenue_DatePicker.setValue(LocalDate.now());
    	money_Field.clear();
    	order_Field.clear();
    	deliver_ChoiceBox.setValue(deliver_ChoiceBox.getItems().get(0));
    }
    
    public void Total(ActionEvent actionEvent){
    	
    }
}
