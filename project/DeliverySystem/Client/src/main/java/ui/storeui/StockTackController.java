package ui.storeui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import org.apache.xmlbeans.impl.schema.SchemaTypeCodePrinter;

import java.io.IOException;

/**
 * Created by Sissel on 2015/11/26.
 */
public class StockTackController {
    public Label location_Label;
    public Label time_Label;
    public Label orderNumber_Label;
    public TableColumn key_TableColumn;
    public TableColumn value_TaxbleColumn;

    public static Parent launch() throws IOException {
        return FXMLLoader.load(StockTackController.class.getResource("stockTack.fxml"));
    }

    public void makeStockTack(ActionEvent actionEvent) {
    }

    public void exportExcel(ActionEvent actionEvent) {
    }
}
