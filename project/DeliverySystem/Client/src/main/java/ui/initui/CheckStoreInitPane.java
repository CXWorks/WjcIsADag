package ui.initui;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * A sub-pane used in the CheckInitInfo layout
 *
 * Created by Sissel on 2015/12/8.
 */
public class CheckStoreInitPane {
    public TextField storeID_Field;
    public AnchorPane content_Pane;

    public void loadStore(ActionEvent actionEvent) {
        String storeID = storeID_Field.getText();
        
    }
}
