package ui.manangeui;




import java.io.IOException;

import po.InfoEnum;
import ui.loginui.LoginController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

public class ManageOraganizationController {

	public Tab Beijing_Tab;
	public Tab Nanjing_Tab;
	public Tab Shanghai_Tab;
	public Tab Guangzhou_Tab;
	
	public TextField address_Field;
	public ChoiceBox<String> type_Box;
	public TextField stuff_Number_Field;
	
	private final static String [] type={"营业厅","中转中心"};
	private InfoEnum organiEnum=InfoEnum.HALL;

	    public static Parent launch() throws IOException {
	        return FXMLLoader.load(LoginController.class.getResource("manageOraganization.fxml"));
	    }
	    
	    

		public void initialize(){
			// initialize the choice box
			type_Box.setItems(FXCollections.observableArrayList(type));
			type_Box.getSelectionModel().selectedItemProperty().addListener(
					(observable, oldValue, newValue) -> {
						switch (newValue) {
							case "营业厅":
								organiEnum = organiEnum.HALL;
								break;
							case "中转中心":
								organiEnum = organiEnum.CENTER;
								break;
						}
					}
			);
			clear(null);
		}
	    
	    public void commit(ActionEvent e){
	    	
	    }
	    
	    public void clear(ActionEvent e){
	    	
	    	address_Field.clear();
	    	stuff_Number_Field.clear();
	    	
	    	
	    	
	    }
	    
	    public void saveDraft(ActionEvent e){
	    	
	    }
	    
	    
}
