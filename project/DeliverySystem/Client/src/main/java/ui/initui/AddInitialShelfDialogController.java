package ui.initui;

import bl.blService.initblService.InitializationBLService;
import factory.InitBLFactory;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.store.StoreAreaCode;
import tool.ui.Enum2ObservableList;
import tool.ui.SimpleEnumProperty;

import java.io.IOException;

/**
 * Created by Sissel on 2015/12/9.
 */
public class AddInitialShelfDialogController {
    public ChoiceBox<SimpleEnumProperty<StoreAreaCode>> area_ChoiceBox;
    public TextField rowNum_Field;
    public TextField shelvesNum_Field;

    private InitializationBLService initService = InitBLFactory.getInitializationBLService();
    private StoreAreaCode defaultAreaCode;
    private Stage stage;
    private String modelID;
    private String defaultRowNum;

    public static Pane launch(String modelID, StoreAreaCode areaCode, String rowNum, Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(AddInitialShelfDialogController.class.getResource("addInitialShelfDialog.fxml"));
        Pane pane = loader.load();
        AddInitialShelfDialogController controller = loader.getController();
        controller.defaultAreaCode = areaCode;
        controller.stage = stage;
        controller.modelID = modelID;
        controller.defaultRowNum = rowNum;

        return pane;
    }

    @FXML
    public void initialize(){
        area_ChoiceBox.setItems(Enum2ObservableList.transit(StoreAreaCode.values()));
        ObservableList<SimpleEnumProperty<StoreAreaCode>> observableList = area_ChoiceBox.getItems();

        // set default
        rowNum_Field.setText(defaultRowNum);
        shelvesNum_Field.setText("1");
        for (SimpleEnumProperty<StoreAreaCode> enumProperty : observableList) {
            if(enumProperty.getEnum() == defaultAreaCode){
                area_ChoiceBox.setValue(enumProperty);
            }
        }
    }

    public void cancel(ActionEvent actionEvent) {
        stage.close();
    }

    public void commit(ActionEvent actionEvent) {
        // TODO check
        initService.addRows(modelID,
                area_ChoiceBox.getSelectionModel().getSelectedItem().getEnum(),
                rowNum_Field.getText(),
                shelvesNum_Field.getText());
    }
}
