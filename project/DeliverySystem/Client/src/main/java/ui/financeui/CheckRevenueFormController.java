package ui.financeui;

import bl.blService.FormatCheckService.FormatCheckService;
import bl.blService.financeblService.RevenueBLService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import tool.time.TimeConvert;
import userinfo.Services;
import vo.financevo.RevenueVO;

import java.io.IOException;
import java.util.List;

/**
 * Created by Sissel on 2015/11/23.
 */
public class CheckRevenueFormController {

    public Label sum_Label;
    public DatePicker revenue_DatePicker;
    public TableView<RevenueVO> revenue_TableView;
    public TextField hall_Field;
    public Label hall_errLabel;
    public Label date_errLabel;
    public TableColumn<RevenueVO, String> orderCounts_Column;
    public TableColumn<RevenueVO, String> deliver_Column;
    public TableColumn<RevenueVO, String> money_Column;

    FormatCheckService formatCheckService;
    RevenueBLService revenueBLService;

    public static Parent launch() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CheckRevenueFormController.class.getResource("checkRevenueForm.fxml"));
        return loader.load();
    }

    @FXML
    public void initialize(){
        ObservableList<RevenueVO> revenueVOs = FXCollections.observableArrayList();

        formatCheckService = Services.formatCheckService;
        revenueBLService = Services.revenueBLService;

        orderCounts_Column.setCellValueFactory(
                cellData -> new SimpleStringProperty(""+cellData.getValue().orderIDs.size())
        );
        deliver_Column.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().deliverName)
        );
        money_Column.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().amount)
        );

        revenue_TableView.setItems(revenueVOs);
    }

    public void search(ActionEvent actionEvent) {
        // TODO check

        List<RevenueVO> revenueVOs = revenueBLService.getRevenueVOs
                (TimeConvert.convertDate(revenue_DatePicker.getValue()), hall_Field.getText());

        revenue_TableView.setItems(FXCollections.observableArrayList(revenueVOs));
    }
}
