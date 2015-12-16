package bl.blImpl.receivebl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import po.receivedata.StateEnum;
import vo.receivevo.ReceiveVO;
import bl.blImpl.formatCheck.FormatCheckImpl;
import bl.blService.receiveblService.ReceiveBLService;
import tool.draft.DraftController;
import tool.vopo.VOPOFactory;

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
		receiveBLService = new ReceiveblImpl(new VOPOFactory(), new DraftController(), new FormatCheckImpl());
		r = new ReceiveVO("11","1111111","1111111",Calendar.getInstance(),"南京仙林",StateEnum.Complete,null);
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
}
