package rmiImpl.companydata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import message.OperationMessage;
import po.companydata.HallPO;
import rmi.companydata.CompanyDataHallService;

public class CompanyDataHallImpl extends UnicastRemoteObject implements CompanyDataHallService {
	private static final long serialVersionUID = 1L;
	public CompanyDataHallImpl() throws RemoteException{
		super();
	}
	public ArrayList<HallPO> getHall() {
		// TODO Auto-generated method stub
		ArrayList<HallPO> result =new ArrayList<HallPO>();
		HallPO stub=new HallPO();
		result.add(stub);
		return result;
	}

	public OperationMessage addHall(HallPO hall) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage deleteHall(HallPO hall) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage modifyHall(HallPO hall) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}


	public String newHallID() {
		// TODO Auto-generated method stub
		return "111111";
	}

}
