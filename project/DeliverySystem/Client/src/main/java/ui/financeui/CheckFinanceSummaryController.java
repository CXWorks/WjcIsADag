package ui.financeui;

import bl.blService.financeblService.PaymentBLService;
import bl.blService.financeblService.RevenueBLService;
import factory.FinanceBLFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import tool.time.TimeConvert;
import vo.financevo.FinanceFormVO;

import java.io.IOException;
import java.util.Calendar;
import java.util.Comparator;

/**
 * Created by Sissel on 2015/11/24.
 */
public class CheckFinanceSummaryController {

    private PaymentBLService paymentBLService = FinanceBLFactory.getPaymentBLService();
    private RevenueBLService revenueBLService = FinanceBLFactory.getRevenueBLService();

    // controls
    public DatePicker begin_DatePicker;
    public DatePicker end_DatePicker;
    public Label payment_Label;
    public Label revenue_Label;
    public Label profit_Label;
    public TableView<FinanceFormVO> finance_TableView;
    public TableColumn<FinanceFormVO, String> date_Column;
    public TableColumn<FinanceFormVO, String> type_Column;
    public TableColumn<FinanceFormVO, String> money_Column;

    public static Parent launch() throws IOException {
        return FXMLLoader.load(CheckFinanceSummaryController.class.getResource("checkFinanceSummary.fxml"));
    }

    @FXML
    public void initialize(){

    }

    public void search(ActionEvent actionEvent) {
        // TODO check date

        Calendar begin = TimeConvert.convertDate(begin_DatePicker.getValue());
        Calendar end = TimeConvert.convertDate(end_DatePicker.getValue());

        ObservableList<FinanceFormVO> forms = FXCollections.observableArrayList();
        forms.addAll(revenueBLService.getRevenueVOs(begin, end));
        forms.addAll(paymentBLService.getPaymentVOs(begin, end));

        forms.sort(
                (o1, o2) -> {
                    return 10;
                }
        );
    }


}
