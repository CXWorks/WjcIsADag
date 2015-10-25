package rmiImpl.configurationdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import message.OperationMessage;
import po.configurationdata.CityDistancePO;
import po.configurationdata.PackPO;
import po.configurationdata.PricePO;
import po.configurationdata.ProportionPO;
import po.configurationdata.SalaryStrategyPO;
import rmi.configurationdata.ConfigurationDataService;
/**
 * 目前全是stub
 * @author cxworks
 *2015/10/25
 */
public class ConfigurationDataImpl extends UnicastRemoteObject implements ConfigurationDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ConfigurationDataImpl() throws RemoteException{
		super();
	}

	public ArrayList<CityDistancePO> getCityDistance()  throws RemoteException{
		// TODO Auto-generated method stub
		ArrayList<CityDistancePO> result=new ArrayList<CityDistancePO>();
		CityDistancePO stub=new CityDistancePO();
		result.add(stub);
		return result;
	}

	public OperationMessage modifyCityDistance(CityDistancePO after)  throws RemoteException{
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public ArrayList<SalaryStrategyPO> getSalaryStrategy()  throws RemoteException{
		// TODO Auto-generated method stub
		ArrayList<SalaryStrategyPO> result=new ArrayList<SalaryStrategyPO>();
		SalaryStrategyPO stub=new SalaryStrategyPO();
		result.add(stub);
		return result;
	}

	public OperationMessage modifySalaryStrategy(SalaryStrategyPO salaryStrategy)  throws RemoteException{
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public ArrayList<PackPO> getPack() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<PackPO> result=new ArrayList<PackPO>();
		PackPO stub=new PackPO();
		result.add(stub);
		return result;
	}

	public OperationMessage modifyPack(PackPO pack) throws RemoteException {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public PricePO getPrice() throws RemoteException {
		// TODO Auto-generated method stub
		
		return new PricePO();
	}

	public OperationMessage modifyPrice(PricePO price) throws RemoteException {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public ProportionPO getProportion() throws RemoteException {
		// TODO Auto-generated method stub
		return new ProportionPO();
	}

	public OperationMessage modifyProportion(ProportionPO proportion) throws RemoteException {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

}
