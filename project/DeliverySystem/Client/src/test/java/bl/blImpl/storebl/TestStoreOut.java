package bl.blImpl.storebl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import model.store.StoreAreaCode;

import org.junit.Before;
import org.junit.Test;

import po.transportdata.TransportationEnum;
import vo.storevo.StoreInVO;
import vo.storevo.StoreOutVO;
import bl.blService.storeblService.StoreInBLService;
import bl.blService.storeblService.StoreOutBLService;

/** 
 * @author Wjc
 * @date 2015年11月16日
 * @version 1.0 
 */
public class TestStoreOut {
	StoreOutBLService storeOutBLService;
	StoreOutVO so;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		storeOutBLService = new StoreOutBLImpl();
		so = new StoreOutVO("111111",Calendar.getInstance(),"南京仙林",TransportationEnum.CAR,"111111");
	}
	
	@Test
	public void testCheckFormat(){
		assertNotNull(storeOutBLService.checkFormat(so, true));
		assertNotNull(storeOutBLService.checkFormat(so, false));
		assertTrue(storeOutBLService.checkFormat(so, true).get(0).getCheckResult());
		assertTrue(storeOutBLService.checkFormat(so, false).get(0).getCheckResult());
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
