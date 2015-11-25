package ui.manangeui;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
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
	
	
	
	 public static Parent launch() throws IOException {
	        return FXMLLoader.load(LoginController.class.getResource("checkFrom.fxml"));
	    }
	
	
	
	public void pass(ActionEvent e){
		
	}
	
	public void fail(ActionEvent e){
		
	}
	
	public void delete(ActionEvent e){
		
	}
	
	
}
