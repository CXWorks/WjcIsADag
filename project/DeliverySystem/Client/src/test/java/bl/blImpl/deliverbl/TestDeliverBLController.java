package bl.blImpl.deliverbl;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import vo.delivervo.DeliverVO;
import vo.ordervo.OrderVO;
import bl.blImpl.formatCheck.FormatCheckImpl;
import bl.blService.deliverblService.DeliverBLService;
import bl.tool.draft.DraftController;
import bl.tool.vopo.VOPOFactory;

/** 
 * Client//bl.blImpl.deliverbl//TestDeliverBLController.java
 * @author CXWorks
 * @date 2015年11月16日 下午7:52:36
 * @version 1.0 
 */
public class TestDeliverBLController {
	DeliverBLService deliverBLService;
	DeliverVO d;
	OrderVO o;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		deliverBLService=new DeliverBLController(new VOPOFactory(),new DraftController(),new FormatCheckImpl());
		d=new DeliverVO("11","34", Calendar.getInstance(), "4322");
		o=new OrderVO("11");
	}

	/**
	 * Test method for {@link bl.blImpl.deliverbl.DeliverBLController#newID()}.
	 */
	@Test
	public void testNewID() {
		assertNotNull(deliverBLService.newID());
	}


	/**
	 * Test method for {@link bl.blImpl.deliverbl.DeliverBLController#submit(vo.delivervo.DeliverVO)}.
	 */
	@Test
	public void testSubmit() {
		assertTrue(deliverBLService.submit(d).operationResult);
	}

	/**
	 * Test method for {@link bl.blImpl.deliverbl.DeliverBLController#saveDraft(vo.delivervo.DeliverVO)}.
	 */
	@Test
	public void testSaveDraft() {
		assertTrue(deliverBLService.saveDraft(d).operationResult);
	}

	/**
	 * Test method for {@link bl.blImpl.deliverbl.DeliverBLController#loadDraft()}.
	 */
	@Test
	public void testLoadDraft() {
		assertNotNull(deliverBLService.loadDraft());;
	}

	/**
	 * Test method for {@link bl.blImpl.deliverbl.DeliverBLController#getOrderVO(java.lang.String)}.
	 */
	@Test
	public void testGetOrderVO() {
		assertNotNull(deliverBLService.getOrderVO("4232"));
	}

}
