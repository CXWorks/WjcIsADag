package bl.blImpl.configurationbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import bl.NetReconnect.Reconnect;
import bl.clientNetCache.CacheHelper;
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
	private VOPOFactory vopoFactory;	//转换的工厂类
	public Distance(VOPOFactory vopoFactory){

		this.vopoFactory=vopoFactory;
	}
	public OperationMessage newCity(City2DVO city2dvo){
		//获得RMI服务
		ConfigurationDataService configurationDataService=CacheHelper.getConfigurationDataService();
		//获得城市列表并转换
		City2DPO city2dpo=(City2DPO)vopoFactory.transVOtoPO(city2dvo);
		try {
			return configurationDataService.newCity2D(city2dpo);
		} catch (RemoteException e) {
			Reconnect.ReConnectFactory();
			return new OperationMessage(false, "net error");//网络异常。
		}
	}
	//
	public OperationMessage deleteCity(City2DVO city2dvo){
		ConfigurationDataService configurationDataService=CacheHelper.getConfigurationDataService();
		try {
			//直接删除城市
			return configurationDataService.deleteCity2D(city2dvo.getName());
		} catch (RemoteException e) {
			Reconnect.ReConnectFactory();
			return new OperationMessage(false, "net error");
		}
	}
	/*
	 * get methods
	 */
	public ArrayList<City2DVO> getCityDistance(){
		ConfigurationDataService configurationDataService=CacheHelper.getConfigurationDataService();
		try {
			//获得城市信息，并转换成为VO
			ArrayList<City2DPO> city2dpos=configurationDataService.getAllCity2D();
			ArrayList<City2DVO> ans=new ArrayList<City2DVO>(city2dpos.size());
			for (int i = 0; i < city2dpos.size(); i++) {
				City2DVO city2dvo=(City2DVO)vopoFactory.transPOtoVO(city2dpos.get(i));
				ans.add(city2dvo);
			}

			return ans;
		} catch (RemoteException e) {
			Reconnect.ReConnectFactory();
			return null;
		}
	}
	/*
	 * modify methods
	 */
	public OperationMessage modifyCityDistance(City2DVO vo){
		//获得服务
		ConfigurationDataService configurationDataService=CacheHelper.getConfigurationDataService();
		City2DPO city2dpo=(City2DPO)vopoFactory.transVOtoPO(vo);
		try {
			//执行操作
			return configurationDataService.modifyCity2D(city2dpo);
		} catch (RemoteException e) {
			Reconnect.ReConnectFactory();
			return null;
		}
	}
}
