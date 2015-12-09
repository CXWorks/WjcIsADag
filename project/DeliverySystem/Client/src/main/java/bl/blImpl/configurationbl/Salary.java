package bl.blImpl.configurationbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import message.OperationMessage;
import po.configurationdata.SalaryStrategyPO;
import tool.vopo.VOPOFactory;
import rmi.configurationdata.ConfigurationDataService;
import vo.configurationvo.ConfigurationVO;
import vo.configurationvo.SalaryStrategyVO;

/** 
 * Client//bl.blImpl.configurationbl//Salary.java
 * @author CXWorks
 * @date 2015年11月15日 下午4:41:35
 * @version 1.0 
 */
public class Salary {
	private ConfigurationDataService configurationDataService;
	private VOPOFactory vopoFactory;
	public Salary(ConfigurationDataService configurationDataService,VOPOFactory vopoFactory){
		this.configurationDataService=configurationDataService;
		this.vopoFactory=vopoFactory;
	}
	public ArrayList<ConfigurationVO> getSalary(){
		try {
			ArrayList<SalaryStrategyPO> po= this.configurationDataService.getSalaryStrategy();
			ArrayList<ConfigurationVO> vo=new ArrayList<ConfigurationVO>(po.size());
			
			for (int i = 0; i < po.size(); i++) {
				SalaryStrategyPO each=po.get(i);
				SalaryStrategyVO temp=(SalaryStrategyVO)vopoFactory.transPOtoVO(each);
				vo.add(temp);
			}
			//
			return vo;
		} catch (RemoteException e) {
			return null;
		}
	}
	//
	public OperationMessage modify(SalaryStrategyVO vo){
		SalaryStrategyPO po=(SalaryStrategyPO)vopoFactory.transVOtoPO(vo);
		try {
			System.out.println(po.getBase());
			return configurationDataService.modifySalaryStrategy(po);
		} catch (RemoteException e) {
			return new OperationMessage(false, "net error");
		}
	}
}
