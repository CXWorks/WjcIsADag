package bl.blImpl.financebl;

import bl.blService.financeblService.RevenueBLService;
import bl.clientNetCache.CacheHelper;
import message.CheckFormMessage;
import message.OperationMessage;
import rmi.examineService.ExamineSubmitService;
import rmi.financedata.RevenueDataService;
import rmi.memberdata.MemberDataService;
import rmi.orderdata.OrderDataService;
import tool.draft.DraftService;
import tool.time.TimeCompare;
import tool.vopo.VOPOFactory;
import userinfo.UserInfo;
import util.R;
import vo.financevo.RevenueVO;
import vo.managevo.staff.StaffVO;
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
import po.memberdata.StaffPO;
import po.memberdata.StaffTypeEnum;
import po.orderdata.OrderPO;

/**
 * Created by Sissel on 2015/10/26.
 */
public class RevenueBLImpl implements RevenueBLService {
	private VOPOFactory vopoFactory;
	private DraftService draftService;

	public RevenueBLImpl(VOPOFactory vopoFactory,DraftService draftService){
		this.vopoFactory=vopoFactory;
		this.draftService=draftService;
		
	}


    private OrderVO loadOrder(String orderNumber) {
    	OrderDataService orderDataService=CacheHelper.getOrderDataService();
        try {
			OrderPO orderPO=orderDataService.getFormPO(orderNumber);
			OrderVO vo=(OrderVO)vopoFactory.transPOtoVO(orderPO);
			return vo;
		} catch (RemoteException e) {
			return null;
		}
    }

	@Override
	public List<OrderVO> getOrders(RevenueVO revenueVO) {
        List<OrderVO> orderVOs = new LinkedList<>();
        for (String orderID : revenueVO.orderIDs) {
            OrderVO orderVO = loadOrder(orderID);
            if(orderVO != null){
                orderVOs.add(orderVO);
            }
        }
        return orderVOs;
	}

	public String getNewRevenueID(Calendar date) {
		RevenueDataService revenueDataService=CacheHelper.getRevenueDataService();
        try {
			String ID=revenueDataService.newID(UserInfo.getInstitutionID());
			return ID;
		} catch (RemoteException e) {
			return null;
		}
    }

    public RevenueVO getRevenueVO(String revenueID) {
    	RevenueDataService revenueDataService=CacheHelper.getRevenueDataService();
        try {
			RevenuePO revenuePO=revenueDataService.getFormPO(revenueID);
			RevenueVO vo=(RevenueVO)vopoFactory.transPOtoVO(revenuePO);
			return vo;
		} catch (RemoteException e) {
			return null;
		}
    }

    public List<RevenueVO> getRevenueVOs(Calendar date, String hallID) {
    	RevenueDataService revenueDataService=CacheHelper.getRevenueDataService();
    	try {
			ArrayList<RevenuePO> po=revenueDataService.getByHallID(hallID);
			LinkedList<RevenueVO> vo=new LinkedList<RevenueVO>();
			for (RevenuePO revenuePO : po) {
				if (TimeCompare.compareCalendar(revenuePO.getDate(), date)) {
					RevenueVO temp=(RevenueVO)vopoFactory.transPOtoVO(revenuePO);
					vo.add(temp);
				}
			}
			return vo;
		} catch (RemoteException e) {
			return null;
		}
        
    }

    public List<RevenueVO> getRevenueVOs(Calendar startDate, Calendar endDate) {
    	RevenueDataService revenueDataService=CacheHelper.getRevenueDataService();
    	try {
			ArrayList<RevenuePO> po=revenueDataService.getByHallID(UserInfo.getInstitutionID());
			LinkedList<RevenueVO> vo=new LinkedList<RevenueVO>();
			for (RevenuePO revenuePO : po) {
				if (revenuePO.getDate().after(startDate)&&revenuePO.getDate().before(endDate)) {
					RevenueVO temp=(RevenueVO)vopoFactory.transPOtoVO(revenuePO);
					vo.add(temp);
				}
			}
			return vo;
		} catch (RemoteException e) {
			return null;
		}
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
		RevenueDataService revenueDataService=CacheHelper.getRevenueDataService();
		try {
			RevenuePO po=revenueDataService.getFormPO(revenueID);
			String num=po.getAmount();
			return Double.parseDouble(num);
		} catch (RemoteException e) {
			return 0;
		}
	}
	/* (non-Javadoc)
	 * @see bl.blService.financeblService.RevenueBLService#getInstitutionDelivers()
	 */
	@Override
	public ArrayList<StaffVO> getInstitutionDelivers() {
		MemberDataService<StaffPO> memberDataService=CacheHelper.getMemberDataService_staff();
		try {
			
			ArrayList<StaffPO> po=memberDataService.getStaffByInstitution(UserInfo.getInstitutionID());
			ArrayList<StaffVO> vo=new ArrayList<StaffVO>();
			for (StaffPO staffPO : po) {
				if (staffPO.getStaff()==StaffTypeEnum.DELIVER) {
					StaffVO staffVO=(StaffVO)vopoFactory.transPOtoVO(staffPO);
					vo.add(staffVO);
				}
			}
			return vo;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
