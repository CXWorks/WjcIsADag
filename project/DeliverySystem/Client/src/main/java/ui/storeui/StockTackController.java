package ui.storeui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import main.Main;
import model.store.StoreArea;
import model.store.StoreAreaCode;
import model.store.StoreLocation;
import model.store.StoreModel;
import tool.time.TimeConvert;
import tool.ui.OrderVO2ColumnHelper;
import tool.ui.SimpleEnumProperty;
import vo.ordervo.OrderVO;
import vo.storevo.StockTackVO;
import vo.storevo.StoreShelfVO;

import org.apache.xmlbeans.impl.schema.SchemaTypeCodePrinter;

import bl.blService.storeblService.StockTackBLService;
import bl.blService.storeblService.StoreModelBLService;
import factory.FormFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sissel on 2015/11/26.
 */
public class StockTackController {
    public Label location_Label;
    public Label time_Label;
    public Label orderNumber_Label;
    public AnchorPane content_Pane;

    private StockTackBLService stockTackBLService = FormFactory.getStockTackBLService();
    private StockTackPaneController stockTackPaneController;

    public static Parent launch() throws IOException {
        return FXMLLoader.load(StockTackController.class.getResource("stockTack.fxml"));
    }

    public void initialize(){
        //  initialize the stockTackPane
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(StockTackPaneController.class.getResource("stockTackPane.fxml"));
        try {
            Pane content = loader.load();
            content_Pane.getChildren().add(content);
            stockTackPaneController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // TODO call back, interaction between subpane and parent
        stockTackPaneController.selectedPosition.addListener(
                (observable, oldValue, newValue) -> {
                    location_Label.setText(stockTackPaneController.generatePath());
                }
        );

        makeStockTack(null);
    }

    @FXML
    public void makeStockTack(ActionEvent actionEvent) {
        StockTackVO stockTackVO = stockTackBLService.getStockTack();

        time_Label.setText(TimeConvert.getDisplayDate(stockTackVO.date));
        orderNumber_Label.setText(stockTackVO.id);

        stockTackPaneController.setStoreModel(stockTackVO.storeModel);
    }

    @FXML
    public void exportExcel(ActionEvent actionEvent) {
        // TODO 记下用户上次存文件的位置
        FileChooser fileChooser = new FileChooser();
        //fileChooser.setInitialDirectory(new File("g:/develop"));
        fileChooser.setInitialFileName("StockTack--"
                + time_Label.getText()
                + "--" + orderNumber_Label.getText()
                + ".xls");
        File file = fileChooser.showSaveDialog(Main.primaryStage);

        if(file != null){
            stockTackBLService.makeExcel(file.getAbsolutePath());
        }
    }
}
