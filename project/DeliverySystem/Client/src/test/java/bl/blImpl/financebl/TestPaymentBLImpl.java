package bl.blImpl.financebl;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import po.financedata.FinancePayEnum;
import vo.financevo.PaymentVO;
import bl.blService.financeblService.PaymentBLService;
import tool.draft.DraftController;
import tool.vopo.VOPOFactory;

/** 
 * Client//bl.blImpl.financebl//TestPaymentBLImpl.java
 * @author CXWorks
 * @date 2015年11月16日 上午10:09:53
 * @version 1.0 
 */
public class TestPaymentBLImpl {
	PaymentBLService pay;
	PaymentVO p;
	LinkedList<PaymentVO> list;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		pay=new PaymentBLImpl(new VOPOFactory(),new DraftController());
		list=new LinkedList<PaymentVO>();
		p=new PaymentVO("11",Calendar.getInstance(), "3432", "432333", "程翔", "43242", "43243", "刘钦", "3223", FinancePayEnum.RENT, "no");
		PaymentVO[] a=new PaymentVO[3];
		a[0]=new PaymentVO("11",Calendar.getInstance(), "3432", "432333", "程翔", "43242", "43243", "刘钦", "3223", FinancePayEnum.AWARD, "no");
		a[1]=new PaymentVO("11",Calendar.getInstance(), "3432", "4333", "王嘉琛", "43242", "43243", "吴嘉荣", "3223", FinancePayEnum.SALARY, "no");
		a[2]=new PaymentVO("11",Calendar.getInstance(), "3432", "487933", "孟鑫", "43242", "43243", "丁二玉", "3223", FinancePayEnum.TRANSPORTION, "no");
		for (int i = 0; i < a.length; i++) {
			list.add(a[i]);
		}
	}

	@Test
	public void testGet() {
		assertNotNull(pay.getNewPaymentID("2015.11.16"));
		assertNotNull(pay.getPaymentVO("0003"));
		assertNotNull(pay.getPaymentVOs(Calendar.getInstance(), Calendar.getInstance()));
		assertNotNull(pay.newID());
		assertNotNull(pay.loadDraft());
	}
	@Test
	public void testManage(){
		assertTrue(pay.saveDraft(p).operationResult);
		assertTrue(pay.submit(p).operationResult);
	}

}
