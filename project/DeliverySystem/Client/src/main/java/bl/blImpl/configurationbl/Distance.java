package bl.blImpl.configurationbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import message.OperationMessage;
import po.configurationdata.CityDistancePO;
import bl.tool.vopo.VOPOFactory;
import rmi.configurationdata.ConfigurationDataService;
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
			ArrayList<CityDistancePO> po=configurationDataService.getCityDistance();
			ArrayList<ConfigurationVO> vo=new ArrayList<ConfigurationVO>(po.size());
			for(int i=0;i<po.size();i++){
				CityDistancePO each=po.get(i);
				CityDistanceVO temp=(CityDistanceVO)vopoFactory.transPOtoVO(each);
				vo.add(temp);
			}
			return vo;
		} catch (RemoteException e) {
			return null;
		}
	}
	/*
	 * modify methods
	 */
	public OperationMessage modifyCityDistance(CityDistanceVO vo){
		CityDistancePO po=(CityDistancePO)vopoFactory.transVOtoPO(vo);
		try {
			return configurationDataService.modifyCityDistance(po);
		} catch (RemoteException e) {
			return new OperationMessage(false, "net error");
		}
	}
}
