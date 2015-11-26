package rmi.examineService;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import message.OperationMessage;
import po.FormPO;

public interface ExamineManageService extends Remote{
	
	/** 接口的名称，RMI绑定时候的名称 */
	public static final String NAME = "ExamineManage";
	
	public ArrayList<FormPO> getForms() throws RemoteException;
	
	public OperationMessage modifyForm(FormPO form) throws RemoteException;
	
	public OperationMessage passForm(ArrayList<FormPO> forms) throws RemoteException;
	
	public OperationMessage deleteForm(ArrayList<FormPO> forms) throws RemoteException;
}
