package ui.hallui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;

import message.OperationMessage;
import factory.FinanceBLFactory;
import bl.blService.financeblService.RevenueBLService;
import po.orderdata.DeliverTypeEnum;
import tool.time.TimeConvert;
import tool.ui.SimpleEnumProperty;
import ui.accountui.ManageAccountController;
import vo.financevo.PaymentVO;
import vo.financevo.RevenueVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;

/**
 * Created by Sissel on 2015/11/27.
 */
public class RevenueFormController {
    public TableView revenues_TableView;
    public TableColumn date_TableColumn;
    public TableColumn money_TableColumn;
    public TableColumn deliver_TableColumn;
    public TableColumn order_TableColumn;
    public DatePicker revenue_DatePicker;
    public TextField money_Field;
    public TextField order_Field;
    //这个是快递员的编号，，等jr搞定我来搬砖orz
    public ChoiceBox<SimpleEnumProperty<DeliverTypeEnum>> deliver_ChoiceBox;
    
    public String institutionID="1001";
    //怎么获得本地营业厅的编号还木有想好
    
    private RevenueBLService revenueBLService= FinanceBLFactory.getRevenueBLService();
	public static Parent launch() throws IOException {
        return FXMLLoader.load(RevenueFormController.class.getResource("revenueForm.fxml"));
    }

    public void add(ActionEvent actionEvent) {
    	//TODO
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
        return new RevenueVO(
                formID,calendar,money_Field.getText(),
                deliver_ChoiceBox.getValue().toString(),institutionID,order_Field.getText()
        );
    }
    
    
    public void clear(ActionEvent actionEvent) {
    	revenue_DatePicker.setValue(LocalDate.now());
    	money_Field.clear();
    	order_Field.clear();
    	deliver_ChoiceBox.setValue(deliver_ChoiceBox.getItems().get(0));
    }
}
