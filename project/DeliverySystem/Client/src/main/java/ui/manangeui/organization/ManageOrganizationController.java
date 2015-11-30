package ui.manangeui.organization;




import java.io.IOException;

import po.InfoEnum;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

public class ManageOrganizationController {

	public Tab Beijing_Tab;
	public Tab Nanjing_Tab;
	public Tab Shanghai_Tab;
	public Tab Guangzhou_Tab;

	public TextField address_Field;
	public ChoiceBox<String> type_Box;
	public TextField stuff_Number_Field;

	private final static String [] type={"营业厅","中转中心"};
	private boolean isHall;

	    public static Parent launch() throws IOException {
	    	FXMLLoader contentLoader = new FXMLLoader();
	        contentLoader.setLocation(ManageOrganizationController.class.getResource("manageOrganization.fxml"));
	        return contentLoader.load();
	    }


	    @FXML
		public void initialize(){
			// initialize the choice box
			type_Box.setItems(FXCollections.observableArrayList(type));
			type_Box.getSelectionModel().selectedItemProperty().addListener(
					(observable, oldValue, newValue) -> {
						switch (newValue) {
							case "营业厅":
								this.isHall=true;
								break;
							case "中转中心":
								this.isHall=false;
								break;
						}
					}
			);
		}

	    public void commit(ActionEvent e){

	    }

	    public void clear(ActionEvent e){

	    	address_Field.clear();
	    	stuff_Number_Field.clear();
	    	type_Box.setValue(type_Box.getItems().get(0));


	    }

	    public void saveDraft(ActionEvent e){

	    }
}
