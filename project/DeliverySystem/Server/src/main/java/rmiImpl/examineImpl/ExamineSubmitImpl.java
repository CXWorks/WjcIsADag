package rmiImpl.examineImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import message.OperationMessage;
import po.FormPO;
import rmi.examineService.ExamineSubmitService;

public class ExamineSubmitImpl extends UnicastRemoteObject implements ExamineSubmitService {
	private static final long serialVersionUID = 1L;
	public ExamineSubmitImpl() throws RemoteException{
		super();
	}
	public OperationMessage submit(FormPO form) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

}
