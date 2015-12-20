package ui.storeui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Main;
import ui.informui.InformController;

import java.io.IOException;

/**
 * Created by Sissel on 2015/12/8.
 */

public class EditShelfDialogController {
    public Label err_Label;
    public TextField number_Field;

    private Stage stage;
    private int shelfNum;


    /**
     *
     * @param stage where dialog is in
     * @return
     * @throws IOException
     */
    public static EditShelfDialogController newDialog(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EditShelfDialogController.class.getResource("editShelfDialog.fxml"));
        Pane pane = loader.load();

        EditShelfDialogController controller = loader.getController();

        controller.stage = stage;
        stage.initOwner(Main.primaryStage);
        stage.setScene(new Scene(pane));

        return controller;
    }

    public void cancel(ActionEvent actionEvent) {
        stage.close();
    }

    public void commit(ActionEvent actionEvent) {
        try{
            shelfNum = Integer.parseInt(number_Field.getText());
            stage.close();
        }catch (NumberFormatException exception) {
            err_Label.setText("please enter integer");
        }
    }

    public int getShelfNum(){
        return shelfNum;
    }
}
