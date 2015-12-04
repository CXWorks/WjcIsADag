package ui.accountui;

import java.io.IOException;

import ui.financeui.AccountEditDialogController;
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
    //public ImageView icon_ImageView;
    public Label staff_Label;
    public Label id_Label;

    
	public static Parent launch() throws IOException {
        return FXMLLoader.load(PersonalAccountViewController.class.getResource("personAccountView.fxml"));
    }
    
	 @FXML
	    public void initialize(){
		 staff_Label.setText("总经理：程翔");
		 id_Label.setText("10010");
	 }
	
    
    public void editPassword(ActionEvent actionEvent)  throws IOException {
    	//获得当前的AccountVO
        AccountVO AccountVO = new AccountVO(null, null, null);
        personAccountViewEditDialogController controller = personAccountViewEditDialogController.newDialog
                (AccountVO);

        controller.stage.showAndWait();

        
        //bankAccountBLService.addAccount(bankAccountVO);
    }

    public void logout(ActionEvent actionEvent) {
    }
}
