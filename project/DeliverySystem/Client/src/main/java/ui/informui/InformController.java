package ui.informui;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import main.Main;
import message.OperationMessage;

import java.io.IOException;

/**
 * Created by Sissel on 2015/12/19.
 */
public class InformController {
    public StackPane stackPane;
    public Label content_Label;
    public Pane informPane;

    public static InformController newInformController(Pane contentPane) {
        FXMLLoader loader = new FXMLLoader(InformController.class.getResource("inform.fxml"));
        try {
            Pane informPane = loader.load();
            InformController controller = loader.getController();
            controller.stackPane = new StackPane(contentPane);
            controller.informPane = informPane;

            return controller;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void inform(String info){
        content_Label.setText(info);
        stackPane.getChildren().add(informPane);
        // sleep for a while
        new Thread(
                () -> {
                    try {
                        Thread.sleep(1700);
                        Platform.runLater(
                                () -> stackPane.getChildren().remove(informPane)
                        );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        ).start();
    }

    public void inform(OperationMessage msg, String successWord){
        if(msg.operationResult){
            this.inform(successWord);
        }else{
            this.inform("操作失败： " + msg.getReason());
        }
    }

}
