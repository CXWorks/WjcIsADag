package bl.blImpl.financebl;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import tool.draft.DraftController;
import tool.vopo.VOPOFactory;
import vo.financevo.RevenueVO;
import bl.blService.financeblService.RevenueBLService;

/** 
 * Client//bl.blImpl.financebl//TestRevenueBLImpl.java
 * @author CXWorks
 * @date 2015年11月16日 上午10:33:45
 * @version 1.0 
 */
public class TestRevenueBLImpl {
	RevenueBLService re;
	RevenueVO revenue;
	

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		re=new RevenueBLImpl(new VOPOFactory(),new DraftController());
		//revenue=new RevenueVO("11",Calendar.getInstance(), "300", "plane", "5839", "0034");
	}

	@Test
	public void testGet() {
		assertNotNull(re.getNewRevenueID(Calendar.getInstance()));
		assertNotNull(re.getRevenueVO("534222"));
		assertNotNull(re.getRevenueVO( "0302"));
		assertNotNull(re.getRevenueVOs(Calendar.getInstance(), Calendar.getInstance()));
		assertNotNull(re.loadDraft());
	}
	
	@Test
	public void testManage(){
		assertTrue(re.saveDraft(revenue).operationResult);
		assertTrue(re.submit(revenue).operationResult);
	}

}
