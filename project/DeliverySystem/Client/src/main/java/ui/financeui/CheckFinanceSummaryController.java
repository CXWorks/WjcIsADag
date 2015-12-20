package ui.financeui;

import bl.blService.financeblService.FinanceChartBLService;
import bl.blService.financeblService.PaymentBLService;
import bl.blService.financeblService.RevenueBLService;
import factory.FinanceBLFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import tool.time.TimeConvert;
import ui.hallui.RevenueFormController;
import ui.informui.InformController;
import vo.financevo.CalculateVO;
import vo.financevo.FinanceFormVO;
import vo.financevo.PaymentVO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Comparator;

/**
 * Created by Sissel on 2015/11/24.
 */
public class CheckFinanceSummaryController {

    private PaymentBLService paymentBLService = FinanceBLFactory.getPaymentBLService();
    private RevenueBLService revenueBLService = FinanceBLFactory.getRevenueBLService();
    private FinanceChartBLService financeChartBLService = FinanceBLFactory.getFinanceChartBLService();

    // controls
    public DatePicker begin_DatePicker;
    public DatePicker end_DatePicker;
    public Label outcome_Label;
    public Label income_Label;
    public Label profit_Label;
    public TableView<FinanceFormVO> finance_TableView;
    public TableColumn<FinanceFormVO, String> date_Column;
    public TableColumn<FinanceFormVO, String> type_Column;
    public TableColumn<FinanceFormVO, String> money_Column;

    private InformController informController;

    public static Parent launch() throws IOException {
        FXMLLoader loader = new FXMLLoader(CheckFinanceSummaryController.class.getResource("checkFinanceSummary.fxml"));
        Pane pane = loader.load();
        CheckFinanceSummaryController controller = loader.getController();
        controller.informController = InformController.newInformController(pane);

        return controller.informController.stackPane;
    }

    @FXML
    public void initialize(){
        date_Column.setCellValueFactory(
                cellData -> new SimpleStringProperty(TimeConvert.getDisplayDate(cellData.getValue().date))
        );
        type_Column.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getFormType().getChinese())
        );
        money_Column.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().amount)
        );

        begin_DatePicker.setValue(LocalDate.now());
        end_DatePicker.setValue(LocalDate.now());
    }

    public void search(ActionEvent actionEvent) {
        // TODO check date

        Calendar begin = TimeConvert.convertDate(begin_DatePicker.getValue());
        Calendar end = TimeConvert.convertDate(end_DatePicker.getValue());

        ObservableList<FinanceFormVO> forms = FXCollections.observableArrayList();
        forms.addAll(revenueBLService.getRevenueVOs(begin, end));
        forms.addAll(paymentBLService.getPaymentVOs(begin, end));

        forms.sort(
                (o1, o2) -> o1.date.compareTo(o2.date)
        );

        finance_TableView.setItems(forms);

        CalculateVO calculateVO = financeChartBLService.getCompanyState(begin, end);
        outcome_Label.setText(calculateVO.companyPayment + "元");
        income_Label.setText(calculateVO.companyRevenue + "元");
        profit_Label.setText(calculateVO.companyProfit + "元");
    }


}
