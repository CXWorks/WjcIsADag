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

    public TableView<Map.Entry<String, String>> message_TableView;
    public TableColumn<Map.Entry<String, String>, String> key_TableColumn;
    public TableColumn<Map.Entry<String, String>, String> value_TableColumn;

    public TableView<SimpleEnumProperty<StoreAreaCode>> area_TableView;
    public TableColumn<SimpleEnumProperty<StoreAreaCode>, String> area_Column;

    public TableView<StoreLocation> row_TableView;
    public TableColumn<StoreLocation, String> row_Column;

    public TableView<StoreLocation> shelf_TableView;
    public TableColumn<StoreLocation, String> shelf_Column;

    public TableView<StoreLocation> position_TableView;
    public TableColumn<StoreLocation, String> position_TableColumn;
    public TableColumn<StoreLocation, String> order_TableColumn;

    private StockTackBLService stockTackBLService = FormFactory.getStockTackBLService();
    private StockTackVO stockTackVO;
    private StoreModel storeModel;
    private StoreArea storeArea;
    private int selectedRow;
    private int selectedShelf;
    private int selectedPosition;

    public static Parent launch() throws IOException {
        return FXMLLoader.load(StockTackController.class.getResource("stockTack.fxml"));
    }

    public void makeStockTack(ActionEvent actionEvent) {
        stockTackVO = stockTackBLService.getStockTack();
        time_Label.setText(stockTackVO.date);
        orderNumber_Label.setText(stockTackVO.id);

        storeModel = stockTackVO.storeModel;
        redirectArea(StoreAreaCode.AIR);
    }

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

    @FXML
    public void initialize(){
        // set cell value factories
        area_Column.setCellValueFactory(
                cell -> new SimpleStringProperty(cell.getValue().getEnum().getChinese())
        );
        row_Column.setCellValueFactory(
                cell -> {
                    int row = cell.getValue().getRow();
                    if(row < 0){
                        return new SimpleStringProperty("All");
                    }else{
                        return new SimpleStringProperty(row + "");
                    }
                }
        );
        shelf_Column.setCellValueFactory(
                cell -> {
                    int shelf = cell.getValue().getShelf();
                    if(shelf < 0){
                        return new SimpleStringProperty("All");
                    }else{
                        return new SimpleStringProperty(shelf + "");
                    }
                }
        );
        position_TableColumn.setCellValueFactory(
                cell -> new SimpleStringProperty(cell.getValue().getPosition() + "")
        );
        order_TableColumn.setCellValueFactory(
                cell -> new SimpleStringProperty(cell.getValue().getOrderID() + "")
        );

        // set onClickListener
        area_TableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    clear(row_TableView, shelf_TableView, position_TableView, message_TableView);
                    redirectArea(newValue.getEnum());
                }
        );
        row_TableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    clear(shelf_TableView, position_TableView, message_TableView);
                    selectedRow = newValue.getRow();
                    List<StoreLocation> storeLocations = storeArea.getByRow(selectedRow);
                    setShelf_TableView(storeLocations);
                    position_TableView.getItems().addAll(storeArea.getByShelf(selectedRow, selectedShelf));
                }
        );
        shelf_TableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    clear(position_TableView, message_TableView);
                    selectedShelf = newValue.getShelf();
                    List<StoreLocation> storeLocations = storeArea.getByShelf(selectedRow, selectedShelf);
                    position_TableView.getItems().addAll(storeLocations);
                }
        );
        position_TableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    clear(message_TableView);
                    selectedPosition = newValue.getPosition();
                    OrderVO orderVO = stockTackBLService.getOrder(newValue.getOrderID());
                    message_TableView.setItems(FXCollections.observableArrayList(
                            new OrderVO2ColumnHelper().VO2Entries(orderVO)
                    ));
                }
        );

    }

    private void clear(TableView...tableViews){
        for (TableView tableView : tableViews) {
            tableView.getItems().clear();
        }
    }

    private void setRow_TableView(List<StoreLocation> locations){
        outer:
        for (StoreLocation location : locations) {
            ObservableList<StoreLocation> observableList = row_TableView.getItems();
            for (StoreLocation storeLocation : observableList) {
                if(storeLocation.getRow() == location.getRow()){
                    continue outer;
                }
            } // the row number in location has not been inserted
            observableList.add(location);
        }
    }

    private void setShelf_TableView(List<StoreLocation> locations){
        outer:
        for (StoreLocation location : locations) {
            ObservableList<StoreLocation> observableList = shelf_TableView.getItems();
            for (StoreLocation storeLocation : observableList) {
                if(storeLocation.getShelf() == location.getShelf()){
                    continue outer;
                }
            } // the row number in location has not been inserted
            observableList.add(location);
        }
    }

    private void redirectArea(StoreAreaCode code){
        storeArea = storeModel.getArea(code);
        selectedRow = 0;
        selectedShelf = 0;
        selectedPosition = 0;
        // TODO may be bug here (hard code
        // exactly the shelf table view display the locations of the getByRow, etc
        setRow_TableView(storeArea.getList());
        setShelf_TableView(storeArea.getByRow(selectedRow));
        position_TableView.getItems().addAll(storeArea.getByShelf(selectedRow, selectedShelf));
    }



    private void generatePath(){
        String path = storeArea.getAreaID().getChinese() + "区/"
                + selectedRow + "排/"
                + selectedShelf + "架/"
                + selectedPosition + "号";
        location_Label.setText(path);
    }
}
