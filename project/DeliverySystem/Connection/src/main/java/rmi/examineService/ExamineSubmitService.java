package rmi.examineService;

import java.rmi.Remote;
import java.rmi.RemoteException;

import message.OperationMessage;
import po.FormPO;

public interface ExamineSubmitService extends Remote{
	
	/** 接口的名称，RMI绑定时候的名称 */
	public static final String NAME = "ExamineSubmit";
	
	public OperationMessage submit(FormPO form) throws RemoteException;
}
