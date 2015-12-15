package ui.initui;

import bl.blService.initblService.InitializationBLService;
import factory.InitBLFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Main;
import model.store.StoreModel;
import ui.storeui.StockTackPaneController;

import java.io.IOException;
import java.util.List;

/**
 * Created by Sissel on 2015/12/9.
 */
public class ManageInitialStoreController {

    public ChoiceBox<StoreModelProperty> store_ChoiceBox;
    public Pane content_Pane;

    private InitializationBLService initService = InitBLFactory.getInitializationBLService();
    private StockTackPaneController contentController;
    private StoreModel selectedModel;
    private Pane father;
    private Pane before;

    public static Pane launch(Pane father, Pane before) throws IOException {
        FXMLLoader outLoader = new FXMLLoader();
        outLoader.setLocation(ManageInitialStoreController.class.getResource("manageInitialStore.fxml"));
        Pane pane = outLoader.load();
        ManageInitialStoreController outController = outLoader.getController();

        FXMLLoader contentLoader = new FXMLLoader();
        contentLoader.setLocation(StockTackPaneController.class.getResource("stockTackPane.fxml"));
        Pane content = contentLoader.load();

        outController.contentController = contentLoader.getController();
        outController.father = father;
        outController.before = before;
        outController.content_Pane.getChildren().add(content);

        return pane;
    }

    @FXML
    public void initialize(){
        List<StoreModel> storeModels = initService.getAllStoreModels();
        for (StoreModel storeModel : storeModels) {
            store_ChoiceBox.getItems().add(new StoreModelProperty(storeModel));
        }
        store_ChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if(newValue == null){
                        System.out.println("ManageInitialStoreController: null storeModel");
                        return;
                    }
                    selectedModel = newValue.getStoreModel();
                    contentController.setStoreModel(selectedModel);
                }
        );

    }

    @FXML
    public void delete(ActionEvent actionEvent) {
        // TODO
    }

    @FXML
    public void addRow(ActionEvent actionEvent) {
        Stage dialogStage = new Stage();
        dialogStage.initOwner(Main.primaryStage);
        try {
            AddInitialRowDialogController.launch
                    (selectedModel.getCenterID(), contentController.storeArea.getAreaID(), dialogStage);
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addShelf(ActionEvent actionEvent) {
        Stage dialogStage = new Stage();
        dialogStage.initOwner(Main.primaryStage);
        try {
            // TODO maybe bug HERE, selectedRow is Int, not String
            AddInitialShelfDialogController.launch
                    (selectedModel.getCenterID(), contentController.storeArea.getAreaID(),
                            contentController.selectedRow.getValue() + "", dialogStage);
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goBack(ActionEvent actionEvent) {
        father.getChildren().clear();
        father.getChildren().add(before);
    }
}
