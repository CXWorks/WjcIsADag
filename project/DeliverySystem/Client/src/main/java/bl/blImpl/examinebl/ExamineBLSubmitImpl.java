package bl.blImpl.examinebl;

import java.rmi.RemoteException;

import po.FormPO;
import message.OperationMessage;
import vo.FormVO;
import bl.blService.examineblService.ExamineblSubmitService;
import bl.clientNetCache.CacheHelper;
import bl.tool.vopo.VOPOFactory;

/** 
 * Client//blImpl.examinebl//ExamineBLSubmitImpl.java
 * @author CXWorks
 * @date 2015年10月26日 上午8:28:03
 * @version 1.0 
 */
public class ExamineBLSubmitImpl implements ExamineblSubmitService {
	VOPOFactory vopoFactory;

	/* (non-Javadoc)
	 * @see blService.examineblService.ExamineblSubmitService#submit(vo.FormVO)
	 */
	public OperationMessage submit(FormVO form) {
		// TODO Auto-generated method stub
		try {
			return CacheHelper.getExamineSubmitService().submit((FormPO)vopoFactory.transVOtoPO(form));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			return new OperationMessage(false, "net error");
		}
	}

}
