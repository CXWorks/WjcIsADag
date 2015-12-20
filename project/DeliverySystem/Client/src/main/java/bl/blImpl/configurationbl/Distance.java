package bl.blImpl.configurationbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import message.OperationMessage;
import po.configurationdata.City2DPO;
import po.configurationdata.CityDistancePO;
import tool.vopo.VOPOFactory;
import rmi.configurationdata.ConfigurationDataService;
import vo.configurationvo.City2DVO;
import vo.configurationvo.CityDistanceVO;
import vo.configurationvo.ConfigurationVO;

/** 
 * Client//bl.blImpl.configurationbl//Distance.java
 * @author CXWorks
 * @date 2015年11月15日 下午4:41:10
 * @version 1.0 
 */
public class Distance {
	private ConfigurationDataService configurationDataService;
	private VOPOFactory vopoFactory;
	public Distance(ConfigurationDataService configurationDataService,VOPOFactory vopoFactory){
		this.configurationDataService=configurationDataService;
		this.vopoFactory=vopoFactory;
	}
	public OperationMessage newCity(City2DVO city2dvo){
		City2DPO city2dpo=(City2DPO)vopoFactory.transVOtoPO(city2dvo);
		try {
			return configurationDataService.newCity2D(city2dpo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new OperationMessage(false, "net error");
		}
	}
	//
	public OperationMessage deleteCity(City2DVO city2dvo){
		try {
			return configurationDataService.deleteCity2D(city2dvo.getName());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new OperationMessage(false, "net error");
		}
	}
	/*
	 * get methods
	 */
	public ArrayList<City2DVO> getCityDistance(){
		try {
			ArrayList<City2DPO> city2dpos=configurationDataService.getAllCity2D();
			ArrayList<City2DVO> ans=new ArrayList<City2DVO>(city2dpos.size());
			for (int i = 0; i < city2dpos.size(); i++) {
				City2DVO city2dvo=(City2DVO)vopoFactory.transPOtoVO(city2dpos.get(i));
				ans.add(city2dvo);
			}
			
			return ans;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/*
	 * modify methods
	 */
	public OperationMessage modifyCityDistance(City2DVO vo){
		City2DPO city2dpo=(City2DPO)vopoFactory.transVOtoPO(vo);
		try {
			return configurationDataService.modifyCity2D(city2dpo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
