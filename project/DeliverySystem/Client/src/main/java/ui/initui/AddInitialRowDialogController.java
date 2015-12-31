package ui.initui;

import bl.blService.initblService.InitializationBLService;
import factory.InitBLFactory;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.store.StoreAreaCode;
import tool.ui.Enum2ObservableList;
import tool.ui.SimpleEnumProperty;
import ui.common.checkFormat.FormatCheckQueue;
import ui.common.checkFormat.field.CheckIsNullTasker;
import ui.informui.InformController;

import java.io.IOException;

/**
 * Created by Sissel on 2015/12/9.
 */
public class AddInitialRowDialogController {
    public ChoiceBox<SimpleEnumProperty<StoreAreaCode>> area_ChoiceBox;
    public TextField rowNum_Field;
    public TextField shelvesNum_Field;

    private InitializationBLService initService = InitBLFactory.getInitializationBLService();
    private StoreAreaCode defaultAreaCode;
    private Stage stage;
    private String modelID;
    
    private FormatCheckQueue formatCheckQueue;

    public static Parent launch(String modelID, StoreAreaCode areaCode, Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(AddInitialRowDialogController.class.getResource("addInitialRowDialog.fxml"));
        Pane pane = loader.load();
        AddInitialRowDialogController controller = loader.getController();

        controller.defaultAreaCode = areaCode;
        controller.stage = stage;
        controller.modelID = modelID;

        return pane;
    }

    @FXML
    public void initialize(){
        area_ChoiceBox.setItems(Enum2ObservableList.transit(StoreAreaCode.values()));
        ObservableList<SimpleEnumProperty<StoreAreaCode>> observableList = area_ChoiceBox.getItems();

        // set default
        rowNum_Field.setText("1");
        shelvesNum_Field.setText("20");
        for (SimpleEnumProperty<StoreAreaCode> enumProperty : observableList) {
            if(enumProperty.getEnum() == defaultAreaCode){
                area_ChoiceBox.setValue(enumProperty);
            }
        }
        //init check
        formatCheckQueue=new FormatCheckQueue();
        formatCheckQueue.addTasker(
        		new CheckIsNullTasker(rowNum_Field),
        		new CheckIsNullTasker(shelvesNum_Field)
        		);
    }

    public void cancel(ActionEvent actionEvent) {
        stage.close();
    }

    public void commit(ActionEvent actionEvent) {
        // check
    	if (!formatCheckQueue.startCheck()) {
			return ;
		}
        initService.addRows(modelID,
                        area_ChoiceBox.getSelectionModel().getSelectedItem().getEnum(),
                        rowNum_Field.getText(),
                        shelvesNum_Field.getText());
    }
}
