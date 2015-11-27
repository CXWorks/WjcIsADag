package bl.blImpl.financebl;

import bl.blService.financeblService.RevenueBLService;
import bl.clientNetCache.CacheHelper;
import message.CheckFormMessage;
import message.OperationMessage;
import rmi.examineService.ExamineSubmitService;
import rmi.financedata.RevenueDataService;
import rmi.orderdata.OrderDataService;
import tool.draft.DraftService;
import tool.vopo.VOPOFactory;
import userinfo.UserInfo;
import util.R;
import vo.financevo.RevenueVO;
import vo.ordervo.OrderVO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import po.FormEnum;
import po.financedata.RevenuePO;
import po.orderdata.OrderPO;

/**
 * Created by Sissel on 2015/10/26.
 */
public class RevenueBLImpl implements RevenueBLService {
	private RevenueDataService revenueDataService;
	private VOPOFactory vopoFactory;
	private DraftService draftService;

	public RevenueBLImpl(VOPOFactory vopoFactory,DraftService draftService){
		this.vopoFactory=vopoFactory;
		this.draftService=draftService;
		this.revenueDataService=CacheHelper.getRevenueDataService();
	}
    public OrderVO loadOrder(String orderNumber) {
    	OrderDataService orderDataService=CacheHelper.getOrderDataService();
        try {
			OrderPO orderPO=orderDataService.getFormPO(orderNumber);
			OrderVO vo=(OrderVO)vopoFactory.transPOtoVO(orderPO);
			return vo;
		} catch (RemoteException e) {
			return null;
		}
        
    }

    public String getNewRevenueID(Calendar date) {
        try {
			String ID=revenueDataService.newID(UserInfo.getInstitutionID());
			return ID;
		} catch (RemoteException e) {
			return null;
		}
    }

    public RevenueVO getRevenueVO(String revenueID) {
        try {
			RevenuePO revenuePO=revenueDataService.getFormPO(revenueID);
			RevenueVO vo=(RevenueVO)vopoFactory.transPOtoVO(revenuePO);
			return vo;
		} catch (RemoteException e) {
			return null;
		}
    }

    public List<RevenueVO> getRevenueVOs(Calendar date, String hallID) {
        return new ArrayList<>();
    }

    public List<RevenueVO> getRevenueVOs(Calendar startDate, Calendar endDate) {
        return new LinkedList<RevenueVO>();
    }

    public RevenueVO loadDraft() {
        RevenueVO vo=(RevenueVO)draftService.getDraft(FormEnum.REVENUE);
        return vo;
    }

    public OperationMessage saveDraft(RevenueVO form) {
        return draftService.saveDraft(form);
    }

    public ArrayList<CheckFormMessage> checkFormat(RevenueVO form, boolean isFinal) {
    	ArrayList<CheckFormMessage> result =new ArrayList<CheckFormMessage>();
		CheckFormMessage stub=new CheckFormMessage();
		result.add(stub);
		return result;
    }

    public OperationMessage submit(RevenueVO form) {
    	RevenuePO po=(RevenuePO)vopoFactory.transVOtoPO(form);
        ExamineSubmitService examineSubmitService=CacheHelper.getExamineSubmitService();
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
		return null;
	}

	/* (non-Javadoc)
	 * @see bl.blService.financeblService.RevenueBLService#sum(java.lang.String)
	 */
	public double sum(String revenueID) {
		try {
			RevenuePO po=revenueDataService.getFormPO(revenueID);
			String num=po.getAmount();
			return Double.parseDouble(num);
		} catch (RemoteException e) {
			return 0;
		}
	}
}
