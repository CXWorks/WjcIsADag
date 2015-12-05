package ui.deliverui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import message.OperationMessage;
import bl.blService.deliverblService.DeliverBLService;
import bl.blService.receiveblService.ReceiveBLService;
import factory.FormFactory;
import tool.time.TimeConvert;
import ui.loginui.LoginController;
import userinfo.UserInfo;
import vo.delivervo.DeliverVO;
import vo.receivevo.ReceiveVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class deliverController {

	public TextField id_Search_Field;

	public TableColumn<Map.Entry<String, String>, String> ids_Column;
	public TableColumn<Map.Entry<String, String>, String> info_Column;

	public TextField id_Field;  //派送单的ID 应该按照鼠标点击自动生成
	public DatePicker date_DatePicker;
	public ChoiceBox postman_Box;

	DeliverBLService deliverBLService = FormFactory.getDeliverBLService();

	ArrayList<String> toSend = deliverBLService.getUnhandledOrderID(UserInfo.getInstitutionID());
	
	
	public static Parent launch() throws IOException {
		return FXMLLoader.load(LoginController.class.getResource("deliver.fxml"));
	}


	@FXML
	public void initialize(){
		
		
		
		//TODO 
		 // initialize the choice box and the id_Field
		date_DatePicker.setValue(LocalDate.now());

	}

	public void search(ActionEvent e){
		String filter = id_Search_Field.getText();
		
	}

    public void commit(ActionEvent actionEvent) {
        OperationMessage msg = deliverBLService.submit(generateVO(deliverBLService.newID()));

        if(msg.operationResult){
            System.out.println("commit successfully");
            clear(null);
        }else{
            System.out.println("commit fail: " + msg.getReason());
        }
    }

	private DeliverVO generateVO(String formID){
		Calendar calendar = TimeConvert.convertDate(date_DatePicker.getValue());
		return new DeliverVO(formID, id_Field.getText(),calendar,"10000");
	}
	
	public void clear(ActionEvent actionEvent) {
		date_DatePicker.setValue(LocalDate.now());
		id_Field.clear();
		postman_Box.setValue(postman_Box.getItems().get(0));
	}

	public void saveDraft(ActionEvent actionEvent) {
		deliverBLService.saveDraft(generateVO(null));
	}





}
