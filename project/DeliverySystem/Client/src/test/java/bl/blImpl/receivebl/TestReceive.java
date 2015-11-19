package bl.blImpl.receivebl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import po.receivedata.StateEnum;
import vo.receivevo.ReceiveVO;
import bl.blService.receiveblService.ReceiveBLService;

/** 
 * @author Wjc
 * @date 2015年11月16日
 * @version 1.0 
 */
public class TestReceive {
	ReceiveBLService receiveBLService;
	ReceiveVO r;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		receiveBLService = new ReceiveblImpl();
		r = new ReceiveVO("1111111","1111111",Calendar.getInstance(),"南京仙林",StateEnum.Complete);
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
