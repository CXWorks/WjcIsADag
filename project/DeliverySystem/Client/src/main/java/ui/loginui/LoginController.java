package ui.loginui;

import java.io.IOException;

import bl.blService.accountblService.AccountBLLoginService;
import bl.blService.accountblService.AccountBLManageService;
import bl.blService.manageblService.ManageblStaffService;
import factory.AccountFactory;
import factory.LoginFactory;
import factory.StaffFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import main.Main;
import message.OperationMessage;
import ui.informui.InformController;
import userinfo.UserInfo;
import vo.managevo.staff.StaffVO;

/**
 * Created by Sissel on 2015/11/25.
 */
public class LoginController {
    public TextField id_Field;
    public PasswordField password_Field;

//    public Image Image_Back= new Image("pic\background.png");
//    public Image Image_Name = new Image();
//    public Image Image_Password = new Image();
//    public Image Image_Sure = new Image();


//    public ImageView back_ImageView;
//    public ImageView name_ImageView;
//    public ImageView password_ImageView;
//    public ImageView sure_ImageView;

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
}
