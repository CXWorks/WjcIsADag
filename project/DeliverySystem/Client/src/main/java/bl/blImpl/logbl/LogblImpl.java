package bl.blImpl.logbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import po.CommonPO;
import po.systemdata.LogPO;
import rmi.systemdata.LogDataService;
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

	private VOPOFactory vopoFactory;
	private ArrayList<LogPO> now;
	private TXTHelper txtHelper;

	public LogblImpl(VOPOFactory vopoFactory){
		this.vopoFactory=vopoFactory;
		
		this.txtHelper=new TXTHelper();
		this.now=new ArrayList<>();
	}

	@Override
	public ArrayList<LogVO> search(String keyword, Calendar start, Calendar end) {
		LogDataService logDataService =CacheHelper.getLogDataService();

		// first step : get datas from server
		try{
			if(start == null || end == null){	// if want all data
				// make new request, in case new data is inserted
				now = logDataService.getAll();
			}else{
				now = logDataService.getByTime(start, end);
			}
		}catch (RemoteException e) {
			e.printStackTrace();
		}

		ArrayList<LogVO> ans = new ArrayList<>();
        LinkedList<LogPO> notFit = new LinkedList<>();
		for (LogPO logPO : now) {
			LogVO logVO = (LogVO)vopoFactory.transPOtoVO(logPO);
			if (logVO.fuzzyCheck(keyword)) {
				ans.add(logVO);
			}else{
				notFit.add(logPO);
			}
		}
        now.removeAll(notFit);

		return ans;
	}

	/* (non-Javadoc)
	 * @see bl.blService.logblService.LogblService#exportToTXT(java.lang.String)
	 */
	@Override
	public OperationMessage exportToTXT(String path) {
		return this.txtHelper.exportToTXT((List<LogVO>)vopoFactory.transPOtoVO(now), path);
	}

}
