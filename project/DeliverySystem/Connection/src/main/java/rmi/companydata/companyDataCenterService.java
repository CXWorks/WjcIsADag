package rmi.companydata;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import message.OperationMessage;
import po.companydata.*;
/**
 * 
 * @author cxworks
 *2015/10/24
 */
public interface CompanyDataCenterService extends Remote{
	/**
	 * 
	 * @return
	 */
	public ArrayList<CenterPO> getCenter() throws RemoteException;
	/**
	 * 
	 * @return
	 */
	public String newCenterID() throws RemoteException;
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
