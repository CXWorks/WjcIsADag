package bl.blImpl.configurationbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.configurationdata.SalaryStrategyPO;
import bl.tool.vopo.VOPOFactory;
import rmi.configurationdata.ConfigurationDataService;
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
	public ArrayList<SalaryStrategyVO> get(){
		try {
			ArrayList<SalaryStrategyPO> po= this.configurationDataService.getSalaryStrategy();
			ArrayList<SalaryStrategyVO> vo=new ArrayList<SalaryStrategyVO>(po.size());
			for (int i = 0; i < po.size(); i++) {
				SalaryStrategyVO temp=new SalaryStrategyVO(po.get(i));
				vo.add(temp);
			}
			//
			return vo;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
}
