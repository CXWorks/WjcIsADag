package ui.common;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

import java.io.IOException;
import java.util.List;

/**
 * Created by Sissel on 2015/11/27.
 */
public class TabMaker {

    public static TabPane newTabPane() throws IOException {
        return FXMLLoader.load(TabMaker.class.getResource("generalTabPane.fxml"));
    }

    public static TabPane newTabPane(List<Pair<String, Parent>> list) throws IOException {
        TabPane tabPane = newTabPane();
        addTabs(tabPane, list);

        return tabPane;
    }

    public static void addTabs(TabPane tabPane, List<Pair<String, Parent>> list){
        for (Pair<String, Parent> pair : list){
            addTab(tabPane, pair);
        }
    }

    public static void addTab(TabPane tabPane, Pair<String, Parent> pair){
        Tab tab = new Tab();
        tab.setText(pair.getKey());
        tab.setContent(pair.getValue());
        tabPane.getTabs().add(tab);
    }

    // The above is the old version

    public static void addLeftTabs(MainPaneController mainPane, List<Pair<String, Parent>> list){
        for (Pair<String, Parent> stringParentPair : list) {
            addLeftTab(mainPane, stringParentPair);
        }
    }

    public static void addLeftTab(MainPaneController mainPane, Pair<String, Parent> pair){
        mainPane.addTabButton(pair.getKey(), pair.getValue());
    }

    public static MainPaneController newMainPaneController() throws IOException {
        MainPaneController controller = MainPaneController.launch();
        return controller;
    }

    public static Pane newLeftTabPane(List<Pair<String, Parent>> list){
        try {
            MainPaneController controller = newMainPaneController();
            addLeftTabs(controller, list);
            return controller.getOuterPane();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
