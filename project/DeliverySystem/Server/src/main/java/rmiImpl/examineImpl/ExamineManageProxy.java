package rmiImpl.examineImpl;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import message.OperationMessage;
import model.examine.ExamineQueue;
import po.FormPO;
import rmi.examineService.ExamineManageService;
import rmiImpl.initaldata.InitialDataProxy;

public class ExamineManageProxy extends UnicastRemoteObject implements ExamineManageService {

	ExamineManageService examineManageService = new ExamineManageImpl();

	public ExamineManageProxy() throws RemoteException, MalformedURLException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<FormPO> getForms() throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return examineManageService.getForms();
		return null;
	}

	@Override
	public OperationMessage modifyForm(FormPO form) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return examineManageService.modifyForm(form);
		return null;
	}

	@Override
	public OperationMessage passForm(ArrayList<FormPO> forms) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return examineManageService.passForm(forms);
		return null;
	}

	@Override
	public OperationMessage deleteForm(ArrayList<FormPO> forms) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return examineManageService.deleteForm(forms);
		return null;
	}

	@Override
	public ExamineQueue getQueue() throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return examineManageService.getQueue();
		return null;
	}

}
