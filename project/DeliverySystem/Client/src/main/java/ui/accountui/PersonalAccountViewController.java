package ui.accountui;

import java.io.IOException;

import bl.blService.accountblService.AccountBLManageService;
import factory.AccountFactory;
import main.Main;
import ui.financeui.AccountEditDialogController;
import userinfo.UserInfo;
import vo.accountvo.AccountVO;
import vo.financevo.BankAccountVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * Created by Sissel on 2015/11/27.
 */
public class PersonalAccountViewController {
    public ImageView icon_ImageView;
    public Label staff_Label;
    public Label id_Label;

    AccountBLManageService accountManageService = AccountFactory.getManageService();
	public static Parent launch() throws IOException {
        return FXMLLoader.load(PersonalAccountViewController.class.getResource("personAccountView.fxml"));
    }
    
	 @FXML
	    public void initialize(){
//		 staff_Label.setText(UserInfo.getStaffType().toString());
//		 id_Label.setText(UserInfo.getUserID());
	 }
	
    
    public void editPassword(ActionEvent actionEvent)  throws IOException {
        AccountVO AccountVO = accountManageService.getAccountVO(UserInfo.getUserID());
        personAccountViewEditDialogController controller = personAccountViewEditDialogController.newDialog
                (AccountVO);

        controller.stage.showAndWait();

    }

    public void logout(ActionEvent actionEvent) {
        Main.logOut();
    }
}
