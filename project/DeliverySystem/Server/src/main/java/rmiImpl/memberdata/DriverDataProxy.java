package rmiImpl.memberdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.util.ArrayList;

import message.OperationMessage;
import po.memberdata.DriverPO;
import po.memberdata.StaffTypeEnum;
import po.systemdata.SystemState;
import rmi.memberdata.MemberDataService;
import rmiImpl.initaldata.InitialDataProxy;

public class DriverDataProxy extends UnicastRemoteObject implements MemberDataService<DriverPO> {

	MemberDataService<DriverPO> driverDataService = new DriverDataImpl();

	public DriverDataProxy() throws RemoteException {
		super();
	}

	@Override
	public Connection getConn() throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<DriverPO> getStaffByInstitution(String institutionID) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return driverDataService.getStaffByInstitution(institutionID);
		return null;
	}

	@Override
	public DriverPO getPerson(String ID) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return driverDataService.getPerson(ID);
		return null;
	}

	@Override
	public ArrayList<DriverPO> getStaff(StaffTypeEnum staffTypeEnum) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return driverDataService.getStaff(staffTypeEnum);
		return null;
	}

	@Override
	public OperationMessage modifyStaff(DriverPO after) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return driverDataService.modifyStaff(after);
		return null;
	}

	@Override
	public OperationMessage addStaff(DriverPO staff) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return driverDataService.addStaff(staff);
		return null;
	}

	@Override
	public OperationMessage dismissStaff(DriverPO staff) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return driverDataService.dismissStaff(staff);
		return null;
	}

	@Override
	public String newStaffID(StaffTypeEnum staffType, String unitID) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return driverDataService.newStaffID(staffType, unitID);
		return null;
	}

}
