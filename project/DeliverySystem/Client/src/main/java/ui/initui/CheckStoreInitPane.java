package ui.initui;

import bl.blService.initblService.InitializationBLService;
import factory.InitBLFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ui.storeui.StockTackPaneController;

import java.io.IOException;

/**
 * A sub-pane used in the CheckInitInfo layout
 *
 * Created by Sissel on 2015/12/8.
 */
public class CheckStoreInitPane {
    public TextField storeID_Field;
    public AnchorPane content_Pane;

    private InitializationBLService initService = InitBLFactory.getInitializationBLService();

    public void loadStore(ActionEvent actionEvent) {
        String storeID = storeID_Field.getText();
        
    }

    @FXML
    public void initialize(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(StockTackPaneController.class.getResource("stockTackPane"));
        try {
            Pane pane = loader.load();
            content_Pane.getChildren().add(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
