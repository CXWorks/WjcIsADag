package rmi.examineService;

import message.OperationMessage;
import po.FormPO;

public interface ExamineSubmitService {
	
	public OperationMessage submit(FormPO form);
}
