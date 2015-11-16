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
	public void testDeleteRow(){
		assertTrue(storeModelBLService.deleteRow(StoreAreaCode.AIR, 1, true).operationResult);
		assertTrue(storeModelBLService.deleteRow(StoreAreaCode.FLEX, 1, true).operationResult);
		assertTrue(storeModelBLService.deleteRow(StoreAreaCode.RAIL, 1, true).operationResult);
		assertTrue(storeModelBLService.deleteRow(StoreAreaCode.ROAD, 1, true).operationResult);
	}
	
	@Test
	public void testExpandPartition(){
		assertTrue(storeModelBLService.expandPartition(StoreAreaCode.AIR, 1).operationResult);
		assertTrue(storeModelBLService.expandPartition(StoreAreaCode.FLEX, 1).operationResult);
		assertTrue(storeModelBLService.expandPartition(StoreAreaCode.RAIL, 1).operationResult);
		assertTrue(storeModelBLService.expandPartition(StoreAreaCode.ROAD, 1).operationResult);
	}
	
	@Test
	public void testAddRow(){
		assertTrue(storeModelBLService.addRow(StoreAreaCode.AIR, 1).operationResult);
		assertTrue(storeModelBLService.addRow(StoreAreaCode.FLEX, 1).operationResult);
		assertTrue(storeModelBLService.addRow(StoreAreaCode.RAIL, 1).operationResult);
		assertTrue(storeModelBLService.addRow(StoreAreaCode.ROAD, 1).operationResult);
	}
	
	@Test
	public void testAdjustRow(){
		assertTrue(storeModelBLService.adjustRow(StoreAreaCode.AIR, 1, 1, true).operationResult);
		assertTrue(storeModelBLService.adjustRow(StoreAreaCode.FLEX, 1, 1, true).operationResult);
		assertTrue(storeModelBLService.adjustRow(StoreAreaCode.RAIL, 1, 1, true).operationResult);
		assertTrue(storeModelBLService.adjustRow(StoreAreaCode.ROAD, 1, 1, true).operationResult);
	}
	
	@Test
	public void testClearLocalBuffer(){
		assertTrue(storeModelBLService.clearLocalBuffer().operationResult);
	}
}
