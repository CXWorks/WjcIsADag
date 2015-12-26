package ui.common;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.*;
import main.Main;
import userinfo.UserInfo;

import java.io.IOException;

import bl.clientNetCache.dataProxy.ConfigurationDataProxy;
import util.R;

/**
 * Created by Sissel on 2015/12/19.
 */
public class MainPaneController {
    public AnchorPane title_Pane;
    public FlowPane left_TabsPane;
    public AnchorPane content_Pane;
    public VBox tabs_VBox;
    public AnchorPane outerPane;
    public Label name_Label;

    public static MainPaneController launch() throws IOException {
        FXMLLoader loader = new FXMLLoader(MainPaneController.class.getResource("mainPane.fxml"));
        AnchorPane pane = loader.load();
        MainPaneController controller = loader.getController();
        controller.init();
        controller.outerPane = pane;;

        // resize setting
        controller.content_Pane.prefWidthProperty()
                .bind(Main.primaryStage.widthProperty().subtract(R.ui.LeftTabsWidth));
        controller.content_Pane.prefHeightProperty()
                .bind(Main.primaryStage.heightProperty().subtract(R.ui.TitleHeight));
        controller.left_TabsPane.prefHeightProperty()
                .bind(Main.primaryStage.heightProperty().subtract(R.ui.TitleHeight));
        controller.title_Pane.prefWidthProperty()
                .bind(Main.primaryStage.widthProperty());

        return controller;
    }

	public void init() {
		name_Label.setText(UserInfo.getStaffType().getChinese()+" : "+UserInfo.getUserName());
	}
    
    public void addTabButton(String name, Parent content){
        ToggleButton tabButton = new ToggleButton(name);
        tabButton.setMaxWidth(9999999);
        tabs_VBox.getChildren().add(tabButton);
        tabButton.setOnAction(
                actionEvent -> {
                    content_Pane.getChildren().clear();
                    content_Pane.getChildren().add(content);
                    AnchorPane.setTopAnchor(content, 0.0);
                    AnchorPane.setLeftAnchor(content, 0.0);
                    AnchorPane.setRightAnchor(content, 0.0);
                    AnchorPane.setBottomAnchor(content, 0.0);
                    ((Pane) content).widthProperty().addListener(
                            (observable, oldValue, newValue) -> {
                                System.out.println("stack width change to : " + newValue);
                            }
                    );
                    content_Pane.widthProperty().addListener(
                            (observable, oldValue, newValue) -> {
                                System.out.println("border center change to : " + newValue);
                            }
                    );
                }
        );
    }

    public void jumpTo(Pane content){
        content_Pane.getChildren().clear();
        content_Pane.getChildren().add(content);
        AnchorPane.setTopAnchor(content, 0.0);
        AnchorPane.setLeftAnchor(content, 0.0);
        AnchorPane.setRightAnchor(content, 0.0);
        AnchorPane.setBottomAnchor(content, 0.0);
    }

    public AnchorPane getOuterPane() {
        return outerPane;
    }

    public void closeStage(ActionEvent actionEvent) {
    	ConfigurationDataProxy.Close();
        Main.primaryStage.close();
        System.exit(0);
    }

    public void maximizeStage(ActionEvent actionEvent) {
        Main.primaryStage.setFullScreen(true);
    }

    public void minimizeStage(ActionEvent actionEvent) {
        Main.primaryStage.toBack();
    }
}
