package blImpl.configurationbl.driver;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.configurationdata.PricePO;
import po.configurationdata.ProportionPO;
import message.OperationMessage;
import rmi.configurationdata.ConfigurationDataService;

/** 
 * Client//blImpl.configurationbl.driver//ConfigurationblImpl_driver.java
 * @author CXWorks
 * @date 2015年10月26日 上午9:18:05
 * @version 1.0 
 */
public class ConfigurationblImpl_driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ConfigurationDataService configurationDataService=(ConfigurationDataService)Naming.lookup("rmi://localhost:2333/configurationDataService");
			System.out.println(configurationDataService.getCityDistance() instanceof ArrayList<?>);
			System.out.println(configurationDataService.modifyCityDistance(null) instanceof OperationMessage);
			System.out.println(configurationDataService.getSalaryStrategy() instanceof ArrayList<?>);
			System.out.println(configurationDataService.modifySalaryStrategy(null) instanceof OperationMessage);
			System.out.println(configurationDataService.getPack() instanceof ArrayList<?>);
			System.out.println(configurationDataService.modifyPack(null) instanceof OperationMessage);
			System.out.println(configurationDataService.getPrice() instanceof PricePO);
			System.out.println(configurationDataService.modifyPrice(null) instanceof OperationMessage);
			System.out.println(configurationDataService.getProportion() instanceof ProportionPO);
			System.out.println(configurationDataService.modifyProportion(null) instanceof OperationMessage);
			System.out.println(configurationDataService.getInstitutionDistance() instanceof Object);
			System.out.println(configurationDataService.newInstitutionDistanceSearch("111")[0] instanceof Object);
			System.out.println(configurationDataService.modifyInstitutionDistance("111", null) instanceof OperationMessage);
			System.out.println(configurationDataService.newInstitutionDistanceInsert("111", null) instanceof OperationMessage);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
