package ui.messageui;

import java.io.IOException;

import ui.financeui.ManageBankAccountController;
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
	public TableView message_View;
    public TableColumn check_TableColumn;
    public TableColumn time_TableColumn;
    public TableColumn message_TableColumn;

    AccountBLRemindService accountblremindService = AccountFactory.getRemindService();
    
    
    public static Parent launch() throws IOException {
        return FXMLLoader.load(CheckMessageController.class.getResource("checkMessage.fxml"));
    }

    @FXML
    public void initialize(){
        
    }
    
    
    public void selectAll(ActionEvent actionEvent) {
    	
    }

    public void markChecked(ActionEvent actionEvent) {
    }

    public void delete(ActionEvent actionEvent) {

    }
}
