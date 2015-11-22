package bl.blImpl.examinebl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import po.FormEnum;
import po.FormStateEnum;
import po.orderdata.DeliverTypeEnum;
import rmi.examineService.ExamineManageService;
import vo.FormVO;
import vo.delivervo.DeliverVO;
import vo.ordervo.OrderVO;
import bl.blService.examineblService.ExamineblManageService;
import bl.blService.examineblService.ExamineblSubmitService;

/** 
 * Client//bl.blImpl.examinebl//TestExamineManage.java
 * @author CXWorks
 * @date 2015年11月16日 上午8:59:34
 * @version 1.0 
 */
public class TestExamineManage {
	ExamineblManageService examineblManageService;
	ArrayList<FormVO> o;
	ArrayList<FormVO> d;
	

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		examineblManageService=new ExamineBLManageImpl();
		o=new ArrayList<FormVO>();
		d=new ArrayList<FormVO>();
		//object
		OrderVO[] order=new OrderVO[3];
		order[0]=new OrderVO("程翔", "王嘉琛", "南京", "北京", "", "", "18351890356", "13724456739", "3", "图书", "", "", "", DeliverTypeEnum.NORMAL);
		order[1]=new OrderVO("刘钦", "丁二玉", "南京", "南京", "", "", "18351436356", "13724456739", "3", "图书", "", "", "", DeliverTypeEnum.FAST);
		order[2]=new OrderVO("邵栋", "郑滔", "广州", "北京", "", "", "18351630356", "13724456739", "3", "图书", "", "", "", DeliverTypeEnum.SLOW);
		for (int i = 0; i < order.length; i++) {
			o.add(order[i]);
		}
		DeliverVO[] deliver=new DeliverVO[3];
		deliver[0]=new DeliverVO("0001", Calendar.getInstance(), "快递哥1");
		deliver[1]=new DeliverVO("0002", Calendar.getInstance(), "快递哥46");
		deliver[2]=new DeliverVO("0309", Calendar.getInstance(), "快递哥24");
		for (int i = 0; i < deliver.length; i++) {
			d.add(deliver[i]);
		}
		
	}

	@Test
	public void testPass() {
		assertTrue(examineblManageService.passForm(o).operationResult);
		assertTrue(examineblManageService.passForm(d).operationResult);
	}
	@Test
	public void testDelete(){
		assertTrue(examineblManageService.deleteForm(o).operationResult);
		assertTrue(examineblManageService.deleteForm(d).operationResult);
	}
	@Test
	public void testGet(){
		for (int i = 0; i < o.size(); i++) {
			assertNotNull(examineblManageService.getForm(o.get(i)));
			assertNotNull(examineblManageService.getForm(d.get(i)));
		}
		//
		assertNotNull(examineblManageService.getForms());
	}

}
