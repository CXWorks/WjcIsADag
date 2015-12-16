package rmiImpl.examineImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import message.OperationMessage;
import model.examine.ExamineQueue;
import po.FormPO;
import po.systemdata.SystemState;
import rmi.examineService.ExamineSubmitService;
import rmiImpl.initaldata.InitialDataProxy;

public class ExamineSubmitProxy extends UnicastRemoteObject implements ExamineSubmitService {

	ExamineSubmitService examineSubmitService;

	public ExamineSubmitProxy(ExamineQueue queue) throws RemoteException {
		super();
		examineSubmitService = new ExamineSubmitImpl(queue);
	}

	@Override
	public OperationMessage submit(FormPO form) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return examineSubmitService.submit(form);
		return null;
	}

}
