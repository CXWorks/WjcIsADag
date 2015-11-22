package bl.blImpl.storebl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import model.store.StoreAreaCode;

import org.junit.Before;
import org.junit.Test;

import vo.storevo.StoreInVO;
import bl.blService.storeblService.StoreIOBLService;
import bl.blService.storeblService.StoreInBLService;

/** 
 * @author Wjc
 * @date 2015年11月16日
 * @version 1.0 
 */
public class TestStoreIO {
	StoreIOBLService storeIOBLService;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		storeIOBLService = new StoreIOBLImpl();
	}
	
	@Test
	public void testCheckFormat(){
		assertNotNull(storeIOBLService.checkFormat("","","",""));
		assertTrue(storeIOBLService.checkFormat("","","","").get(0).getCheckResult());
	}
	
	@Test
	public void testFilterGoods(){
		assertNotNull(storeIOBLService.filterGoods("111111"));
	}
	
	@Test
	public void testGetGoodsInfo(){
		assertNotNull(storeIOBLService.getGoodsInfo("","","",""));
	}
}
