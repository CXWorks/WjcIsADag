package rmiImpl.memberdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import message.OperationMessage;
import po.memberdata.StaffPO;
import po.memberdata.StaffTypeEnum;
import rmi.memberdata.MemberDataService;

public class MemberDataImpl extends UnicastRemoteObject implements MemberDataService {
	private static final long serialVersionUID = 1L;
	
	private String Table_Name;
	private Connection conn = null;
	private PreparedStatement statement = null;
	
	public MemberDataImpl() throws RemoteException{
		super();
	}
	
	public Connection getConn() {
		return conn;
	}
	
	public ArrayList<StaffPO> getStaff(StaffTypeEnum staffTypeEnum) {
		// TODO Auto-generated method stub
		ArrayList<StaffPO> result =new ArrayList<StaffPO>();
		StaffPO stub=new StaffPO();
		result.add(stub);
		return result;
	}

	public OperationMessage modifyStaff(StaffPO after) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage addStaff(StaffPO staff) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage dismissStaff(StaffPO staff) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}


	public String newStaffID(StaffTypeEnum staffType) {
		// TODO Auto-generated method stub
		return "1111";
	}

}
