package bl.blImpl.examinebl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import po.FormEnum;
import po.FormStateEnum;
import vo.FormVO;
import bl.blService.examineblService.ExamineblSubmitService;

/** 
 * Client//bl.blImpl.examinebl//TestExamineSubmit.java
 * @author CXWorks
 * @date 2015年11月16日 上午9:41:49
 * @version 1.0 
 */
public class TestExamineSubmit {
	ExamineblSubmitService examineblSubmitService;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		examineblSubmitService=new ExamineBLSubmitImpl();
	}

	@Test
	public void testSubmit() {
		assertTrue(examineblSubmitService.submit(new FormVO(FormEnum.DELIVER,FormStateEnum.SUBMIT,"0001")).operationResult);
		assertTrue(examineblSubmitService.submit(new FormVO(FormEnum.DELIVER,FormStateEnum.SUBMIT,"0043")).operationResult);
		assertTrue(examineblSubmitService.submit(new FormVO(FormEnum.MONEY_IN,FormStateEnum.SUBMIT,"5323")).operationResult);
		assertTrue(examineblSubmitService.submit(new FormVO(FormEnum.STORE_IN,FormStateEnum.SUBMIT,"6521")).operationResult);
		assertTrue(examineblSubmitService.submit(new FormVO(FormEnum.TRANSPORT_CENTER,FormStateEnum.SUBMIT,"6423")).operationResult);
		
	}

}
