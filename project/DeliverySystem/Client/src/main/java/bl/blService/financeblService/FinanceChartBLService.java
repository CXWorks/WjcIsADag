package bl.blService.financeblService;

import vo.financevo.BaseChartVO;
import vo.financevo.CalculateVO;
import vo.financevo.PieChartVO;

/**
 * Created by Sissel on 2015/10/25.
 */
public interface FinanceChartBLService {

    public BaseChartVO getHistogram();

    public PieChartVO getPieChart();

    public BaseChartVO getLineChart();
    
    public CalculateVO getCompanyState();
}
