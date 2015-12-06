package ui.examineui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import po.FormEnum;
import factory.ExamineFactory;
import bl.blService.examineblService.ExamineblManageService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import ui.loginui.LoginController;
import vo.FormVO;

public class CheckFormController {

	public Tab all_Tab;
	public Tab order_Tab;
	public Tab hall_Arrive_Tab;
	public Tab center_Arrive_Tab;
	public Tab store_In_Tab;
	public Tab store_Out_Tab;
	public Tab deliver_Tab;
	public Tab load_Tab;
	public Tab transit_Tab;
	public Tab payment_Tab;
	public Tab Revenue_Tab;
	
	public CheckBox chooseAll_Box;
	
	public Label pass;
	public Label deny;
	public Label modify;
	
	public TabPane tabPane;
	//
	//
	@FXML
	public ObservableList<Tab> tabs;
	
	@FXML
	public ExamineblManageService examineblManageService=ExamineFactory.getExamineblManageService();
	@FXML
	public FormTableController formTableController;
	
	
	 public static Parent launch() throws IOException {
	       FXMLLoader fxmlLoader=new FXMLLoader();
	       fxmlLoader.setLocation(CheckFormController.class.getResource("checkForm.fxml"));
	       return fxmlLoader.load();
	 }
	 
	 public void initialize(){
		 tabs=tabPane.getTabs();
		 FXMLLoader fxmlLoader=new FXMLLoader();
		 fxmlLoader.setLocation(FormTableController.class.getResource("FormTableView.fxml"));
		 try {
			Parent son=(Parent)fxmlLoader.load();
			for (Tab tab : tabs) {
				tab.setContent(son);
			}
			this.formTableController=(FormTableController)fxmlLoader.getController();
			if (formTableController==null) {
				System.out.println("isnull");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 this.selectedChanged();
	 }
	
	public void selectedChanged(){
		if (formTableController==null) {
			return;
		}
		this.tabs=tabPane.getTabs();
		for (Tab tab : tabs) {
			if (tab.isSelected()) {
				//TODO do something
				String text=tab.getText();
				FormEnum formEnum=this.getFormEnum(text);
				System.out.println(formEnum);
				formTableController.change(formEnum);
				tab.setContent(formTableController.tableView);
			}
		}
	}
	
	public void pass(){
		ArrayList<FormVO> temp=formTableController.getSelected();
		examineblManageService.passForm(temp);
	}
	
	public void fail(){
		
	}
	
	public void delete(){
		ArrayList<FormVO> temp=formTableController.getSelected();
	}
	public void selectAll(){
		formTableController.selectAll();
	}
	private FormEnum getFormEnum(String text){
		if (text==null) {
			return null;
		}
		//
		switch (text) {
		case "订单":
			return FormEnum.ORDER;
		case "派件单":
			return FormEnum.DELIVER;
		case "付款单":
			return FormEnum.PAYMENT;
		case "收款单":
			return FormEnum.REVENUE;
		case "到达单":
			return FormEnum.RECEIVE;
		case "营业厅中转单":
			return FormEnum.TRANSPORT_HALL;
		case "中转中心中转单":
			return FormEnum.TRANSPORT_CENTER;
		case "入库单":
			return FormEnum.STORE_IN;
		case "出库单":
			return FormEnum.STORE_OUT;

		default:
			return null;
		}
	}
}
