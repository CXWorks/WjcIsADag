package bl.blService.examineblService;

import java.util.ArrayList;
import java.util.List;

import po.FormEnum;
import message.OperationMessage;
import vo.FormVO;

public interface ExamineblManageService {
	
	public OperationMessage modifyForm(FormVO form);
	
	public List<FormVO> getForms(FormEnum formType);
	
	public OperationMessage refresh();
	
	public OperationMessage passForm(ArrayList<FormVO> form);
	
	public OperationMessage deleteForm(ArrayList<FormVO> form);
	
	public FormVO getForm(String formID);
}
