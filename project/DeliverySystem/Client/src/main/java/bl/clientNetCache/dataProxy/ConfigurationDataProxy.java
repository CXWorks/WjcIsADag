package bl.clientNetCache.dataProxy;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import message.OperationMessage;
import operation.Operation;
import po.configurationdata.City2DPO;
import po.configurationdata.PackPO;
import po.configurationdata.PricePO;
import po.configurationdata.ProportionPO;
import po.configurationdata.SalaryStrategyPO;
import rmi.configurationdata.ConfigurationDataService;

/** 
 * Client//bl.clientNetCache.dataProxy//ConfigurationDataProxy.java
 * @author CXWorks
 * @date 2015年12月17日 下午11:36:39
 * @version 1.0 
 */
public class ConfigurationDataProxy implements ConfigurationDataService {

	/* (non-Javadoc)
	 * @see rmi.DataService#getConn()
	 */
	@Override
	public Connection getConn() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see rmi.cachedata.CacheDataService#getLatestVersionID()
	 */
	@Override
	public long getLatestVersionID() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see rmi.cachedata.CacheDataService#getOperation(long)
	 */
	@Override
	public List<Operation> getOperation(long localVersion)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#newCity2D(po.configurationdata.City2DPO)
	 */
	@Override
	public OperationMessage newCity2D(City2DPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#deleteCity2D(java.lang.String)
	 */
	@Override
	public OperationMessage deleteCity2D(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#modifyCity2D(po.configurationdata.City2DPO)
	 */
	@Override
	public OperationMessage modifyCity2D(City2DPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#getCity2D(java.lang.String)
	 */
	@Override
	public City2DPO getCity2D(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#getAllCity2D()
	 */
	@Override
	public ArrayList<City2DPO> getAllCity2D() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#clearCity2D()
	 */
	@Override
	public OperationMessage clearCity2D() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#getSalaryStrategy()
	 */
	@Override
	public ArrayList<SalaryStrategyPO> getSalaryStrategy()
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#newSalaryStrategy(java.util.List)
	 */
	@Override
	public OperationMessage newSalaryStrategy(List<SalaryStrategyPO> po)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#modifySalaryStrategy(po.configurationdata.SalaryStrategyPO)
	 */
	@Override
	public OperationMessage modifySalaryStrategy(SalaryStrategyPO salaryStrategy)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#getPack()
	 */
	@Override
	public PackPO getPack() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#newPack(po.configurationdata.PackPO)
	 */
	@Override
	public OperationMessage newPack(PackPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#modifyPack(po.configurationdata.PackPO)
	 */
	@Override
	public OperationMessage modifyPack(PackPO pack) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#getPrice()
	 */
	@Override
	public PricePO getPrice() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#newPrice(po.configurationdata.PricePO)
	 */
	@Override
	public OperationMessage newPrice(PricePO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#modifyPrice(po.configurationdata.PricePO)
	 */
	@Override
	public OperationMessage modifyPrice(PricePO price) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#getProportion()
	 */
	@Override
	public ProportionPO getProportion() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#modifyProportion(po.configurationdata.ProportionPO)
	 */
	@Override
	public OperationMessage modifyProportion(ProportionPO proportion)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#newProportion(po.configurationdata.ProportionPO)
	 */
	@Override
	public OperationMessage newProportion(ProportionPO po)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#getWarningline(java.lang.String)
	 */
	@Override
	public double getWarningline(String centerID) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#setWarningline(java.lang.String, double)
	 */
	@Override
	public OperationMessage setWarningline(String centerID, double value)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
