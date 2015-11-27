package ui.common;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.util.Pair;

import java.io.IOException;
import java.util.List;

/**
 * Created by Sissel on 2015/11/27.
 */
public class TabMaker {

    public static Parent makeTabs(List<Pair<String, Parent>> list) throws IOException {
        TabPane tabPane = FXMLLoader.load(TabMaker.class.getResource("generalTabPane.fxml"));
        for (Pair<String, Parent> pair : list){
            Tab tab = new Tab();
            tab.setText(pair.getKey());
            tab.setContent(pair.getValue());
            tabPane.getTabs().add(tab);
        }

        return tabPane;
    }
}
