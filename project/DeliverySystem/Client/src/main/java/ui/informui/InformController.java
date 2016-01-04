package ui.informui;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import main.Main;
import message.OperationMessage;
import tool.ui.AnchorSet;

import java.io.IOException;

/**
 * Created by Sissel on 2015/12/19.
 */
public class InformController {
    public AnchorPane stackPane;
    public Label content_Label;
    public HBox informPane;

    public static InformController newInformController(Pane contentPane) {
        FXMLLoader loader = new FXMLLoader(InformController.class.getResource("inform.fxml"));
        try {
            HBox informPane = loader.load();
            InformController controller = loader.getController();
            controller.stackPane = new AnchorPane(contentPane);
            AnchorSet.set0(contentPane);
            AnchorSet.set0(controller.stackPane);
            controller.stackPane.getChildren().add(informPane);
            AnchorPane.setTopAnchor(informPane, 0.0);
            AnchorPane.setLeftAnchor(informPane, 0.0);
            AnchorPane.setRightAnchor(informPane, 0.0);
            informPane.setVisible(false);
            controller.informPane = informPane;

            return controller;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void inform(String info){
        content_Label.setText(info);
        if(! informPane.isVisible()){ // do not new another thread if is already shown
            new Thread(
                    () -> {
                        try {
                            Thread.sleep(3000);
                            Platform.runLater(
                                    () -> informPane.setVisible(false)
                            );
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            ).start();
        }
        informPane.setVisible(true);
        // debug
        System.out.println(info);
    }

    public void inform(OperationMessage msg, String successWord){
        if(msg.operationResult){
            this.inform(successWord);
        }else{
            this.inform("操作失败： " + msg.getReason());
        }
    }

}
