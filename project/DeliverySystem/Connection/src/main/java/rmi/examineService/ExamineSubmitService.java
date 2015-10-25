package rmi.examineService;

import java.rmi.Remote;
import java.rmi.RemoteException;

import message.OperationMessage;
import po.FormPO;

public interface ExamineSubmitService extends Remote{
	
	public OperationMessage submit(FormPO form) throws RemoteException;
}
