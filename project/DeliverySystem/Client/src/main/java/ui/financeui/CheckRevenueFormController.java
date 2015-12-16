package ui.financeui;

import bl.blService.FormatCheckService.FormatCheckService;
import bl.blService.financeblService.RevenueBLService;
import factory.FinanceBLFactory;
import factory.FormatCheckFactory;
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
import vo.ordervo.OrderVO;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Sissel on 2015/11/23.
 */
public class CheckRevenueFormController {

    public Label sum_Label;
    public DatePicker revenue_DatePicker;
    public TextField hall_Field;
    public Label hall_errLabel;
    public Label date_errLabel;

    public TableView<RevenueVO> revenue_TableView;
    public TableColumn<RevenueVO, String> deliver_Column;
    public TableColumn<RevenueVO, String> sum_money_Column;
    public TableColumn<RevenueVO, String> revenueID_TableColumn;

    public TableView<OrderVO> detail_TableView;
    public TableColumn<OrderVO, String> orderID_Column;
    public TableColumn<OrderVO, String> money_Column;

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

        formatCheckService = FormatCheckFactory.getFormatCheckService();
        revenueBLService = FinanceBLFactory.getRevenueBLService();

        // Revenue Form TableView
        revenueID_TableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getFormID())
        );
        deliver_Column.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().deliverName)
        );
        sum_money_Column.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().amount)
        );

        // Detail Form TableView
        orderID_Column.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getFormID())
        );
        money_Column.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getFormID())
        );

        revenue_TableView.setItems(revenueVOs);
    }

    public void search(ActionEvent actionEvent) {

        List<RevenueVO> revenueVOs = revenueBLService.getRevenueVOs
                (TimeConvert.convertDate(revenue_DatePicker.getValue()), hall_Field.getText());


        revenue_TableView.setItems(FXCollections.observableArrayList(revenueVOs));
    }
}
