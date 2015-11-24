package bl.blImpl.configurationbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import message.OperationMessage;
import po.configurationdata.PackPO;
import po.configurationdata.PricePO;
import po.configurationdata.ProportionPO;
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
	private ConfigurationDataService configurationDataService;
	private VOPOFactory vopoFactory;
	public Money(ConfigurationDataService configurationDataService,VOPOFactory vopoFactory){
		this.configurationDataService=configurationDataService;
		this.vopoFactory=vopoFactory;
	}
	//
	public ArrayList<ConfigurationVO> getPrice(){
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
		PricePO po=(PricePO)vopoFactory.transVOtoPO(vo);
		try {
			return configurationDataService.modifyPrice(po);
		} catch (RemoteException e) {
			return new OperationMessage(false,"net error");
		}
	}
	//
	public OperationMessage modifyProportion(ProportionVO vo){
		ProportionPO po=(ProportionPO)vopoFactory.transVOtoPO(vo);
		try {
			return configurationDataService.modifyProportion(po);
		} catch (RemoteException e) {
			return new OperationMessage(false,"net error");
		}
	}
	//
	public OperationMessage modifyPack(PackVO vo){
		PackPO po=(PackPO)vopoFactory.transVOtoPO(vo);
		try {
			return configurationDataService.modifyPack(po);
		} catch (RemoteException e) {
			return new OperationMessage(false,"net error");
		}
	}
}
