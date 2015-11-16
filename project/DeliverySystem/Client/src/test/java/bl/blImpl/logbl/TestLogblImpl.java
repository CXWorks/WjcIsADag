package bl.blImpl.logbl;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import bl.blService.logblService.LogblService;

/** 
 * Client//bl.blImpl.logbl//TestLogblImpl.java
 * @author CXWorks
 * @date 2015年11月16日 下午7:53:23
 * @version 1.0 
 */
public class TestLogblImpl {
	LogblService logblService;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		logblService=new LogblImpl();
	}

	/**
	 * Test method for {@link bl.blImpl.logbl.LogblImpl#getHistory()}.
	 */
	@Test
	public void testGetHistory() {
		assertNotNull(logblService.getHistory());
	}

	/**
	 * Test method for {@link bl.blImpl.logbl.LogblImpl#fuzzyQuery(java.lang.String)}.
	 */
	@Test
	public void testFuzzyQuery() {
		assertNotNull(logblService.fuzzyQuery("4242322"));
	}

	/**
	 * Test method for {@link bl.blImpl.logbl.LogblImpl#dateSearch(java.util.Calendar)}.
	 */
	@Test
	public void testDateSearch() {
		assertNotNull(logblService.dateSearch(Calendar.getInstance()));
	}

}
