package rmiImpl.examineImpl;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import message.OperationMessage;
import model.examine.ExamineQueue;
import po.FormPO;
import po.systemdata.SystemState;
import rmi.examineService.ExamineManageService;
import rmiImpl.initaldata.InitialDataProxy;

public class ExamineManageProxy extends UnicastRemoteObject implements ExamineManageService {

	ExamineManageService examineManageService = new ExamineManageImpl();

	public ExamineManageProxy() throws RemoteException, MalformedURLException {
		super();
	}

	@Override
	public ArrayList<FormPO> getForms() throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return examineManageService.getForms();
		return null;
	}

	@Override
	public OperationMessage modifyForm(FormPO form) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return examineManageService.modifyForm(form);
		return null;
	}

	@Override
	public OperationMessage passForm(ArrayList<FormPO> forms) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return examineManageService.passForm(forms);
		return null;
	}

	@Override
	public OperationMessage deleteForm(ArrayList<FormPO> forms) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return examineManageService.deleteForm(forms);
		return null;
	}

	@Override
	public ExamineQueue getQueue() throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return examineManageService.getQueue();
		return null;
	}

}
