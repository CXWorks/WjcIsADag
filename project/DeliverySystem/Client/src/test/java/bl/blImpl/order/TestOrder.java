package bl.blImpl.order;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import vo.ordervo.OrderVO;
import bl.blImpl.orderbl.OrderBLController;
import bl.blService.orderblService.OrderBLService;

/** 
 * @author Wjc
 * @date 2015年11月16日
 * @version 1.0 
 */
public class TestOrder {
	OrderBLService orderBLService;
	OrderVO o;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		orderBLService = new OrderBLController();
	}
	
	@Test
	public void testCheckFormat(){
		assertNotNull(orderBLService.checkFormat(o,true));
		assertTrue(orderBLService.checkFormat(o,true).get(0).checkResult);
	}
	
	@Test
	public void testPredict(){
		assertNotNull(orderBLService.predict(o));
	}
	
	@Test
	public void testSubmit(){
		assertTrue(orderBLService.submit(o).operationResult);
	}
	
	@Test
	public void testNewID(){
		assertNotNull(orderBLService.newID());
	}
}
