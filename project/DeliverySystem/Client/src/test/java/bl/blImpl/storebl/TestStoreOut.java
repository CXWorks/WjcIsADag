package bl.blImpl.storebl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import model.store.StoreAreaCode;

import org.junit.Before;
import org.junit.Test;

import vo.storevo.StoreInVO;
import vo.storevo.StoreOutVO;
import bl.blService.storeblService.StoreInBLService;
import bl.blService.storeblService.StoreOutBLService;

public class TestStoreOut {
	StoreOutBLService storeOutBLService;
	StoreOutVO so;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		storeOutBLService = new StoreOutBLImpl();
		so = new StoreOutVO();
	}
	
	@Test
	public void testCheckFormat(){
		assertNotNull(storeOutBLService.checkFormat(so, true));
		assertNotNull(storeOutBLService.checkFormat(so, false));
		assertTrue(storeOutBLService.checkFormat(so, true).get(0).checkResult);
		assertTrue(storeOutBLService.checkFormat(so, false).get(0).checkResult);
	}
	
	@Test
	public void testSubmit(){
		assertTrue(storeOutBLService.submit(so).operationResult);
	}
	
	@Test
	public void testGetNewStoreOutID(){
		assertNotNull(storeOutBLService.getNewStoreOutID("2015-11-16"));
	}
	
	@Test
	public void testLoadOrder(){
		assertTrue(storeOutBLService.loadOrder("111111").operationResult);
	}
	
	@Test
	public void testGetOrder(){
		assertNotNull(storeOutBLService.getOrderVO());
	}
	
	@Test
	public void testGetTransportVO(){
		assertNotNull(storeOutBLService.getTransportVO());
	}
	
	@Test
	public void testClearLocalBuffer(){
		assertTrue(storeOutBLService.clearLocalBuffer().operationResult);
	}
}
