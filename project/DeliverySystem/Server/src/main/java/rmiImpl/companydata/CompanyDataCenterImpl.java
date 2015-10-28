package rmiImpl.companydata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import message.OperationMessage;
import po.companydata.CenterPO;
import rmi.companydata.CompanyDataCenterService;

public class CompanyDataCenterImpl extends UnicastRemoteObject implements CompanyDataCenterService {
	private static final long serialVersionUID = 1L;
	public CompanyDataCenterImpl() throws RemoteException{
		super();
	}
	public ArrayList<CenterPO> getCenter() {
		// TODO Auto-generated method stub
		ArrayList<CenterPO> result=new ArrayList<CenterPO>();
		CenterPO stub=new CenterPO();
		result.add(stub);
		return result;
	}

	public String newCenterID() {
		// TODO Auto-generated method stub
		return "1111111";
	}

	public OperationMessage addCenter(CenterPO center) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage deleteCenter(CenterPO center) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage modifyCenter(CenterPO center) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

}
