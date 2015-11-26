package bl.blImpl.financebl;

import bl.blService.financeblService.FinanceChartBLService;
import vo.financevo.*;

import java.util.Calendar;

/**
 * Created by Sissel on 2015/10/26.
 */
public class FinanceChartBLImpl implements FinanceChartBLService {
	ChartMaker chartMaker;

	/* (non-Javadoc)
	 * @see bl.blService.financeblService.FinanceChartBLService#getCompanyState()
	 */
	public CalculateVO getCompanyState() {
		// TODO Auto-generated method stub
		return new CalculateVO();
	}

    @Override
    public BaseChartVO getBarChart(Calendar begin, Calendar end, FinanceBaseChartType type) {
        return new BaseChartVO();
    }

    @Override
    public PieChartVO getPieChart(Calendar begin, Calendar end, FinancePieChartType type) {
        return new PieChartVO();
    }

    @Override
    public BaseChartVO getLineChart(Calendar begin, Calendar end, FinanceBaseChartType type) {
        return new BaseChartVO();
    }

    @Override
    public CalculateVO getCompanyState(Calendar begin, Calendar end) {
        return new CalculateVO();
    }
}
