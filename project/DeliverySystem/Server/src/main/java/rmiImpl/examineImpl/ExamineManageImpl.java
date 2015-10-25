package rmiImpl.examineImpl;

import java.util.ArrayList;

import message.OperationMessage;
import po.FormPO;
import rmi.examineService.ExamineManageService;

public class ExamineManageImpl implements ExamineManageService {

	public ArrayList<FormPO> getForms() {
		// TODO Auto-generated method stub
		ArrayList<FormPO> result =new ArrayList<FormPO>();
		FormPO stub=new FormPO();
		result.add(stub);
		return result;
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
