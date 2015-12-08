package ui.loginui;

import bl.blService.accountblService.AccountBLLoginService;
import bl.blService.accountblService.AccountBLManageService;
import bl.blService.manageblService.ManageblStaffService;
import factory.AccountFactory;
import factory.BLFactory;
import factory.LoginFactory;
import factory.StaffFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.Main;
import message.OperationMessage;
import userinfo.UserInfo;
import vo.accountvo.AccountVO;
import vo.managevo.staff.StaffVO;

import java.io.IOException;
import java.lang.management.ManagementFactory;

/**
 * Created by Sissel on 2015/11/25.
 */
public class LoginController {
    public TextField id_Field;
    public PasswordField password_Field;

    public Image Image_Back= new Image("http://docs.oracle.com/javafx/"
            + "javafx/images/javafx-documentation.png");
    
    public ImageView back_ImageView;
    
    AccountBLLoginService loginService = LoginFactory.getAccountBLLoginService();
    AccountBLManageService manageService = AccountFactory.getManageService();
    ManageblStaffService manageblStaffService = StaffFactory.getManageService();

    public static Parent launch() throws IOException {
        return FXMLLoader.load(LoginController.class.getResource("logIn.fxml"));
    }
    
    
    @FXML
    public void initialize(){
    	back_ImageView.setImage(Image_Back);
    }
    

    public void login(ActionEvent actionEvent) {
        OperationMessage msg = loginService.checkAccount(id_Field.getText(), password_Field.getText());
        if(msg.operationResult){
            StaffVO staffVO = manageblStaffService.searchStaff(id_Field.getText());
            UserInfo.setInfo(staffVO.getID(), staffVO.getStaff(), staffVO.getInstitutionID(), staffVO.getName());

            Main.logIn();
            System.out.println("login successfully");
        }else{
            // TODO tips
            System.out.println("login fail: " + msg.getReason());
        }
    }
}
