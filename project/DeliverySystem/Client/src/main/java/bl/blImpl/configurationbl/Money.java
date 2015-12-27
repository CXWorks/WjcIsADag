package bl.blImpl.configurationbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import bl.clientNetCache.CacheHelper;
import message.OperationMessage;
import po.configurationdata.PackPO;
import po.configurationdata.PricePO;
import po.configurationdata.ProportionPO;
import po.configurationdata.enums.PackEnum;
import po.orderdata.DeliverTypeEnum;
import tool.vopo.VOPOFactory;
import rmi.configurationdata.ConfigurationDataService;
import vo.configurationvo.ConfigurationVO;
import vo.configurationvo.PackVO;
import vo.configurationvo.PriceVO;
import vo.configurationvo.ProportionVO;

/**
 * Client//bl.blImpl.configurationbl//Money.java
 * @author CXWorks
 * @date 2015年11月15日 下午4:41:22
 * @version 1.0
 */
public class Money {

	private VOPOFactory vopoFactory;
	public Money(VOPOFactory vopoFactory){
		this.vopoFactory=vopoFactory;
	}
	//
	public ArrayList<ConfigurationVO> getPrice(){
		ConfigurationDataService configurationDataService=CacheHelper.getConfigurationDataService();
		try {
			PricePO po=configurationDataService.getPrice();
			ArrayList<ConfigurationVO> ans=new ArrayList<ConfigurationVO>(1);
			PriceVO vo=(PriceVO)vopoFactory.transPOtoVO(po);

			ans.add(vo);
			return ans;
		} catch (RemoteException e) {
			return null;
		}
	}
	//
	public ArrayList<ConfigurationVO> getProportion(){
		ConfigurationDataService configurationDataService=CacheHelper.getConfigurationDataService();
		try {
			ProportionPO po=configurationDataService.getProportion();
			ArrayList<ConfigurationVO> ans=new ArrayList<ConfigurationVO>(1);
			ProportionVO vo=(ProportionVO)vopoFactory.transPOtoVO(po);
			ans.add(vo);
			return ans;
		} catch (RemoteException e) {
			return null;
		}
	}
	//
	public ArrayList<ConfigurationVO> getPack(){
		ConfigurationDataService configurationDataService=CacheHelper.getConfigurationDataService();
		try {
			PackPO po=configurationDataService.getPack();
			ArrayList<ConfigurationVO> ans=new ArrayList<ConfigurationVO>(1);
			PackVO vo=(PackVO)vopoFactory.transPOtoVO(po);
			ans.add(vo);
			return ans;
		} catch (RemoteException e) {
			return null;
		}
	}
	/*
	 * modify methods
	 */
	public OperationMessage modifyPrice(PriceVO vo){
		ConfigurationDataService configurationDataService=CacheHelper.getConfigurationDataService();
		PricePO po=(PricePO)vopoFactory.transVOtoPO(vo);
		try {
			return configurationDataService.modifyPrice(po);
		} catch (RemoteException e) {
			return new OperationMessage(false,"net error");
		}
	}
	//
	public OperationMessage modifyProportion(ProportionVO vo){
		ConfigurationDataService configurationDataService=CacheHelper.getConfigurationDataService();
		ProportionPO po=(ProportionPO)vopoFactory.transVOtoPO(vo);
		try {
			return configurationDataService.modifyProportion(po);
		} catch (RemoteException e) {
			return new OperationMessage(false,"net error");
		}
	}
	//
	public OperationMessage modifyPack(PackVO vo){
		ConfigurationDataService configurationDataService=CacheHelper.getConfigurationDataService();
		PackPO po=(PackPO)vopoFactory.transVOtoPO(vo);
		try {
			return configurationDataService.modifyPack(po);
		} catch (RemoteException e) {
			return new OperationMessage(false,"net error");
		}
	}
}
