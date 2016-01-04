package ui.financeui;

import bl.blService.financeblService.FinanceChartBLService;
import factory.FinanceBLFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.*;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import tool.time.TimeConvert;
import tool.ui.Enum2ObservableList;
import tool.ui.SimpleEnumProperty;
import ui.common.checkFormat.FormatCheckQueue;
import ui.common.checkFormat.date.CheckDualDateTasker;
import ui.common.checkFormat.date.CheckPreDateTasker;
import ui.hallui.RevenueFormController;
import ui.informui.InformController;
import vo.financevo.*;

import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * Created by Sissel on 2015/11/27.
 */
public class CheckFinanceChartController {


    private FinanceChartBLService financeChartBLService = FinanceBLFactory.getFinanceChartBLService();

    // controls
    public Label dateErr_Label;
    public DatePicker begin_DatePicker;
    public DatePicker end_DatePicker;
    public Label outcome_Label;
    public Label income_Label;
    public Label profit_Label;
    public PieChart pieChart;
    public BarChart barChart;
    public LineChart lineChart;
    public ChoiceBox<SimpleEnumProperty<FinancePieChartType>> pieType_ChoiceBox;
    public ChoiceBox<SimpleEnumProperty<FinanceBaseChartType>> barType_ChoiceBox;
    public ChoiceBox<SimpleEnumProperty<FinanceBaseChartType>> lineType_ChoiceBox;

    private InformController informController;
    private FormatCheckQueue formatCheckQueue;

    public static Parent launch() throws IOException {
        FXMLLoader loader = new FXMLLoader(CheckFinanceChartController.class.getResource("checkFinanceChart.fxml"));
        Pane pane = loader.load();
        CheckFinanceChartController controller = loader.getController();
        controller.informController = InformController.newInformController(pane);

        return controller.informController.stackPane;
    }

    @FXML
    public void initialize(){

        pieType_ChoiceBox.setItems(Enum2ObservableList.transit(FinancePieChartType.values()));
        pieType_ChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) ->
                        refreshPieChart()
        );

        barType_ChoiceBox.setItems(Enum2ObservableList.transit(FinanceBaseChartType.values()));
        barType_ChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) ->
                        refreshBarChart()
        );

        lineType_ChoiceBox.setItems(Enum2ObservableList.transit(FinanceBaseChartType.values()));
        lineType_ChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) ->
                        refreshLineChart()
        );

        formatCheckQueue = new FormatCheckQueue(
                new CheckDualDateTasker(dateErr_Label, begin_DatePicker, end_DatePicker),
                new CheckPreDateTasker(dateErr_Label, begin_DatePicker),
                new CheckPreDateTasker(dateErr_Label, end_DatePicker)
        );
    }

    public void search(ActionEvent actionEvent) {
        if(!formatCheckQueue.startCheck()){
            return;
        }

        CalculateVO calculateVO = financeChartBLService.getCompanyState(getBegin(), getEnd());
        outcome_Label.setText(calculateVO.companyPayment + "元");
        income_Label.setText(calculateVO.companyRevenue + "元");
        profit_Label.setText(calculateVO.companyProfit + "元");

        refreshPieChart();
        refreshBarChart();
        refreshLineChart();
        dateErr_Label.setText("");
    }

    private void refreshLineChart() {
        lineChart.getData().clear();
        if (lineType_ChoiceBox.getValue()==null) {
			return;
		}

        FinanceBaseChartType type = lineType_ChoiceBox.getValue().getEnum();
        BaseChartVO lineChartVO = financeChartBLService
                .getLineChart(getBegin(), getEnd(), type);

        // set Axis and Title
        NumberAxis yAxis = (NumberAxis)lineChart.getYAxis();
        CategoryAxis xAxis = (CategoryAxis)lineChart.getXAxis();
//        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle(lineChartVO.title);
        // set labels
        xAxis.setLabel(lineChartVO.mainXType);
        yAxis.setLabel(lineChartVO.mainYType);
        yAxis.setTickLabelRotation(90);

        // set value
        double[][] values = lineChartVO.values;
        String[] categories = lineChartVO.categories;
        String[] elements = lineChartVO.elements;
        for (int i = 0; i < categories.length; i++) {
            XYChart.Series series = new XYChart.Series();
            series.setName( categories[i] );
            for (int j = 0; j < elements.length; j++) {
                series.getData().add(new XYChart.Data(elements[j], values[i][j]));
            }
            lineChart.getData().add(series);
        }
    }

    private void refreshBarChart() {

        barChart.getData().clear();
        if (barType_ChoiceBox.getValue()==null) {
			return;
		}

        FinanceBaseChartType type = barType_ChoiceBox.getValue().getEnum();
        BaseChartVO barChartVO = financeChartBLService
                .getBarChart(getBegin(), getEnd(), type);

        // set Axis and Title
        NumberAxis yAxis = (NumberAxis)barChart.getYAxis();
        CategoryAxis xAxis = (CategoryAxis)barChart.getXAxis();
        barChart.setTitle(barChartVO.title);
        // set labels
        xAxis.setLabel(barChartVO.mainXType);
        yAxis.setLabel(barChartVO.mainYType);
        yAxis.setTickLabelRotation(90);

        // set value
        double[][] values = barChartVO.values;
        String[] categories = barChartVO.categories;
        String[] elements = barChartVO.elements;
        for (int i = 0; i < categories.length; i++) {
            XYChart.Series series = new XYChart.Series();
            series.setName( categories[i] );
            for (int j = 0; j < elements.length; j++) {
                series.getData().add(new XYChart.Data(elements[j], values[i][j]));
            }
            barChart.getData().add(series);
        }

    }

    private void refreshPieChart() {
        pieChart.getData().clear();
        if (pieType_ChoiceBox.getValue()==null) {
			return;
		}
        FinancePieChartType type = pieType_ChoiceBox.getValue().getEnum();
        PieChartVO pieChartVO = financeChartBLService
                .getPieChart(getBegin(), getEnd(), type);

        ObservableList<PieChart.Data> datas = FXCollections.observableArrayList();
        Set<Map.Entry<String, Double>> vo_datas = pieChartVO.originMap.entrySet();
        for (Map.Entry<String, Double> vo_data : vo_datas) {
            datas.add(new PieChart.Data(vo_data.getKey(), vo_data.getValue()));
        }
        pieChart.setData(datas);
        pieChart.setTitle(pieChartVO.title);
    }

    private Calendar getBegin(){
        return TimeConvert.convertDate(begin_DatePicker.getValue());
    }

    private Calendar getEnd(){
        return TimeConvert.convertDate(end_DatePicker.getValue());
    }

}
