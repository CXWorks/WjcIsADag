package bl.blImpl.storebl;

import static org.junit.Assert.assertTrue;
import model.store.StoreAreaCode;

import org.junit.Before;
import org.junit.Test;

import bl.blService.storeblService.StoreModelBLService;

/** 
 * @author Wjc
 * @date 2015年11月16日
 * @version 1.0 
 */
public class TestStoreModel {
	StoreModelBLService storeModelBLService;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		storeModelBLService = new StoreModelBLImpl();
	}
	
	@Test
	public void testSetWarningLine(){
		assertTrue(storeModelBLService.setWarningLine(1.0).operationResult);
	}
	
	@Test
	public void testReducePartition(){
		assertTrue(storeModelBLService.reducePartition(StoreAreaCode.AIR, 1).operationResult);
		assertTrue(storeModelBLService.reducePartition(StoreAreaCode.FLEX, 1).operationResult);
		assertTrue(storeModelBLService.reducePartition(StoreAreaCode.RAIL, 1).operationResult);
		assertTrue(storeModelBLService.reducePartition(StoreAreaCode.ROAD, 1).operationResult);
	}
	

	
	@Test
	public void testExpandPartition(){
		assertTrue(storeModelBLService.expandPartition(StoreAreaCode.AIR, 1).operationResult);
		assertTrue(storeModelBLService.expandPartition(StoreAreaCode.FLEX, 1).operationResult);
		assertTrue(storeModelBLService.expandPartition(StoreAreaCode.RAIL, 1).operationResult);
		assertTrue(storeModelBLService.expandPartition(StoreAreaCode.ROAD, 1).operationResult);
	}
	

	}
