package ui.accountui;

import java.io.IOException;

import bl.blService.accountblService.AccountBLManageService;
import factory.AccountFactory;
import main.Main;
import ui.common.checkFormat.field.PasswordOnlyField;
import ui.financeui.AccountEditDialogController;
import ui.financeui.AccountEditDialogController.EditType;
import ui.informui.InformController;
import vo.accountvo.AccountVO;
import vo.financevo.BankAccountVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class personAccountViewEditDialogController {

	public PasswordOnlyField originalPassWord_Field;
	public PasswordOnlyField newPassWord_Field;
	public PasswordOnlyField againNewPassWord_Field;

	public Label attention_Label;

	private AccountVO editVO =  new AccountVO();
    private AccountBLManageService accountBLManageService = AccountFactory.getManageService();
	public Stage stage;

	private InformController informController;

	public static personAccountViewEditDialogController newDialog(AccountVO editVO) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(personAccountViewEditDialogController.class.getResource("personAccountViewEditDialog.fxml"));
		Pane pane = loader.load();

		Stage stage = new Stage();
		stage.initOwner(Main.primaryStage);
		stage.setScene(new Scene(pane));

		personAccountViewEditDialogController controller = loader.getController();
		controller.informController = InformController.newInformController(pane);

		controller.editVO = editVO;
		controller.stage = stage;

		return controller;
	}

	public boolean check(String pw0,String pw1,String pw2){
		if(pw1.equals(pw2) && pw0.equals(editVO.password) ){
			return true;
		}if( !pw1.equals(pw2) ){
			originalPassWord_Field.clear();
			newPassWord_Field.clear();
			againNewPassWord_Field.clear();
			attention_Label.setText("请两次输入相同新密码！");
			return false;
		}else{
			originalPassWord_Field.clear();
			newPassWord_Field.clear();
			againNewPassWord_Field.clear();
			attention_Label.setText("请输入正确的原始密码！");
			return false;

		}

	}


	public void ok(ActionEvent actionEvent) {
		if(check(originalPassWord_Field.getText(),
				newPassWord_Field.getText(),againNewPassWord_Field.getText())){
			editVO.password = newPassWord_Field.getText();

			System.out.println("modify password successfully!");

            editVO.password = newPassWord_Field.getText();
			accountBLManageService.modifyAccount(editVO);

			stage.close();
		}else{
			System.out.println("modify password failed!");
		}
	}

	public void cancel(ActionEvent actionEvent) {
		stage.close();
	}



}
