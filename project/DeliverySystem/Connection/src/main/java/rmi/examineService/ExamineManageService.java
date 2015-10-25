package rmi.examineService;
import java.util.ArrayList;

import message.OperationMessage;
import po.FormPO;

public interface ExamineManageService {
	
	public ArrayList<FormPO> getForms();
	
	public OperationMessage modifyForm(FormPO form);
	
	public OperationMessage passForm(ArrayList<FormPO> form);
	
	public OperationMessage deleteForm(ArrayList<FormPO> form);
}
