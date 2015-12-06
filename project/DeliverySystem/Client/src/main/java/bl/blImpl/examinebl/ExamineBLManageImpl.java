package bl.blImpl.examinebl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.FormEnum;
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
	private ArrayList<FormVO> formVOs;
	public ExamineBLManageImpl(VOPOFactory vopoFactory){
		this.examineManageService=CacheHelper.getExamineManageService();
		this.vopoFactory=vopoFactory;
		formVOs=new ArrayList<FormVO>();
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
		this.refresh();
		for (FormVO formVO : formVOs) {
			if (formVO.formID.equalsIgnoreCase(formID)) {
				return formVO;
			}
		}
		return null;

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
	/* (non-Javadoc)
	 * @see bl.blService.examineblService.ExamineblManageService#getForms(po.FormEnum)
	 */
	@Override
	public ArrayList<FormVO> getForms(FormEnum formType) {
		ArrayList<FormVO> result = new ArrayList<FormVO>();
		for (int i = 0; i < formVOs.size(); i++) {
			FormVO temp = formVOs.get(i);
			if (checkFormType(temp.getFormType(), formType)) {

				result.add(temp);
			}

		}
		return result;
	}
	//
	private boolean checkFormType(FormEnum po,FormEnum std){
		if (std==null) {
			return true;
		}
		else {
			return po==std;
		}
	}


	/* (non-Javadoc)
	 * @see bl.blService.examineblService.ExamineblManageService#refresh()
	 */
	@Override
	public OperationMessage refresh() {
		try {
			ArrayList<FormPO> formPOs=examineManageService.getForms();
			for (FormPO formPO : formPOs) {
				FormVO vo=(FormVO)vopoFactory.transPOtoVO(formPO);
				formVOs.add(vo);
			}
			return new OperationMessage();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new OperationMessage(false, e.getMessage());
		}
	}

}
