package bl.blImpl.deliverbl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import vo.delivervo.DeliverVO;
import bl.blService.deliverblService.CheckDeliverForm;
import bl.tool.vopo.VOPOFactory;

/** 
 * Client//bl.blImpl.deliverbl//TestCheckDeliverImpl.java
 * @author CXWorks
 * @date 2015年11月16日 下午7:52:09
 * @version 1.0 
 */
public class TestCheckDeliverImpl {
	CheckDeliverForm checkDeliverForm;
	ArrayList<DeliverVO> list;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		checkDeliverForm=new CheckDeliverImpl(new VOPOFactory());
		list=new ArrayList<DeliverVO>();
		list.add(new DeliverVO("11","321432", Calendar.getInstance(), "43232"));
		list.add(new DeliverVO("11","3232", Calendar.getInstance(), "887232"));
		list.add(new DeliverVO("11","3642", Calendar.getInstance(), "4342"));
	}

	/**
	 * Test method for {@link bl.blImpl.deliverbl.CheckDeliverImpl#getDeliverForms(java.lang.String)}.
	 */
	@Test
	public void testGetDeliverForms() {
		assertNotNull(checkDeliverForm.getDeliverForms("141250018"));
		assertNotNull(checkDeliverForm.getDeliverForms("141250137"));
		assertNotNull(checkDeliverForm.getDeliverForms("141250148"));
	}

	/**
	 * Test method for {@link bl.blImpl.deliverbl.CheckDeliverImpl#finishDelivery(java.util.ArrayList)}.
	 */
	@Test
	public void testFinishDelivery() {
		assertTrue(checkDeliverForm.finishDelivery(list).operationResult);
	}

}
