package bl.blImpl.storebl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import model.store.StoreAreaCode;

import org.junit.Before;
import org.junit.Test;

import vo.storevo.StoreInVO;
import bl.blService.storeblService.StoreIOBLService;
import bl.blService.storeblService.StoreInBLService;

public class TestStoreIO {
	StoreIOBLService storeIOBLService;
	StoreInVO si;
	
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
		assertTrue(storeIOBLService.checkFormat("","","","").get(0).checkResult);
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
