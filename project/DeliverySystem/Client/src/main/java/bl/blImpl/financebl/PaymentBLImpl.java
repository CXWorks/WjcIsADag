package bl.blImpl.financebl;

import bl.blService.financeblService.PaymentBLService;
import bl.clientNetCache.CacheHelper;
import tool.draft.DraftService;
import tool.vopo.VOPOFactory;
import message.CheckFormMessage;
import message.OperationMessage;
import userinfo.UserInfo;
import vo.financevo.PaymentVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import po.FormEnum;
import po.financedata.PaymentPO;
import rmi.examineService.ExamineSubmitService;
import rmi.financedata.BankAccountDataService;
import rmi.financedata.PaymentDataService;

/**
 * Created by Sissel on 2015/10/26.
 */
public class PaymentBLImpl implements PaymentBLService {
	private PaymentDataService paymentDataService;
	private VOPOFactory vopoFactory;
	private DraftService draftService;
	public PaymentBLImpl(VOPOFactory vopoFactory,DraftService draftService){
		this.draftService=draftService;
		this.vopoFactory=vopoFactory;
		this.paymentDataService=CacheHelper.getPaymentDataService();
	}
    public String getNewPaymentID(String date) {
        try {
			String ID=paymentDataService.newID(UserInfo.getInstitutionID());
			return ID;
		} catch (RemoteException e) {
			return null;
		}
        
    }

    public PaymentVO getPaymentVO(String paymentID) {
        try {
			PaymentPO po=paymentDataService.getFormPO(paymentID);
			PaymentVO vo=(PaymentVO)vopoFactory.transPOtoVO(po);
			return vo;
		} catch (RemoteException e) {
			return null;
		}
    }

    public List<PaymentVO> getPaymentVOs(Calendar startDate, Calendar endDate) {
    	try {
			ArrayList<PaymentPO> po=paymentDataService.getAll();
			ArrayList<PaymentVO> vo=new ArrayList<PaymentVO>();
			for (PaymentPO paymentPO : po) {
				if (this.comp(startDate, endDate, paymentPO.getDate())) {
					PaymentVO paymentVO=(PaymentVO)vopoFactory.transPOtoVO(paymentPO);
					vo.add(paymentVO);
				}
			}
			//
			return vo;
		} catch (RemoteException e) {
			return null;
		}
    }

    public PaymentVO loadDraft() {
       return (PaymentVO)draftService.getDraft(FormEnum.PAYMENT);
    }

    public OperationMessage saveDraft(PaymentVO form) {
        return draftService.saveDraft(form);
    }

    public ArrayList<CheckFormMessage> checkFormat(PaymentVO form, boolean isFinal) {
    	ArrayList<CheckFormMessage> result =new ArrayList<CheckFormMessage>();
		CheckFormMessage stub=new CheckFormMessage();
		result.add(stub);
		return result;
    }

    public OperationMessage submit(PaymentVO form) {
        ExamineSubmitService examineSubmitService=CacheHelper.getExamineSubmitService();
        PaymentPO po=(PaymentPO)vopoFactory.transVOtoPO(form);
        
        try {
        	//complete bank ID
            BankAccountDataService bankAccountDataService=CacheHelper.getBankAccountDataService();
            String payerAccID=bankAccountDataService.getBankIDByName(po.getPayerAccount());
            String receiverAccID=bankAccountDataService.getBankIDByName(po.getReceiverAccount());
            if (payerAccID!=null&&receiverAccID!=null) {
            	po.setPayerAccID(payerAccID);
            	po.setReceiverAccID(receiverAccID);
            	return examineSubmitService.submit(po);
			}else {
				return new OperationMessage(false, "account not exist");
			}
			
		} catch (RemoteException e) {
			return new OperationMessage(false, "net error");
		}
    }

	/* (non-Javadoc)
	 * @see bl.blService.FormBLService#newID()
	 */
	public String newID() {
		// TODO Auto-generated method stub
		return "010011001201511230000001";
	}
	//
	private boolean comp(Calendar strat,Calendar end,Calendar target){
		if(target.after(strat)&&target.before(end))
			return true;
		return false;
	}
}
