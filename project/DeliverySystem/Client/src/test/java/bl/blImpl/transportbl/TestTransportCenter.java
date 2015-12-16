package bl.blImpl.transportbl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import po.transportdata.TransportationEnum;
import tool.draft.DraftController;
import tool.vopo.VOPOFactory;
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
		transportCenterBLService = new TransportCenterBLImpl(new VOPOFactory(),new DraftController());
		co = new ArrayList<CenterOutVO>();
		CenterOutVO[] centerout= new CenterOutVO[3];
		centerout[0] = new CenterOutVO("11", "广州", "111111", "1", Calendar.getInstance(), "111111", "北京", "人1",null,TransportationEnum.PLANE,null);
		centerout[1] = new CenterOutVO("11", "大连", "111111", "2", Calendar.getInstance(), "111111", "北京", "人2",null,TransportationEnum.CAR,null);
		centerout[2] = new CenterOutVO("11", "洛阳", "111111", "3", Calendar.getInstance(), "111111", "北京", "人3",null,TransportationEnum.TRAIN,null);
		for (int i = 0; i < centerout.length; i++) {
			co.add(centerout[i]);
		}
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
