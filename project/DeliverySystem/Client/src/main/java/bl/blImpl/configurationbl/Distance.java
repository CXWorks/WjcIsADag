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
	/*
	 * get methods
	 */
	public ArrayList<ConfigurationVO> getCityDistance(){
		try {
			ArrayList<City2DPO> city2dpos=configurationDataService.getAllCity2D();
			System.out.println(city2dpos.get(0).getX());
			ArrayList<ConfigurationVO> ans=new ArrayList<ConfigurationVO>(city2dpos.size());
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
