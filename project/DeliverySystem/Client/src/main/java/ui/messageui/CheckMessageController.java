package ui.messageui;

import java.io.IOException;
import java.util.ArrayList;

import message.ChatMessage;
import tool.time.TimeConvert;
import ui.financeui.ManageBankAccountController;
import ui.hallui.RevenueFormController;
import ui.informui.InformController;
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
import javafx.scene.layout.Pane;

/**
 * Created by Sissel on 2015/11/27.
 */
public class CheckMessageController {
	public TableView<ChatMessage> message_View;
//    public TableColumn<ChatMessage,String> check_TableColumn;
    public TableColumn<ChatMessage,String> time_TableColumn;
    public TableColumn<ChatMessage,String> message_TableColumn;


    AccountBLRemindService accountblremindService = AccountFactory.getRemindService();
    ArrayList<ChatMessage> chatMessage=accountblremindService.receive(UserInfo.getUserID());

    private InformController informController;

    public static Parent launch() throws IOException {
        FXMLLoader loader = new FXMLLoader(CheckMessageController.class.getResource("checkMessage.fxml"));
        Pane pane = loader.load();
        CheckMessageController controller = loader.getController();
        controller.informController = InformController.newInformController(pane);

        return controller.informController.stackPane;
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
