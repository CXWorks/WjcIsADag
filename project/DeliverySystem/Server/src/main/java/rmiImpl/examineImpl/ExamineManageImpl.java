package rmiImpl.examineImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import message.OperationMessage;
import po.FormPO;
import rmi.examineService.ExamineManageService;

public class ExamineManageImpl extends UnicastRemoteObject implements ExamineManageService {
	private static final long serialVersionUID = 1L;
	public ExamineManageImpl() throws RemoteException{
		super();
	}
	public ArrayList<FormPO> getForms() {
		// TODO Auto-generated method stub
		return null;
	}

	public OperationMessage modifyForm(FormPO form) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage passForm(ArrayList<FormPO> form) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage deleteForm(ArrayList<FormPO> form) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

}
