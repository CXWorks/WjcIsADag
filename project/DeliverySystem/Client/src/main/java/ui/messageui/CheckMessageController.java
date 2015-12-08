package ui.messageui;

import java.io.IOException;
import java.util.ArrayList;

import message.ChatMessage;
import tool.time.TimeConvert;
import ui.financeui.ManageBankAccountController;
import userinfo.UserInfo;
import vo.financevo.BankAccountVO;
import bl.blService.accountblService.AccountBLRemindService;
import factory.AccountFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Created by Sissel on 2015/11/27.
 */
public class CheckMessageController {
	public TableView<ChatMessage> message_View;
//    public TableColumn<ChatMessage,String> check_TableColumn;
    public TableColumn<ChatMessage,String> time_TableColumn;
    public TableColumn<ChatMessage,String> message_TableColumn;

    
    AccountBLRemindService accountblremindService = AccountFactory.getRemindService();
    ArrayList<ChatMessage> chatMessage=accountblremindService.receive(UserInfo.getInstitutionID());
    
 
    public static Parent launch() throws IOException {
        return FXMLLoader.load(CheckMessageController.class.getResource("checkMessage.fxml"));
    }

    @FXML
    public void initialize(){
    	
    	message_View.setItems(
                FXCollections.observableArrayList(chatMessage)
                		);
    	time_TableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(TimeConvert.getDisplayDate(cellData.getValue().getTime()))
                );
    	message_TableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getMessage())
                );
    }
    
    
    public void selectAll(ActionEvent actionEvent) {
    	
    }

    public void markChecked(ActionEvent actionEvent) {
    	
    }

    public void delete(ActionEvent actionEvent) {
    	
    }
}
