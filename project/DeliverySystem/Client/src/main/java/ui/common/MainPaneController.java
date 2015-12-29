package ui.common;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import main.Main;
import ui.accountui.PersonalAccountViewController;
import userinfo.UserInfo;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import bl.clientNetCache.dataProxy.ConfigurationDataProxy;
import util.R;

/**
 * Created by Sissel on 2015/12/19.
 */
public class MainPaneController {
    public AnchorPane title_Pane;
    public StackPane left_TabsPane;
    public AnchorPane content_Pane;
    public VBox tabs_VBox;
    public AnchorPane outerPane;
    public Label name_Label;
    public AnchorPane personGFatherPane;
    public AnchorPane personFatherPane;

    List<ToggleButton> toggleTabs = new LinkedList<>();

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
		name_Label.setText(UserInfo.getStaffType().getChinese() + " : " + UserInfo.getUserName());

        try {
            Pane personView = (Pane)PersonalAccountViewController.launch(this);
            personFatherPane.getChildren().add(personView);
            setAnchor(personView, 0.0, 0.0, 0.0, 0.0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        personGFatherPane.setVisible(false);
	}
    
    public void addTabButton(String name, Parent content){
        ToggleButton tabButton = new ToggleButton(name);
        tabButton.setMaxWidth(9999999);
        toggleTabs.add(tabButton);
        tabs_VBox.getChildren().add(tabButton);
        tabButton.setOnAction(
                actionEvent -> {
                    disSelectAllTabs();
                    tabButton.setSelected(true);
                    content_Pane.getChildren().clear();
                    content_Pane.getChildren().add(content);
                    setAnchor(content, 0.0, 0.0, 0.0, 0.0);
                }
        );
    }

    private void disSelectAllTabs(){
        for (ToggleButton toggleTab : toggleTabs) {
            toggleTab.setSelected(false);
        }
    }

    public void selectButton(String tabText){
        for (ToggleButton toggleTab : toggleTabs) {
            if(toggleTab.getText().equals(tabText)){
                toggleTab.getOnAction().handle(null);
            }
        }
    }

    public void jumpTo(Pane content){
        content_Pane.getChildren().clear();
        content_Pane.getChildren().add(content);
        setAnchor(content, 0.0, 0.0, 0.0, 0.0);
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

    public void popUpPersonView(Event event) {
        boolean now = personGFatherPane.isVisible();
        personGFatherPane.setVisible(!now);
    }

    private static void setAnchor(Node node, double top, double bottom, double left, double right){
        AnchorPane.setTopAnchor(node, top);
        AnchorPane.setBottomAnchor(node, bottom);
        AnchorPane.setLeftAnchor(node, left);
        AnchorPane.setRightAnchor(node, right);
    }

    public void showSettingDialog(Event event) {
        Stage stage = SettingDialogController.newDialog(Main.primaryStage);
        stage.showAndWait();
    }
}