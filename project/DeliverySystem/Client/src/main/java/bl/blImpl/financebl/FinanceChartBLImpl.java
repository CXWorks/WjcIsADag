package bl.blImpl.financebl;

import bl.blService.financeblService.FinanceChartBLService;
import vo.financevo.BaseChartVO;
import vo.financevo.CalculateVO;
import vo.financevo.PieChartVO;

/**
 * Created by Sissel on 2015/10/26.
 */
public class FinanceChartBLImpl implements FinanceChartBLService {
    public BaseChartVO getHistogram() {
        return new BaseChartVO();
    }

    public PieChartVO getPieChart() {
        return new PieChartVO();
    }

    public BaseChartVO getLineChart() {
        return new BaseChartVO();
    }

	/* (non-Javadoc)
	 * @see bl.blService.financeblService.FinanceChartBLService#getCompanyState()
	 */
	public CalculateVO getCompanyState() {
		// TODO Auto-generated method stub
		return null;
	}
}
