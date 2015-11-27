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
    public TableView<RevenueVO> revenue_TableView;
    public TextField hall_Field;
    public Label hall_errLabel;
    public Label date_errLabel;
    public TableColumn<RevenueVO, String> orderID_Column;
    public TableColumn<RevenueVO, String> deliver_Column;
    public TableColumn<RevenueVO, String> money_Column;

    private ObservableList<RevenueVO> revenueVOs;

    private RevenueVO selectedVO;

    FormatCheckService formatCheckService;
    RevenueBLService revenueBLService;

    public static Parent launch() throws IOException {
        //return FXMLLoader.load(CheckRevenueFormController.class.getResource("checkRevenueForm.fxml"));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CheckRevenueFormController.class.getResource("checkRevenueForm.fxml"));
        return loader.load();
    }

    @FXML
    public void initialize(){
        revenueVOs = FXCollections.observableArrayList();

        formatCheckService = Services.formatCheckService;
        revenueBLService = Services.revenueBLService;

        revenueVOs.add(new RevenueVO("1235", Calendar.getInstance(), "450","wjr" ,"321" , "999"));
        revenueVOs.add(new RevenueVO("1234", Calendar.getInstance(), "4670","wjc" ,"233" , "1999"));

        orderID_Column.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().orderID)
        );
        deliver_Column.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().deliverName)
        );
        money_Column.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().amount)
        );

        revenue_TableView.setItems(revenueVOs);
        revenue_TableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selectedVO = (RevenueVO)newValue;
                }
        );
    }

    public void search(ActionEvent actionEvent) {

    }
}
