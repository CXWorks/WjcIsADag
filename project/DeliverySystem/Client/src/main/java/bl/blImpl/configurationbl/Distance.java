package bl.blImpl.configurationbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import message.OperationMessage;
import po.configurationdata.City2DPO;
import po.configurationdata.CityDistancePO;
import tool.vopo.VOPOFactory;
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
		return null;
	}
	/*
	 * modify methods
	 */
	public OperationMessage modifyCityDistance(CityDistanceVO vo){
		return null;
	}
}
