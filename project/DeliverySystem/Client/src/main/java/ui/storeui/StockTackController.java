package ui.storeui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
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
import model.store.StoreArea;
import model.store.StoreAreaCode;
import model.store.StoreModel;
import tool.ui.OrderVO2ColumnHelper;
import vo.ordervo.OrderVO;
import vo.storevo.StoreShelfVO;

import org.apache.xmlbeans.impl.schema.SchemaTypeCodePrinter;

import bl.blService.storeblService.StockTackBLService;
import bl.blService.storeblService.StoreModelBLService;
import factory.FormFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sissel on 2015/11/26.
 */
public class StockTackController {
    public Label location_Label;
    public Label time_Label;
    public Label orderNumber_Label;
    public TableView<Map.Entry<String, String>> order_TableView;
    public TableColumn<Map.Entry<String, String>, String> key_TableColumn;
    public TableColumn<Map.Entry<String, String>, String> value_TaxbleColumn;
    public TreeTableView<StoreArea> store_TableView;
    public TreeTableColumn<StoreArea,String> area_Column;
    public TreeTableColumn row_Column;
    public TreeTableColumn shelf_Column;
    public TreeTableColumn position_Column;

    private StoreModelBLService storeModelBLService = FormFactory.getStoreModelBLService();
    private StockTackBLService stockTackBLService = FormFactory.getStockTackBLService();

    public static Parent launch() throws IOException {
        return FXMLLoader.load(StockTackController.class.getResource("stockTack.fxml"));
    }

    @FXML
    public void initialize(){

    	TreeItem<StoreArea> rootItem;
//    	rootItem.getChildren().add(arg0);
    }

    public void makeStockTack(ActionEvent actionEvent) {
    }

    public void exportExcel(ActionEvent actionEvent) {

    }
    private void fillOrderTable() {
		OrderVO orderVO = stockTackBLService.getOrder(null);

		order_TableView.setItems(FXCollections.observableArrayList(new OrderVO2ColumnHelper().VO2Entries(orderVO)));
	}
}
