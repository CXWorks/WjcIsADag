package ui.examineui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



import po.FormEnum;
import factory.ExamineFactory;
import bl.blService.examineblService.ExamineblManageService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import ui.loginui.LoginController;
import vo.FormVO;

public class CheckFormController implements EventHandler<Event>{

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
	public Tab selectedTab;
	//
	//
	@FXML
	public ObservableList<Tab> tabs;

	@FXML
	public ExamineblManageService examineblManageService=ExamineFactory.getExamineblManageService();
	@FXML
	public FormTableController formTableController;
	
	public ArrayList<FormVO> unhandledForms;
	
	public FormEnum nowFormEnum=null;


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
			this.formTableController=(FormTableController)fxmlLoader.getController();
			for (Tab tab : tabs) {
				tab.setOnSelectionChanged(this);
			}
			tabPane.getSelectionModel().selectFirst();
			selectedTab=tabPane.getSelectionModel().getSelectedItem();
			this.handleSelection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }

//	public void selectedChanged(EventHandler<Event> value){
//		value.handle(event);
//		this.tabs=tabPane.getTabs();
//		for (Tab tab : tabs) {
//			if (tab.isSelected()) {
//				String text=tab.getText();
//				nowFormEnum=this.getFormEnum(text);
//				formTableController.change(nowFormEnum);
//			}
//		}
//	}

	public void pass(){
		ArrayList<FormVO> temp=formTableController.getSelected();
		examineblManageService.passForm(temp);
		unhandledForms=examineblManageService.getForms(nowFormEnum);
	}

	public void delete(){
		ArrayList<FormVO> temp=formTableController.getSelected();
		examineblManageService.deleteForm(temp);
		unhandledForms=examineblManageService.getForms(nowFormEnum);
	}
	public void selectAll(){
		formTableController.selectAll();
	}
	//
	public void refresh(){
		examineblManageService.refresh();
		unhandledForms=examineblManageService.getForms(nowFormEnum);
		formTableController.change(nowFormEnum);
	}
	
	public void oneKey(){
		this.selectAll();
		this.pass();
	}
	
	private FormEnum getFormEnum(String text){
		if (text==null) {
			return null;
		}
		//
		switch (text) {
		case "全部单据":
			return null;
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

	/* (non-Javadoc)
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	@Override
	public void handle(Event event) {
		Tab unknown=(Tab)event.getSource();
		if (unknown==selectedTab) {
			return;
		}else {
			selectedTab=unknown;
			this.handleSelection();
		}
	}
	//
	private void handleSelection(){
		
//		System.out.println("used"+selectedTab.getText());
		String text=selectedTab.getText();
		nowFormEnum=this.getFormEnum(text);
		formTableController.change(nowFormEnum);
		selectedTab.setContent(formTableController.tableView);
	}
}
