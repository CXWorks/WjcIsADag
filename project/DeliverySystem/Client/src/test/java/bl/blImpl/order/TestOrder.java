package bl.blImpl.order;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import po.orderdata.OrderPO;
import vo.ordervo.OrderVO;
import vo.receivevo.ReceiveVO;
import bl.blImpl.orderbl.OrderBLController;
import bl.blImpl.receivebl.ReceiveblImpl;
import bl.blService.orderblService.OrderBLService;
import bl.blService.receiveblService.ReceiveBLService;

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
