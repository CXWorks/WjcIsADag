package rmiImpl.configurationdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import message.OperationMessage;
import po.configurationdata.CityDistancePO;
import po.configurationdata.PackPO;
import po.configurationdata.PricePO;
import po.configurationdata.ProportionPO;
import po.configurationdata.SalaryStrategyPO;
import rmi.configurationdata.ConfigurationDataService;
import rmiImpl.ConnecterHelper;
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
	 
	private String Table_Name;
	private Connection conn = null;
	private PreparedStatement statement = null;
	
	public ConfigurationDataImpl() throws RemoteException{
		// TODO Auto-generated constructor stub
		super();
		Table_Name = "configuration";
		conn = ConnecterHelper.connSQL(conn);
	}
	
	public Connection getConn() {
		return conn;
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
		return null;
	}

	public OperationMessage modifySalaryStrategy(SalaryStrategyPO salaryStrategy)  throws RemoteException{
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public PackPO getPack() throws RemoteException {
		// TODO Auto-generated method stub
		
		return new PackPO();
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

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#getInstitutionDistance()
	 */
	public Object getInstitutionDistance() throws RemoteException {
		// TODO Auto-generated method stub
		return new Object();
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#modifyInstitutionDistance(java.lang.String, java.lang.Object)
	 */
	public OperationMessage modifyInstitutionDistance(String ID, Object ob)
			throws RemoteException {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#newInstitutionDistanceSearch(java.lang.String)
	 */
	public Object[] newInstitutionDistanceSearch(String ID)
			throws RemoteException {
		// TODO Auto-generated method stub
		Object[] stub=new Object[2];
		return stub;
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#newInstitutionDistanceInsert(java.lang.String, java.lang.Object[])
	 */
	public OperationMessage newInstitutionDistanceInsert(String ID, Object[] ob)
			throws RemoteException {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

}
