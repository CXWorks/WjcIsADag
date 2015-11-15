package bl.blImpl.logbl;

import java.util.ArrayList;
import java.util.Calendar;

import vo.FormVO;
import bl.blService.logblService.LogblService;

/** 
 * Client//blImpl.logbl//LogblImpl.java
 * @author CXWorks
 * @date 2015年10月26日 上午8:31:30
 * @version 1.0 
 */
public class LogblImpl implements LogblService {

	/* (non-Javadoc)
	 * @see blService.logblService.LogblService#getHistory()
	 */
	public ArrayList<FormVO> getHistory() {
		// TODO Auto-generated method stub
		ArrayList<FormVO> result=new ArrayList<FormVO>();
		FormVO stub=new FormVO();
		result.add(stub);
		return result;
	}

	/* (non-Javadoc)
	 * @see bl.blService.logblService.LogblService#fuzzyQuery(java.lang.String)
	 */
	public ArrayList<FormVO> fuzzyQuery(String info) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see bl.blService.logblService.LogblService#dateSearch(java.util.Calendar)
	 */
	public ArrayList<FormVO> dateSearch(Calendar date) {
		// TODO Auto-generated method stub
		return null;
	}

}
