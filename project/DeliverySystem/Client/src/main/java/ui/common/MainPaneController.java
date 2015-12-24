package ui.common;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import main.Main;
import ui.informui.InformController;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import bl.clientNetCache.CacheHelper;
import bl.clientNetCache.dataProxy.ConfigurationDataProxy;

/**
 * Created by Sissel on 2015/12/19.
 */
public class MainPaneController {
    public AnchorPane title_Pane;
    public FlowPane left_TabsPane;
    public AnchorPane content_Pane;
    public VBox tabs_VBox;
    private BorderPane borderPane;

    public static MainPaneController launch() throws IOException {
        FXMLLoader loader = new FXMLLoader(MainPaneController.class.getResource("mainPane.fxml"));
        BorderPane pane = loader.load();
        MainPaneController controller = loader.getController();
        controller.borderPane = pane;

        return controller;
    }

    public void addTabButton(String name, Parent content){
        Button tabButton = new Button(name);
        tabs_VBox.getChildren().add(tabButton);
        tabButton.setOnAction(
                actionEvent -> {
                    content_Pane.getChildren().clear();
                    content_Pane.getChildren().add(content);
                }
        );
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public void closeStage(ActionEvent actionEvent) {
    	ConfigurationDataProxy.Close();
        Main.primaryStage.close();
    }

    public void maximizeStage(ActionEvent actionEvent) {
        Main.primaryStage.setFullScreen(true);
    }

    public void minimizeStage(ActionEvent actionEvent) {
        Main.primaryStage.toBack();
    }
}
