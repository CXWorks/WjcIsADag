package bl.blImpl.order;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import po.orderdata.DeliverTypeEnum;
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
		o = new OrderVO("程翔","孟鑫","南京仙林","南京鼓楼","南京仙林厅","南京鼓楼厅","0411-87620011","025-84561234",
				"186999988888","15699998888","2","iPhone 6s","1",DeliverTypeEnum.NORMAL);
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
