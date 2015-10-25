package rmi.examineService;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import message.OperationMessage;
import po.FormPO;

public interface ExamineManageService extends Remote{
	
	public ArrayList<FormPO> getForms() throws RemoteException;
	
	public OperationMessage modifyForm(FormPO form) throws RemoteException;
	
	public OperationMessage passForm(ArrayList<FormPO> form) throws RemoteException;
	
	public OperationMessage deleteForm(ArrayList<FormPO> form) throws RemoteException;
}
