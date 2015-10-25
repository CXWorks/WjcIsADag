package rmi.configurationdata;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import message.OperationMessage;
import po.configurationdata.*;
/**
 * 
 * @author cxworks
 *2015/10/25
 */
public interface ConfigurationDataService extends Remote{
	
	public ArrayList<CityDistancePO> getCityDistance() throws RemoteException;
	
	public OperationMessage modifyCityDistance(CityDistancePO after) throws RemoteException;
	
	public ArrayList<SalaryStrategyPO> getSalaryStrategy() throws RemoteException;
	
	public OperationMessage modifySalaryStrategy(SalaryStrategyPO salaryStrategy) throws RemoteException;
	
	public ArrayList<PackPO> getPack() throws RemoteException;
	
	public OperationMessage modifyPack(PackPO pack) throws RemoteException;
	
	public PricePO getPrice() throws RemoteException;
	
	public OperationMessage modifyPrice(PricePO price) throws RemoteException;
	
	public ProportionPO getProportion() throws RemoteException;
	
	public OperationMessage modifyProportion(ProportionPO proportion) throws RemoteException;
	
}
