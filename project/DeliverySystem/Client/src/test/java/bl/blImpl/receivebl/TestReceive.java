package bl.blImpl.receivebl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import vo.receivevo.ReceiveVO;
import bl.blService.receiveblService.ReceiveBLService;

public class TestReceive {
	ReceiveBLService receiveBLService;
	ReceiveVO r;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		receiveBLService = new ReceiveblImpl();
	}
	
	@Test
	public void testCheckFormat(){
		assertNotNull(receiveBLService.checkFormat(r,true));
		assertTrue(receiveBLService.checkFormat(r,true).get(0).checkResult);
	}
	
	@Test
	public void testSubmit(){
		assertTrue(receiveBLService.submit(r).operationResult);
	}
	
	@Test
	public void testNewID(){
		assertNotNull(receiveBLService.newID());
	}
	
	@Test
	public void testGetOrder(){
		assertNotNull(receiveBLService.getOrderVO("111111"));
	}
	
	@Test
	public void testGetTransitVO(){
		assertNotNull(receiveBLService.getTransitVO());
	}
}
