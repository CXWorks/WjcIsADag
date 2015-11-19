package bl.blImpl.transportbl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import vo.FormVO;
import vo.transitvo.CenterOutVO;
import bl.blService.transportblService.TransportCenterBLService;

/** 
 * @author Wjc
 * @date 2015年11月16日
 * @version 1.0 
 */
public class TestTransportCenter {
	TransportCenterBLService transportCenterBLService;
	ArrayList<CenterOutVO> co;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		transportCenterBLService = new TransportCenterBLImpl();
		co = new ArrayList<CenterOutVO>();
		CenterOutVO[] centerout= new CenterOutVO[3];
		centerout[0] = new CenterOutVO( "广州", "111111", "1", Calendar.getInstance(), "111111", "北京", "人1");
		centerout[1] = new CenterOutVO( "大连", "111111", "2", Calendar.getInstance(), "111111", "北京", "人2");
		centerout[2] = new CenterOutVO( "洛阳", "111111", "3", Calendar.getInstance(), "111111", "北京", "人3");
		for (int i = 0; i < centerout.length; i++) {
			co.add(centerout[i]);
		}
	}
	
	@Test
	public void testCheckFormat(){
		assertNotNull(transportCenterBLService.checkFormat(co.get(0),true));
		assertNotNull(transportCenterBLService.checkFormat(co.get(0),false));
		assertNotNull(transportCenterBLService.checkFormat(co.get(1),true));
		assertNotNull(transportCenterBLService.checkFormat(co.get(1),false));
		assertNotNull(transportCenterBLService.checkFormat(co.get(2),true));
		assertNotNull(transportCenterBLService.checkFormat(co.get(2),false));
		assertTrue(transportCenterBLService.checkFormat(co.get(0),true).get(0).checkResult);
		
	}
	
	@Test
	public void testSubmit(){
		assertTrue(transportCenterBLService.submit(co.get(0)).operationResult);
		assertTrue(transportCenterBLService.submit(co.get(1)).operationResult);
		assertTrue(transportCenterBLService.submit(co.get(2)).operationResult);
	}
	
	@Test
	public void testNewID(){
		assertNotNull(transportCenterBLService.newID());
	}
	
	@Test
	public void testGetOrder(){
		assertNotNull(transportCenterBLService.getOrder("111111"));
	}
}
