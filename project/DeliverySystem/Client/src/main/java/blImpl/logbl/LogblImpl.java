package blImpl.logbl;

import java.util.ArrayList;

import vo.FormVO;
import blService.logblService.LogblService;

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

}
