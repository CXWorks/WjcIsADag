package rmiImpl.examineImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import message.OperationMessage;
import model.examine.ExamineQueue;
import po.FormPO;
import rmi.examineService.ExamineSubmitService;
import rmiImpl.initaldata.InitialDataProxy;

public class ExamineSubmitProxy extends UnicastRemoteObject implements ExamineSubmitService {

	ExamineSubmitService examineSubmitService;

	public ExamineSubmitProxy(ExamineQueue queue) throws RemoteException {
		super();
		examineSubmitService = new ExamineSubmitImpl(queue);
		// TODO Auto-generated constructor stub
	}

	@Override
	public OperationMessage submit(FormPO form) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return examineSubmitService.submit(form);
		return null;
	}

}
