package bl.blImpl.orderbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import message.CheckFormMessage;
import message.OperationMessage;
import po.FormEnum;
import po.FormPO;
import po.configurationdata.City2DPO;
import po.orderdata.OrderPO;
import rmi.configurationdata.ConfigurationDataService;
import rmi.examineService.ExamineSubmitService;
import rmi.orderdata.OrderDataService;
import userinfo.UserInfo;
import vo.ordervo.OrderVO;
import vo.ordervo.PredictVO;
import bl.NetReconnect.Reconnect;
import bl.blService.FormatCheckService.FormatCheckService;
import bl.blService.configurationblService.ConfigurationBLService;
import bl.blService.orderblService.OrderBLService;
import bl.clientNetCache.CacheHelper;
import tool.draft.DraftService;
import tool.vopo.VOPOFactory;

public class OrderBLController implements OrderBLService{
	private DraftService draftService;
	private VOPOFactory vopoFactory;
	private Predicter predicter;
	private String localCity;
	//
	public OrderBLController(VOPOFactory vopoFactory,DraftService draftService){
		this.vopoFactory=vopoFactory;
		this.draftService=draftService;
		this.predicter=new Predicter(vopoFactory);
	}
	public OrderVO loadDraft() {
		return (OrderVO)draftService.getDraft(FormEnum.ORDER);
	}

	public OperationMessage saveDraft(OrderVO form) {
		return draftService.saveDraft(form);
	}

	public ArrayList<CheckFormMessage> checkFormat(OrderVO form, boolean isFinal) {
		ArrayList<CheckFormMessage> result=new ArrayList<CheckFormMessage>();
		CheckFormMessage stub=new CheckFormMessage();
		result.add(stub);
		return result;
	}

	public OperationMessage submit(OrderVO form) {
		try {
			OrderPO ready=(OrderPO)vopoFactory.transVOtoPO(form);
			ExamineSubmitService examineSubmitService=CacheHelper.getExamineSubmitService();
			return examineSubmitService.submit(ready);
//			OrderDataService orderDataService=CacheHelper.getOrderDataService();
//			return orderDataService.insert(ready);
		} catch (Exception e) {
			Reconnect.ReConnectFactory();
			return new OperationMessage(false,"");
		}
	}

	public PredictVO predict(OrderVO vo) {
		return predicter.calculatePredict(vo);
	}

	/* (non-Javadoc)
	 * @see bl.blService.FormBLService#newID()
	 */
	public String newID() {
		try {
			OrderDataService orderDataService=CacheHelper.getOrderDataService();
			String id=orderDataService.newID(UserInfo.getInstitutionID());
			return id;
		} catch (RemoteException e) {
			Reconnect.ReConnectFactory();
			return null;
		}
		
	}
	/* (non-Javadoc)
	 * @see bl.blService.orderblService.OrderBLService#localCity()
	 */
	@Override
	public String localCity() {
		if (localCity==null) {
			ConfigurationDataService configurationDataService=CacheHelper.getConfigurationDataService();
			String id=UserInfo.getInstitutionID().substring(0, 3);
			try {
				ArrayList<City2DPO> city2dpos=configurationDataService.getAllCity2D();
				localCity=city2dpos.stream()
						.filter(city->city.getID().equalsIgnoreCase(id)).findFirst().get().getName();
			} catch (RemoteException e) {
				Reconnect.ReConnectFactory();
			} catch (Exception e){
				return null;
			}
		}
		return localCity;
	}
	/* (non-Javadoc)
	 * @see bl.blService.orderblService.OrderBLService#getAvaliableCity()
	 */
	@Override
	public List<String> getAvaliableCity() {
		ConfigurationDataService configurationDataService=CacheHelper.getConfigurationDataService();
		try {
			ArrayList<City2DPO> city2dpos=configurationDataService.getAllCity2D();
			return city2dpos.stream().map(city->city.getName()).collect(Collectors.toList());
		} catch (RemoteException e) {
			Reconnect.ReConnectFactory();
			return null;
		}
	}
	/* (non-Javadoc)
	 * @see bl.blService.FormBLService#getHistory(java.lang.String)
	 */
	@Override
	public List<OrderVO> getHistory(String creatorID) {
		OrderDataService orderDataService=CacheHelper.getOrderDataService();
		try {
			List<OrderPO> orderPOs=orderDataService.getHistory(creatorID);
			List<OrderVO> ans=(List<OrderVO>) vopoFactory.transPOtoVO(orderPOs);
			return ans;
		} catch (RemoteException e) {
			Reconnect.ReConnectFactory();
			return null;
		}
	}


}
