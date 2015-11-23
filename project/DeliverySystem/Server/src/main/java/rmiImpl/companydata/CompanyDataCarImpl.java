package rmiImpl.companydata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import message.OperationMessage;
import po.companydata.CarPO;
import rmi.companydata.CompanyDataCarService;
import rmiImpl.ConnecterHelper;

/**
 * Server//rmiImpl.companydata//CompanyDataCarImpl.java
 * 
 * @author CXWorks
 * @date 2015年10月25日 下午2:56:26
 * @version 1.0
 */
public class CompanyDataCarImpl extends UnicastRemoteObject implements
		CompanyDataCarService {
	private static final long serialVersionUID = 1L;

	private String Table_Name;
	private Connection conn = null;
	private PreparedStatement statement = null;

	public CompanyDataCarImpl() throws RemoteException {
		// TODO Auto-generated constructor stub
		super();
		Table_Name = "car";
		conn = ConnecterHelper.connSQL(conn);
	}

	public Connection getConn() {
		return conn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rmi.companydata.CompanyDataCarService#getCar()
	 */
	public ArrayList<CarPO> getCar() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<CarPO> result = new ArrayList<CarPO>();
		CarPO stub = new CarPO();
		result.add(stub);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rmi.companydata.CompanyDataCarService#newCarID()
	 */
	public String newCarID() {
		// TODO Auto-generated method stub
		return "11111";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * rmi.companydata.CompanyDataCarService#addCar(po.configurationdata.CarPO)
	 */
	public OperationMessage addCar(CarPO car) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * rmi.companydata.CompanyDataCarService#deleteCar(po.configurationdata.
	 * CarPO)
	 */
	public OperationMessage deleteCar(CarPO car) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * rmi.companydata.CompanyDataCarService#modifyCar(po.configurationdata.
	 * CarPO)
	 */
	public OperationMessage modifyCar(CarPO car) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	@Override
	public ArrayList<CarPO> availableCar() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<CarPO> result = new ArrayList<CarPO>();
		CarPO stub = new CarPO();
		result.add(stub);
		return result;
	}

}
