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
import tool.ui.VisibilityTool;
import ui.accountui.ManageAccountController;
import ui.informui.InformController;
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
import javafx.scene.layout.Pane;
import vo.receivevo.ReceiveVO;

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
    public Button add_Btn;
    public Button save_Btn;
    public Button clear_Btn;
    public Button commit_Btn;

    private RevenueBLService revenueBLService= FinanceBLFactory.getRevenueBLService();
    DeliverBLService deliverBLService = FormFactory.getDeliverBLService();

    String institutionID=UserInfo.getInstitutionID();
	ArrayList<String> postmans;
	ArrayList<String> orderIDs=new ArrayList<String>();
    int money=0;

    private InformController informController;

	public static RevenueFormController launch(){
        try {
            FXMLLoader loader = new FXMLLoader(RevenueFormController.class.getResource("revenueForm.fxml"));
            Pane pane = loader.load();
            RevenueFormController controller = loader.getController();
            controller.informController = InformController.newInformController(pane);

            return controller;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static Parent launchInNew(){
        RevenueFormController controller = launch();
        return controller.informController.stackPane;
    }

    public static Parent launchInHistory(ReceiveVO receiveVO){
        RevenueFormController controller = launch();
        VisibilityTool.setInvisible(controller.add_Btn, controller.save_Btn, controller.clear_Btn, controller.commit_Btn);
        controller.showDetail(receiveVO);
        return controller.informController.stackPane;
    }

    private void showDetail(ReceiveVO receiveVO) {

    }

    @FXML
	public void initialize(){
		postmans= deliverBLService.getPostman(institutionID);
		deliver_ChoiceBox.setItems(FXCollections.observableArrayList(postmans));
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
    	OperationMessage msg = revenueBLService.submit(generateRevenueVO(revenueBLService.newID()));
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
    	money=0;
    	total_Label.setText(money+"");

    	revenues_TableView.getItems().clear();
    }
}
