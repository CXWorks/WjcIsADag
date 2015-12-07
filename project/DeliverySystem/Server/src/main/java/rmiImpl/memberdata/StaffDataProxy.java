package rmiImpl.memberdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.util.ArrayList;

import message.OperationMessage;
import po.memberdata.StaffPO;
import po.memberdata.StaffTypeEnum;
import rmi.memberdata.MemberDataService;
import rmiImpl.initaldata.InitialDataProxy;

public class StaffDataProxy extends UnicastRemoteObject implements MemberDataService<StaffPO> {

	MemberDataService<StaffPO> staffDataService = new StaffDataImpl();

	public StaffDataProxy() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Connection getConn() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<StaffPO> getStaffByInstitution(String institutionID) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return staffDataService.getStaffByInstitution(institutionID);
		return null;
	}

	@Override
	public StaffPO getPerson(String ID) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return staffDataService.getPerson(ID);
		return null;
	}

	@Override
	public ArrayList<StaffPO> getStaff(StaffTypeEnum staffTypeEnum) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return staffDataService.getStaff(staffTypeEnum);
		return null;
	}

	@Override
	public OperationMessage modifyStaff(StaffPO after) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return staffDataService.modifyStaff(after);
		return null;
	}

	@Override
	public OperationMessage addStaff(StaffPO staff) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return staffDataService.addStaff(staff);
		return null;
	}

	@Override
	public OperationMessage dismissStaff(StaffPO staff) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return staffDataService.dismissStaff(staff);
		return null;
	}

	@Override
	public String newStaffID(StaffTypeEnum staffType, String unitID) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return staffDataService.newStaffID(staffType, unitID);
		return null;
	}

}
