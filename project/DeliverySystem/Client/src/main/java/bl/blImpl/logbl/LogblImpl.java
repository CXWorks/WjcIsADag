package bl.blImpl.logbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;

import po.systemdata.LogPO;
import rmi.systemdata.LogDataService;
import sun.util.logging.resources.logging;
import tool.vopo.VOPOFactory;
import message.OperationMessage;
import vo.systemvo.LogVO;
import bl.blService.logblService.LogblService;
import bl.clientNetCache.CacheHelper;

/**
 * Client//bl.blImpl.logbl//LogBLImpl.java
 * @author CXWorks
 * @date 2015年11月29日 下午5:13:26
 * @version 1.0
 */
public class LogblImpl implements LogblService {
	private LogDataService LogDataService;
	private VOPOFactory vopoFactory;
	private ArrayList<LogVO> now;
	private TXTHelper txtHelper;
	public LogblImpl(VOPOFactory vopoFactory){
		this.vopoFactory=vopoFactory;
		LogDataService=CacheHelper.getLogDataService();
		this.txtHelper=new TXTHelper();
		this.now=null;
	}

	/* (non-Javadoc)
	 * @see bl.blService.logblService.LogblService#fuzzyQuery(java.lang.String)
	 */
	@Override
	public ArrayList<LogVO> fuzzyQuery(String info) {
		ArrayList<LogVO> ans=new ArrayList<LogVO>();
		for (LogVO logVO : now) {
			if (logVO.fuzzyCheck(info)) {
				ans.add(logVO);
			}
		}
		return ans;
	}

	/* (non-Javadoc)
	 * @see bl.blService.logblService.LogblService#dateSearch(java.util.Calendar, java.util.Calendar)
	 */
	@Override
	public ArrayList<LogVO> dateSearch(Calendar start, Calendar end) {
		try {
			ArrayList<LogPO> logPOs=LogDataService.getByTime(start, end);
			ArrayList<LogVO> now=new ArrayList<LogVO>(logPOs.size());
			for (LogPO logPO : logPOs) {
				LogVO temp=(LogVO)vopoFactory.transPOtoVO(logPO);
				now.add(temp);
			}
			return now;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	/* (non-Javadoc)
	 * @see bl.blService.logblService.LogblService#exportToTXT(java.lang.String)
	 */
	@Override
	public OperationMessage exportToTXT(String path) {
		// TODO Auto-generated method stub
		return this.txtHelper.exportToTXT(now, path);
	}

}
