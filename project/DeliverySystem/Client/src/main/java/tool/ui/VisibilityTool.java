package tool.ui;

import javafx.scene.Node;

/**
 * Created by Sissel on 2015/12/30.
 */
public class VisibilityTool {
    public static void setInvisible(Node...nodes){
        for (Node node : nodes) {
            node.setVisible(false);
        }
    }
}
