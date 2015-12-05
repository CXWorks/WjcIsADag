package ui.examineui;

import java.io.IOException;
import java.util.List;

import factory.ExamineFactory;
import bl.blService.examineblService.ExamineblManageService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
	private ObservableList<Tab> tabs;
	
	
	private ExamineblManageService examineblManageService=ExamineFactory.getExamineblManageService();
	private FormTableController formTableController;
	
	
	 public static Parent launch() throws IOException {
	        return FXMLLoader.load(LoginController.class.getResource("checkFrom.fxml"));
	 }
	 
	 public void initialize(){
		 tabs=tabPane.getTabs();
	 }
	
	public void selectedChanged(){
		for (Tab tab : tabs) {
			if (tab.isSelected()) {
				//TODO do something
				
			}
		}
	}
	
	public void pass(){
		
	}
	
	public void fail(){
		
	}
	
	public void delete(){
		
	}
	public void selectAll(){
		
	}
	
}
