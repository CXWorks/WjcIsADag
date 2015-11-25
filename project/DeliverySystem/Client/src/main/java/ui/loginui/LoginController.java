package ui.loginui;

import bl.blService.accountblService.AccountBLLoginService;
import factory.BLFactory;
import factory.LoginFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import message.OperationMessage;

import java.io.IOException;

/**
 * Created by Sissel on 2015/11/25.
 */
public class LoginController {
    public TextField id_Field;
    public PasswordField password_Field;

    AccountBLLoginService loginService = LoginFactory.getAccountBLLoginService();

    public static Parent launch() throws IOException {
        return FXMLLoader.load(LoginController.class.getResource("logIn.fxml"));
    }

    public void login(ActionEvent actionEvent) {
        OperationMessage msg = loginService.checkAccount(id_Field.getText(), password_Field.getText());
        if(msg.operationResult){
            // TODO jump between scenes
            System.out.println("login successfully");
        }else{
            System.out.println("login fail: " + msg.getReason());
        }
    }
}
