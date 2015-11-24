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
import java.util.LinkedList;
import java.util.List;

import po.FormEnum;
import po.financedata.PaymentPO;
import rmi.examineService.ExamineSubmitService;
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

    public List<PaymentVO> getPaymentVOs(String startDate, String endDate) {
    	//TODO talk with JC
        return new LinkedList<PaymentVO>();
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
			return examineSubmitService.submit(po);
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
}
