package bl.blImpl.examinebl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.FormPO;
import rmi.examineService.ExamineManageService;
import message.OperationMessage;
import vo.FormVO;
import vo.ordervo.OrderVO;
import bl.blService.examineblService.ExamineblManageService;
import bl.clientNetCache.CacheHelper;
import tool.vopo.VOPOFactory;

/** 
 * Client//blImpl.examinebl//ExamineBLManageImpl.java
 * @author CXWorks
 * @date 2015年10月26日 上午8:29:04
 * @version 1.0 
 */
public class ExamineBLManageImpl implements ExamineblManageService {
	private ExamineManageService examineManageService;
	private VOPOFactory vopoFactory;
	public ExamineBLManageImpl(VOPOFactory vopoFactory){
		this.examineManageService=CacheHelper.getExamineManageService();
		this.vopoFactory=vopoFactory;
	}
	/* (non-Javadoc)
	 * @see blService.examineblService.ExamineblManageService#getForms()
	 */
	public ArrayList<FormVO> getForms() {
		try {
			ArrayList<FormPO> po=examineManageService.getForms();
			ArrayList<FormVO> result=new ArrayList<FormVO>(po.size());
			for (int i = 0; i < po.size(); i++) {
				FormPO each=po.get(i);
				FormVO temp=(FormVO)vopoFactory.transPOtoVO(each);
				result.add(temp);
			}
			return result;
		} catch (RemoteException e) {
			return null;
		}
		
	}

	/* (non-Javadoc)
	 * @see blService.examineblService.ExamineblManageService#passForm(java.util.ArrayList)
	 */
	public OperationMessage passForm(ArrayList<FormVO> form) {
		ArrayList<FormPO> po=new ArrayList<FormPO>(form.size());
		for (int i = 0; i < form.size(); i++) {
			FormVO each=form.get(i);
			FormPO temp=(FormPO)vopoFactory.transVOtoPO(each);
			po.add(temp);
		}
		try {
			return examineManageService.passForm(po);
		} catch (RemoteException e) {
			return new OperationMessage(false, "net error");
		}
		
	}

	/* (non-Javadoc)
	 * @see blService.examineblService.ExamineblManageService#deleteForm(java.util.ArrayList)
	 */
	public OperationMessage deleteForm(ArrayList<FormVO> form) {
		ArrayList<FormPO> po=new ArrayList<FormPO>(form.size());
		for (int i = 0; i < form.size(); i++) {
			FormVO each=form.get(i);
			FormPO temp=(FormPO)vopoFactory.transVOtoPO(each);
			po.add(temp);
		}
		try {
			return examineManageService.deleteForm(po);
		} catch (RemoteException e) {
			return new OperationMessage(false, "net error");
		}
	}

	/* (non-Javadoc)
	 * @see blService.examineblService.ExamineblManageService#getForm(vo.FormVO)
	 */
	public FormVO getForm(String formID) {
		try {
			boolean found=false;
			FormPO each=null;
			ArrayList<FormPO> po=examineManageService.getForms();
			for (int i = 0; i < po.size(); i++) {
				each=po.get(i);
				if (each.getFormID().equalsIgnoreCase(formID)) {
					found=true;
					break;
				}
			}
			//
			if (found) {
				FormVO vo=(FormVO)vopoFactory.transPOtoVO(each);
				return vo;
			} else {
				return null;
			}
		} catch (RemoteException e) {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see blService.examineblService.ExamineblManageService#getFormHistory()
	 */
	public ArrayList<FormVO> getFormHistory() {
		// TODO discuss with JC// get all for each
		ArrayList<FormVO> result=new ArrayList<FormVO>();
		return result;
	}
	/* (non-Javadoc)
	 * @see bl.blService.examineblService.ExamineblManageService#modifyForm(vo.FormVO)
	 */
	@Override
	public OperationMessage modifyForm(FormVO form) {
		FormPO po=(FormPO)vopoFactory.transVOtoPO(form);
		try {
			return examineManageService.modifyForm(po);
		} catch (RemoteException e) {
			return new OperationMessage(false, "net error");
		}
	}

}
