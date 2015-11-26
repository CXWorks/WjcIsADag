package rmiImpl.examineImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import message.OperationMessage;
import po.FormPO;
import rmi.examineService.ExamineSubmitService;

public class ExamineSubmitImpl extends UnicastRemoteObject implements ExamineSubmitService {
	private static final long serialVersionUID = 1L;
	private ExamineQueue queue;
	public ExamineSubmitImpl(ExamineQueue queue) throws RemoteException{
		super();
		this.queue = queue;
	}
	public OperationMessage submit(FormPO form) {
		// TODO Auto-generated method stub
		this.queue.addForm(form);
		return new OperationMessage();
	}

}
