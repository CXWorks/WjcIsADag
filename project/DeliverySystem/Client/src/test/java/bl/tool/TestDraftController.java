package bl.tool;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import po.FormEnum;
import vo.delivervo.DeliverVO;
import vo.financevo.PaymentVO;
import vo.financevo.RevenueVO;
import vo.ordervo.OrderVO;
import vo.receivevo.ReceiveVO;
import vo.storevo.StoreInVO;
import vo.storevo.StoreOutVO;
import vo.transitvo.CenterOutVO;
import vo.transitvo.LoadVO;
import bl.tool.draft.DraftController;
import bl.tool.draft.DraftService;

/** 
 * Client//bl.tool//TestDraftController.java
 * @author CXWorks
 * @date 2015年11月20日 下午7:01:08
 * @version 1.0 
 */
public class TestDraftController {
	DraftService draftService;
	OrderVO order;
	DeliverVO deliver;
	PaymentVO payment;
	ReceiveVO receive;
	RevenueVO revenue;
	StoreInVO store_in;
	StoreOutVO store_out;
	LoadVO load;
	CenterOutVO center_out;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		draftService=new DraftController();
		order=new OrderVO();
		deliver=new DeliverVO();
		payment=new PaymentVO();
		receive=new ReceiveVO();
		revenue=new RevenueVO();
		store_in=new StoreInVO();
		store_out=new StoreOutVO();
		load=new LoadVO();
		center_out=new CenterOutVO();
	}

	/**
	 * Test method for {@link bl.tool.draft.DraftController#saveDraft(vo.FormVO)}.
	 */
	@Test
	public void testSaveDraft() {
		assertTrue(draftService.saveDraft(order).operationResult);
		assertTrue(draftService.saveDraft(center_out).operationResult);
		assertTrue(draftService.saveDraft(deliver).operationResult);
		assertTrue(draftService.saveDraft(load).operationResult);
		assertTrue(draftService.saveDraft(order).operationResult);
		assertTrue(draftService.saveDraft(payment).operationResult);
		assertTrue(draftService.saveDraft(receive).operationResult);
		assertTrue(draftService.saveDraft(revenue).operationResult);
		assertTrue(draftService.saveDraft(store_in).operationResult);
		assertTrue(draftService.saveDraft(store_out).operationResult);
	}

	/**
	 * Test method for {@link bl.tool.draft.DraftController#getDraft(po.FormEnum)}.
	 */
	@Test
	public void testGetDraft() {
		assertNotNull(draftService.getDraft(FormEnum.ORDER));
		assertNotNull(draftService.getDraft(FormEnum.DELIVER));
		assertNotNull(draftService.getDraft(FormEnum.PAYMENT));
		assertNotNull(draftService.getDraft(FormEnum.RECEIVE));
		assertNotNull(draftService.getDraft(FormEnum.REVENUE));
		assertNotNull(draftService.getDraft(FormEnum.STORE_IN));
		assertNotNull(draftService.getDraft(FormEnum.STORE_OUT));
		assertNotNull(draftService.getDraft(FormEnum.TRANSPORT_CENTER));
		assertNotNull(draftService.getDraft(FormEnum.TRANSPORT_HALL));
	}

}
