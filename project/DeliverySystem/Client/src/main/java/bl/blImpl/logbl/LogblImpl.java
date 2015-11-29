package bl.blImpl.logbl;

import java.util.ArrayList;
import java.util.Calendar;

import vo.systemvo.LogVO;
import bl.blService.logblService.LogblService;

/** 
 * Client//bl.blImpl.logbl//LogBLImpl.java
 * @author CXWorks
 * @date 2015年11月29日 下午5:13:26
 * @version 1.0 
 */
public class LogBLImpl implements LogblService {

	/* (non-Javadoc)
	 * @see bl.blService.logblService.LogblService#getHistory()
	 */
	@Override
	public ArrayList<LogVO> getHistory() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see bl.blService.logblService.LogblService#fuzzyQuery(java.lang.String)
	 */
	@Override
	public ArrayList<LogVO> fuzzyQuery(String info) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see bl.blService.logblService.LogblService#dateSearch(java.util.Calendar, java.util.Calendar)
	 */
	@Override
	public ArrayList<LogVO> dateSearch(Calendar start, Calendar end) {
		// TODO Auto-generated method stub
		return null;
	}

}
