package bl.blService.financeblService;

import vo.financevo.*;

import java.util.Calendar;

/**
 * Created by Sissel on 2015/10/25.
 */
public interface FinanceChartBLService {

    public BaseChartVO getBarChart(Calendar begin, Calendar end, FinanceBaseChartType type);

    public PieChartVO getPieChart(Calendar begin, Calendar end, FinancePieChartType type);

    public BaseChartVO getLineChart(Calendar begin, Calendar end, FinanceBaseChartType type);
    
    public CalculateVO getCompanyState(Calendar begin, Calendar end);
}
