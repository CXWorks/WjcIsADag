package rmi.configurationdata;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import message.OperationMessage;
import po.CommonPO;
import po.configurationdata.*;
import rmi.DataService;

/**
 *
 * @author cxworks 2015/10/25
 */
public interface ConfigurationDataService extends DataService<CommonPO> {

	/** 接口的名称，RMI绑定时候的名称 */
	public static final String NAME = "ConfigurationData";

	// public ArrayList<CityDistancePO> getCityDistance() throws
	// RemoteException;

	// public OperationMessage modifyCityDistance(CityDistancePO after) throws
	// RemoteException;

	public OperationMessage newCity2D(City2DPO po) throws RemoteException;

	public OperationMessage deleteCity2D(String name) throws RemoteException;

	public OperationMessage modifyCity2D(City2DPO po) throws RemoteException;

	public City2DPO getCity2D(String name) throws RemoteException;

	public ArrayList<City2DPO> getAllCity2D() throws RemoteException;

	public OperationMessage clearCity2D() throws RemoteException;

	public ArrayList<SalaryStrategyPO> getSalaryStrategy() throws RemoteException;

	public OperationMessage newSalaryStrategy(List<SalaryStrategyPO> po) throws RemoteException;

	public OperationMessage modifySalaryStrategy(SalaryStrategyPO salaryStrategy) throws RemoteException;

	public PackPO getPack() throws RemoteException;

	public OperationMessage newPack(PackPO po) throws RemoteException;

	public OperationMessage modifyPack(PackPO pack) throws RemoteException;

	public PricePO getPrice() throws RemoteException;

	public OperationMessage newPrice(PricePO po) throws RemoteException;

	public OperationMessage modifyPrice(PricePO price) throws RemoteException;

	public ProportionPO getProportion() throws RemoteException;

	public OperationMessage modifyProportion(ProportionPO proportion) throws RemoteException;

	public OperationMessage newProportion(ProportionPO po) throws RemoteException;

	public Object getInstitutionDistance() throws RemoteException;

	public Object[] newInstitutionDistanceSearch(String ID) throws RemoteException;

	public OperationMessage newInstitutionDistanceInsert(String ID, Object[] ob) throws RemoteException;

	public OperationMessage modifyInstitutionDistance(String ID, Object ob) throws RemoteException;

	public double getWarningline(String centerID) throws RemoteException;

	public OperationMessage setWarningline(String centerID, double value) throws RemoteException;
}
