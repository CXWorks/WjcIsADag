package bl.blImpl.storebl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import model.store.StoreAreaCode;
import model.store.StoreLocation;

import org.junit.Before;
import org.junit.Test;

import po.transportdata.TransportationEnum;
import vo.storevo.StoreInVO;
import bl.blService.storeblService.StoreInBLService;

/** 
 * @author Wjc
 * @date 2015年11月16日
 * @version 1.0 
 */
public class TestStoreIn {
	StoreInBLService storeInBLService;
	StoreInVO si;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		storeInBLService = new StoreInBLImpl();
		si = new StoreInVO("111111","2015-11-16","南京仙林",new StoreLocation());
	}
	
	@Test
	public void testCheckFormat(){
		assertNotNull(storeInBLService.checkFormat(si, true));
		assertNotNull(storeInBLService.checkFormat(si, false));
		assertTrue(storeInBLService.checkFormat(si, true).get(0).checkResult);
		assertTrue(storeInBLService.checkFormat(si, false).get(0).checkResult);
	}
	
	@Test
	public void testSubmit(){
		assertTrue(storeInBLService.submit(si).operationResult);
	}
	
	@Test
	public void testGetAvailableLocation(){
		assertNotNull(storeInBLService.getAvailableLocation(StoreAreaCode.AIR));
		assertNotNull(storeInBLService.getAvailableLocation(StoreAreaCode.FLEX));
		assertNotNull(storeInBLService.getAvailableLocation(StoreAreaCode.RAIL));
		assertNotNull(storeInBLService.getAvailableLocation(StoreAreaCode.ROAD));
	}
	
	@Test
	public void testGetNewStoreInID(){
		assertNotNull(storeInBLService.getNewStoreInID("2015-11-16"));
	}
	
	@Test
	public void testLoadOrder(){
		assertTrue(storeInBLService.loadOrder("111111").operationResult);
	}
	
	@Test
	public void testGetOrder(){
		assertNotNull(storeInBLService.getOrderVO());
	}
	
	@Test
	public void testClearLocalBuffer(){
		assertTrue(storeInBLService.clearLocalBuffer().operationResult);
	}
}
