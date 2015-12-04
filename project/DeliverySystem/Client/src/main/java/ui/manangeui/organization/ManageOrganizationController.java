package ui.manangeui.organization;




import java.io.IOException;

import com.sun.glass.ui.EventLoop.State;

import po.InfoEnum;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class ManageOrganizationController {

	public Tab Beijing_Tab;
	public Tab Nanjing_Tab;
	public Tab Shanghai_Tab;
	public Tab Guangzhou_Tab;
	
	public WebView webView;

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
			//
			WebEngine webEngine=this.webView.getEngine();
			if (webEngine==null) {
				System.out.println("null");
			}
			this.webView.getEngine().getLoadWorker().stateProperty().addListener(
					(observable,oldState,newState)->{if (newState==javafx.concurrent.Worker.State.SUCCEEDED){
						System.out.println("test");
					} {
				
			}});
			webEngine.load("file:///D:/文档/GitHub/WjcIsADag/project/DeliverySystem/Client/src/main/java/ui/manangeui/organization/map.html");
//			this.webView.getEngine().load("http://javafx.com");
//			webEngine.executeScript("var map = new BMap.Map(\"allmap\");   map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);  map.addControl(new BMap.MapTypeControl());   map.setCurrentCity(\"北京\");         map.enableScrollWheelZoom(true);    ");
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
