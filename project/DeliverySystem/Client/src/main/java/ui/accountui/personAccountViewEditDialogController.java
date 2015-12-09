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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class personAccountViewEditDialogController {

	public PasswordField originalPassWord_Field;
	public PasswordField newPassWord_Field;
	public PasswordField againNewPassWord_Field;

	public Label attention_Label;

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

	public boolean check(String pw0,String pw1,String pw2){
		if(pw1==pw2&&pw0==editVO.password){
			return true;
		}if(pw1!=pw2){
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

			stage.close();
		}else{
			System.out.println("modify password failed!");
		}
	}

	public void cancel(ActionEvent actionEvent) {
		stage.close();
	}



}
