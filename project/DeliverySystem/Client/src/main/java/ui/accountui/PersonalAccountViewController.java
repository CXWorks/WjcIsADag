package ui.accountui;

import java.io.IOException;

import javafx.event.ActionEvent;
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

    
	public static Parent launch() throws IOException {
        return FXMLLoader.load(PersonalAccountViewController.class.getResource("personAccountView.fxml"));
    }
    
    
    
    public void editPassword(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) {
    }
}
