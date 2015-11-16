package bl.blImpl.financebl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import bl.blService.financeblService.FinanceChartBLService;

/** 
 * Client//bl.blImpl.financebl//TestFinanceChartBLImpl.java
 * @author CXWorks
 * @date 2015年11月16日 上午10:03:13
 * @version 1.0 
 */
public class TestFinanceChartBLImpl {
	FinanceChartBLService financeChartBLService;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		financeChartBLService=new FinanceChartBLImpl();
	}

	@Test
	public void testGet() {
		assertNotNull(financeChartBLService.getCompanyState());
		assertNotNull(financeChartBLService.getHistogram());
		assertNotNull(financeChartBLService.getLineChart());
		assertNotNull(financeChartBLService.getPieChart());
	}

}
