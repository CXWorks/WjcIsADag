package rmiImpl.configurationdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.util.ArrayList;

import message.OperationMessage;
import po.configurationdata.City2DPO;
import po.configurationdata.PackPO;
import po.configurationdata.PricePO;
import po.configurationdata.ProportionPO;
import po.configurationdata.SalaryStrategyPO;
import po.systemdata.SystemState;
import rmi.configurationdata.ConfigurationDataService;
import rmiImpl.initaldata.InitialDataProxy;

public class ConfigurationDataProxy extends UnicastRemoteObject implements ConfigurationDataService {

	ConfigurationDataService configurationDataService = new ConfigurationDataImpl();
	public ConfigurationDataProxy() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Connection getConn() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperationMessage newCity2D(City2DPO po) throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return configurationDataService.newCity2D(po);
		return null;
	}

	@Override
	public OperationMessage deleteCity2D(String name) throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return configurationDataService.deleteCity2D(name);
		return null;
	}

	@Override
	public OperationMessage modifyCity2D(City2DPO po) throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return configurationDataService.modifyCity2D(po);
		return null;
	}

	@Override
	public City2DPO getCity2D(String name) throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return configurationDataService.getCity2D(name);
		return null;
	}

	@Override
	public ArrayList<City2DPO> getAllCity2D() throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return configurationDataService.getAllCity2D();
		return null;
	}

	@Override
	public OperationMessage clearCity2D() throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return configurationDataService.clearCity2D();
		return null;
	}

	@Override
	public ArrayList<SalaryStrategyPO> getSalaryStrategy() throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return configurationDataService.getSalaryStrategy();
		return null;
	}

	@Override
	public OperationMessage modifySalaryStrategy(SalaryStrategyPO salaryStrategy) throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return configurationDataService.modifySalaryStrategy(salaryStrategy);
		return null;
	}

	@Override
	public PackPO getPack() throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return configurationDataService.getPack();
		return null;
	}

	@Override
	public OperationMessage modifyPack(PackPO pack) throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return configurationDataService.modifyPack(pack);
		return null;
	}

	@Override
	public PricePO getPrice() throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return configurationDataService.getPrice();
		return null;
	}

	@Override
	public OperationMessage modifyPrice(PricePO price) throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return configurationDataService.modifyPrice(price);
		return null;
	}

	@Override
	public ProportionPO getProportion() throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return configurationDataService.getProportion();
		return null;
	}

	@Override
	public OperationMessage modifyProportion(ProportionPO proportion) throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return configurationDataService.modifyProportion(proportion);
		return null;
	}

	@Override
	public Object getInstitutionDistance() throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return configurationDataService.getInstitutionDistance();
		return null;
	}

	@Override
	public Object[] newInstitutionDistanceSearch(String ID) throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return configurationDataService.newInstitutionDistanceSearch(ID);
		return null;
	}

	@Override
	public OperationMessage newInstitutionDistanceInsert(String ID, Object[] ob) throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return configurationDataService.newInstitutionDistanceInsert(ID,ob);
		return null;
	}

	@Override
	public OperationMessage modifyInstitutionDistance(String ID, Object ob) throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return configurationDataService.modifyInstitutionDistance(ID,ob);
		return null;
	}

}
