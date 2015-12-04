package ui.accountui;

import java.io.IOException;

import main.Main;
import ui.financeui.AccountEditDialogController;
import ui.financeui.AccountEditDialogController.EditType;
import vo.accountvo.AccountVO;
import vo.financevo.BankAccountVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class personAccountViewEditDialogController {

	public TextField originalPassWord_Field;
	public TextField newPassWord_Field;
	public TextField againNewPassWord_Field;
	
	private AccountVO editVO =  new AccountVO();
    public Stage stage;
	
	public static personAccountViewEditDialogController newDialog(AccountVO editVO) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(personAccountViewEditDialogController.class.getResource("personAccountViewEditDialog.fxml"));
        Pane pane = loader.load();

        Stage stage = new Stage();
        stage.initOwner(Main.primaryStage);
        stage.setScene(new Scene(pane));

        personAccountViewEditDialogController controller = (personAccountViewEditDialogController)loader.getController();
        controller.editVO = editVO;
        controller.stage = stage;
        
        return controller;
    }

	public void check(){
		//TODO  检查输入密码以及两次新密码是否正确
		
	}
	
	
    public void ok(ActionEvent actionEvent) {
        // TODO check
        editVO.password = newPassWord_Field.getText();
        
        stage.close();
    }

    public void cancel(ActionEvent actionEvent) {
        stage.close();
    }
	
	
	
}
