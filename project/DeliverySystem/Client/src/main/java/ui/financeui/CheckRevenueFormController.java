package ui.financeui;

import bl.blService.FormatCheckService.FormatCheckService;
import bl.blService.financeblService.RevenueBLService;
import bl.tool.time.TimeConvert;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import userinfo.Services;
import vo.financevo.RevenueVO;

import java.io.IOException;
import java.util.Calendar;

/**
 * Created by Sissel on 2015/11/23.
 */
public class CheckRevenueFormController {

    public Label sum_Label;
    public DatePicker revenue_DatePicker;
    public TextField hall_textField;
    public TableView revenue_TableView;
    public TextField hall_Field;
    public Label hall_errLabel;
    public Label date_errLabel;
    public TableColumn<RevenueVO, String> orderID_Column;
    public TableColumn<RevenueVO, String> deliver_Column;
    public TableColumn<RevenueVO, String> money_Column;

    private ObservableList<RevenueVO> revenueVOs;

    FormatCheckService formatCheckService;
    RevenueBLService revenueBLService;

    public static Parent launch() throws IOException {
        return FXMLLoader.load(CheckRevenueFormController.class.getResource("checkRevenueForm.fxml"));
    }

    @FXML
    public void initialize(){
        formatCheckService = Services.formatCheckService;
        revenueBLService = Services.revenueBLService;

        revenueVOs.add(new RevenueVO("123", Calendar.getInstance(), "450","wjr" ,"321" , "999"));

        orderID_Column.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getOrderID())
        );
        deliver_Column.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getDeliverName())
        );
        money_Column.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getAmount())
        );
        revenue_TableView.setItems(revenueVOs);
    }

    public void search(ActionEvent actionEvent) {
        Calendar calendar = TimeConvert.convertDate(revenue_DatePicker.getValue());

        // TODO check
        String hallID = hall_textField.getText();
        // TODO check

    }
}
