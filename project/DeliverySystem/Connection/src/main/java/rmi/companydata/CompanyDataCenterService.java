package rmi.companydata;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import message.OperationMessage;
import po.companydata.*;
import rmi.DataService;
/**
 * 
 * @author cxworks
 *2015/10/24
 */
public interface CompanyDataCenterService extends DataService<CenterPO>{
	
	/** 接口的名称，RMI绑定时候的名称 */
	public static final String NAME = "CompanyDataCenter";
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<CenterPO> getCenter() throws RemoteException;
	/**
	 * 
	 * @param center
	 * @return
	 */
	public OperationMessage addCenter(CenterPO center) throws RemoteException;
	/**
	 * 
	 * @param center
	 * @return
	 */
	public OperationMessage deleteCenter(CenterPO center) throws RemoteException;
	/**
	 * 
	 * @param center
	 * @return
	 */
	public OperationMessage modifyCenter(CenterPO center) throws RemoteException;
	
}
