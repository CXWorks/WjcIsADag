package ui.connectui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Main;
import ui.common.SettingDialogController;

import java.io.IOException;

/**
 * Created by Sissel on 2015/12/29.
 */
public class ConnectDialogController {

    private Stage dialog;

    public static ConnectDialogController newConnectDialog() throws IOException {
        FXMLLoader loader = new FXMLLoader(ConnectDialogController.class.getResource("connectDialog.fxml"));
        Pane pane = loader.load();
        ConnectDialogController controller = loader.getController();

        controller.dialog = new Stage();
        
        controller.dialog.initStyle(StageStyle.UNDECORATED);
        
        controller.dialog.initOwner(Main.primaryStage);
        controller.dialog.setScene(new Scene(pane));

        return controller;
    }


    public void popUpSetting(ActionEvent actionEvent) {
        Stage settingDialog = SettingDialogController.newDialog(this.dialog);
        settingDialog.showAndWait();
    }

    public void exitSystem(ActionEvent actionEvent) {
    	System.exit(0);

    }

    public void showDialog(){
        dialog.showAndWait();
    }

    public void closeDialog(){
        dialog.close();
    }
}
