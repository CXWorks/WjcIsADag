package ui.common;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Main;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Sissel on 2015/12/19.
 */
public class MainPaneController {
    public AnchorPane title_Pane;
    public FlowPane left_TabsPane;
    public AnchorPane content_Pane;
    private BorderPane borderPane;

    private double offsetX;
    private double offsetY;

    public static MainPaneController launch() throws IOException {
        FXMLLoader loader = new FXMLLoader(MainPaneController.class.getResource("mainPane.fxml"));
        BorderPane pane = loader.load();
        MainPaneController controller = loader.getController();
        controller.borderPane = pane;

//        controller.title_Pane.setOnMousePressed(
//                event -> {
//                    controller.offsetX = event.getSceneX();
//                    controller.offsetY = event.getScreenY();
//                    event.consume();
//                }
//        );
//        controller.title_Pane.setOnMouseDragged(
//                event -> {
//                    Stage primary = Main.primaryStage;
//                    double dx = event.getSceneX() - controller.offsetX;
//                    double dy = event.getSceneY() - controller.offsetY;
//                    primary.setX(primary.getX() + dx);
//                    primary.setY(primary.getY() + dy);
//                    event.consume();
//                }
//        );

        return controller;
    }

    public void addTabButton(String name, Parent content){
        Button tabButton = new Button(name);
        left_TabsPane.getChildren().add(tabButton);
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
        Main.primaryStage.close();
    }

    public void maximizeStage(ActionEvent actionEvent) {
        Main.primaryStage.setFullScreen(true);
    }

    public void minimizeStage(ActionEvent actionEvent) {
        Main.primaryStage.toBack();
    }
}
