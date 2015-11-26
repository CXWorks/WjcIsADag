package ui.configurationui;

import java.io.IOException;

import ui.loginui.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

public class ModifySaralyController {

	public TextField postman_Basic_Field;
	public TextField postman_Items_Field;
	public TextField postman_Awards_Field;
	
	public TextField hall_Basic_Field;
	public TextField hall_Items_Field;
	public TextField hall_Awards_Field;
	
	public TextField center_Basic_Field;
	public TextField center_Items_Field;
	public TextField center_Awards_Field;
	
	public TextField store_Basic_Field;
	public TextField store_Items_Field;
	public TextField store_Awards_Field;
	
	public TextField driver_Basic_Field;
	public TextField driver_Items_Field;
	public TextField driver_Awards_Field;
	
	public TextField finance_Basic_Field;
	public TextField finance_Items_Field;
	public TextField finance_Awards_Field;
	
	public TextField manager_Basic_Field;
	public TextField manager_Items_Field;
	public TextField manager_Awards_Field;
	
	
	 public static Parent launch() throws IOException {
	        return FXMLLoader.load(LoginController.class.getResource("modifySalary.fxml"));
	    }
	    
	    @FXML
	    public void initialize(){
	    	
	    }
	 
	 
	public void commit(ActionEvent e){
		
	}
}
