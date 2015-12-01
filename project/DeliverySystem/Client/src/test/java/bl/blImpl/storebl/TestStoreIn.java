package bl.blImpl.storebl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import model.store.StoreAreaCode;
import model.store.StoreLocation;

import org.junit.Before;
import org.junit.Test;

import vo.storevo.StoreInVO;
import bl.blService.storeblService.StoreInBLService;
import tool.draft.DraftController;
import tool.vopo.VOPOFactory;

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
		storeInBLService = new StoreInBLImpl(new VOPOFactory(),new DraftController());
		si = new StoreInVO("11","111111",Calendar.getInstance(),"南京仙林",new StoreLocation(""));
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
	
	

}
