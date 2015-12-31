package ui.loginui;

import java.io.IOException;







import bl.blService.accountblService.AccountBLLoginService;
import bl.blService.accountblService.AccountBLManageService;
import bl.blService.manageblService.ManageblStaffService;
import bl.blService.searchblService.SearchBLService;
import factory.AccountFactory;
import factory.LoginFactory;
import factory.SearchFactory;
import factory.StaffFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import main.Main;
import message.OperationMessage;
import ui.informui.InformController;
import userinfo.UserInfo;
import vo.logisticsvo.LogisticsInfo;
import vo.logisticsvo.LogisticsVO;
import vo.managevo.staff.StaffVO;

/**
 * Created by Sissel on 2015/11/25.
 */
public class LoginController {
    public TextField id_Field;
    public PasswordField password_Field;

    public TextField search_Field;

	public TableView<LogisticsInfo> logistics_TableView;
	public TableColumn<LogisticsInfo, String> time_Column;
	public TableColumn<LogisticsInfo, String> address_Column;

	SearchBLService searchblService = SearchFactory.getSearchBLService();


    private AccountBLLoginService loginService = LoginFactory.getAccountBLLoginService();
    private ManageblStaffService manageblStaffService;

    public static Parent launch() throws IOException {
        FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("logIn.fxml"));
        Pane content = loader.load();

        return content;
    }


    @FXML
    public void initialize(){
    	time_Column.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getTime()));
    	address_Column.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getInfo()));
    	logistics_TableView.getColumns().clear();
    	logistics_TableView.getColumns().add(time_Column);
    	logistics_TableView.getColumns().add(address_Column);
    }

    public void login(ActionEvent actionEvent) {

        OperationMessage msg = loginService.checkAccount(id_Field.getText(), password_Field.getText());

        if(msg.operationResult){
        	manageblStaffService=StaffFactory.getManageService();
            StaffVO staffVO = manageblStaffService.searchStaff(id_Field.getText());
            if(staffVO == null){
                // not yet make the staff by manager
                // TODO : display tips
                System.out.println("ask the manager to new a staff for you");
            }

            // TODO : display tips
            System.out.println("login successfully");
            Main.logIn();
        }else{
            // TODO : display tips
            System.out.println("login fail: " + msg.getReason());
        }

        password_Field.clear();
    }

    public void search(){
		logistics_TableView.getItems().clear();
		LogisticsVO logisticsvo=searchblService.searchOrder(
				search_Field.getText());
        if(logisticsvo == null){
            return;
        }
       System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuuuuu");
        logistics_TableView.getItems().addAll(logisticsvo.getInfoForTableView());
        
	}
}
