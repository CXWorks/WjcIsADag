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

    public static Pane newLeftTabPane(List<Pair<String, Parent>> list){
        try {
            MainPaneController controller = MainPaneController.launch();
            for (Pair<String, Parent> stringPanePair : list) {
                controller.addTabButton(stringPanePair.getKey(), stringPanePair.getValue());
            }
            return controller.getBorderPane();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
