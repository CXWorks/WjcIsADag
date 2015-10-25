package rmi.companydata;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import message.OperationMessage;
import po.companydata.CarPO;

/** 
 * Connection//rmi.companydata//CompanyDataCarService.java
 * @author CXWorks
 * @date 2015年10月25日 下午2:53:09
 * @version 1.0 
 */
public interface CompanyDataCarService extends Remote{
	
	public ArrayList<CarPO> getCar() throws RemoteException;
	
	public String newCarID() throws RemoteException;
	
	public OperationMessage addCar(CarPO car) throws RemoteException;
	
	public OperationMessage deleteCar(CarPO car) throws RemoteException;
	
	public OperationMessage modifyCar(CarPO car) throws RemoteException;
}
