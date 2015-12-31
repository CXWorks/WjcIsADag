package bl.blImpl.examinebl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import po.FormEnum;
import po.FormPO;
import po.FormStateEnum;
import po.transportdata.LoadPO;
import rmi.examineService.ExamineManageService;
import message.OperationMessage;
import vo.FormVO;
import vo.ordervo.OrderVO;
import bl.NetReconnect.Reconnect;
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
	private VOPOFactory vopoFactory;
	private ArrayList<FormVO> formVOs;
	public ExamineBLManageImpl(VOPOFactory vopoFactory){
		
		this.vopoFactory=vopoFactory;
		formVOs=new ArrayList<FormVO>();
	}

	private void maintainData(ArrayList<FormVO> form){
		if (form.size()==formVOs.size()) {
			formVOs.clear();
			return;
		} else {
			ArrayList<FormVO> ans=new ArrayList<FormVO>();
			int i=0,j=0;
			while(i<form.size()&&j<formVOs.size()){
				FormVO temp=form.get(i);
				if (!temp.formID.equalsIgnoreCase(formVOs.get(j).formID)) {
					ans.add(temp);
				}
				j++;
				i++;
			}
			this.formVOs=ans;
		}
	}


	/* (non-Javadoc)
	 * @see blService.examineblService.ExamineblManageService#passForm(java.util.ArrayList)
	 */
	public OperationMessage passForm(ArrayList<FormVO> form) {
		ExamineManageService examineManageService=CacheHelper.getExamineManageService();
		this.maintainData(form);
		ArrayList<FormPO> po=new ArrayList<FormPO>(form.size());
		for (int i = 0; i < form.size(); i++) {
			FormVO each=form.get(i);
			FormPO temp=(FormPO)vopoFactory.transVOtoPO(each);
			po.add(temp);
		}
		try {
			return examineManageService.passForm(po);
			
		} catch (RemoteException e) {
			Reconnect.ReConnectFactory();
			return new OperationMessage(false, "net error");
		}

	}

	/* (non-Javadoc)
	 * @see blService.examineblService.ExamineblManageService#deleteForm(java.util.ArrayList)
	 */
	public OperationMessage deleteForm(ArrayList<FormVO> form) {
		ExamineManageService examineManageService=CacheHelper.getExamineManageService();
		this.maintainData(form);
		ArrayList<FormPO> po=new ArrayList<FormPO>(form.size());
		for (int i = 0; i < form.size(); i++) {
			FormVO each=form.get(i);
			FormPO temp=(FormPO)vopoFactory.transVOtoPO(each);
			po.add(temp);
		}
		try {
			return examineManageService.deleteForm(po);
		} catch (RemoteException e) {
			Reconnect.ReConnectFactory();
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
		ExamineManageService examineManageService=CacheHelper.getExamineManageService();
		ArrayList<FormVO> temp=new ArrayList<FormVO>(1);
		temp.add(form);
		//
		this.formVOs.removeIf(f->f.getFormID().equalsIgnoreCase(form.formID));
		formVOs.add(form);
		//
		FormPO po=(FormPO)vopoFactory.transVOtoPO(form);
		try {
			return examineManageService.modifyForm(po);
		} catch (RemoteException e) {
			Reconnect.ReConnectFactory();
			return new OperationMessage(false, "net error");
		}
	}
	/* (non-Javadoc)
	 * @see bl.blService.examineblService.ExamineblManageService#getForms(po.FormEnum)
	 */
	@Override
	public List<FormVO> getForms(FormEnum formType) {
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
		ExamineManageService examineManageService=CacheHelper.getExamineManageService();
		try {
			ArrayList<FormPO> formPOs=examineManageService.getForms();
			for (FormPO formPO : formPOs) {
				FormVO vo=(FormVO)vopoFactory.transPOtoVO(formPO);
				vo.state=FormStateEnum.SUBMIT;
				formVOs.add(vo);
			}
			return new OperationMessage();
		} catch (RemoteException e) {
			Reconnect.ReConnectFactory();
			return new OperationMessage(false, e.getMessage());
		}
	}

}
