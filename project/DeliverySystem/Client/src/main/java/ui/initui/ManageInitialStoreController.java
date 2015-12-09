package ui.initui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import ui.storeui.StockTackPaneController;

import java.io.IOException;

/**
 * Created by Sissel on 2015/12/9.
 */
public class ManageInitialStoreController {

    public ChoiceBox store_ChoiceBox;
    public Pane content_Pane;

    private StockTackPaneController contentController;
    private Pane father;
    private Pane before;

    public static void launch(Pane father, Pane before) throws IOException {
        FXMLLoader outLoader = new FXMLLoader();
        outLoader.setLocation(ManageInitialStoreController.class.getResource("manageInitialStore.fxml"));
        outLoader.load();
        ManageInitialStoreController outController = outLoader.getController();

        FXMLLoader contentLoader = new FXMLLoader();
        contentLoader.setLocation(StockTackPaneController.class.getResource("stockTackPaneController.fxml"));
        Pane content = contentLoader.load();

        outController.contentController = contentLoader.getController();
        outController.father = father;
        outController.before = before;
    }

    @FXML
    public void delete(ActionEvent actionEvent) {

    }

    @FXML
    public void addRow(ActionEvent actionEvent) {
    }

    @FXML
    public void addShelf(ActionEvent actionEvent) {
    }

    @FXML
    public void goBack(ActionEvent actionEvent) {

    }
}
