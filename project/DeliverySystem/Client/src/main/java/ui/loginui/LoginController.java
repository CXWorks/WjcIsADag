package ui.loginui;

import java.io.IOException;
import java.util.Map;

import bl.blService.accountblService.AccountBLLoginService;
import bl.blService.accountblService.AccountBLManageService;
import bl.blService.manageblService.ManageblStaffService;
import bl.blService.searchblService.SearchBLService;
import factory.AccountFactory;
import factory.LoginFactory;
import factory.SearchFactory;
import factory.StaffFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
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
import tool.ui.LogisticsVO2ColumnHelper;
import ui.informui.InformController;
import userinfo.UserInfo;
import vo.logisticsvo.LogisticsVO;
import vo.managevo.staff.StaffVO;

/**
 * Created by Sissel on 2015/11/25.
 */
public class LoginController {
    public TextField id_Field;
    public PasswordField password_Field;

    public TextField search_Field;

	public TableView<Map.Entry<String, String>> logistics_TableView;
	public TableColumn<Map.Entry<String, String>, String> time_Column;
	public TableColumn<Map.Entry<String, String>, String> address_Column;

	SearchBLService searchblService = SearchFactory.getSearchBLService();

	private LogisticsVO logisticsvo=new LogisticsVO();

    private AccountBLLoginService loginService = LoginFactory.getAccountBLLoginService();
    private ManageblStaffService manageblStaffService;

    public static Parent launch() throws IOException {
        FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("logIn.fxml"));
        Pane content = loader.load();

        return content;
    }


//    @FXML
//    public void initialize(){
//    	back_ImageView.setImage(Image_Back);
//    }

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

    public void search(ActionEvent atcionEvent){
		logistics_TableView.getItems().clear();
//		System.out.println(search_Field.getText());
		logisticsvo=searchblService.searchOrder(
				search_Field.getText());
//		System.out.println(logisticsvo.getLocation().toString());
        if(this.logisticsvo == null){
            return;
        }

        LogisticsVO2ColumnHelper.setKeyColumn(time_Column);
        LogisticsVO2ColumnHelper.setValueColumn(address_Column);
        logistics_TableView.setItems(FXCollections.observableArrayList(new LogisticsVO2ColumnHelper().VO2Entries(logisticsvo)));

	}
}
