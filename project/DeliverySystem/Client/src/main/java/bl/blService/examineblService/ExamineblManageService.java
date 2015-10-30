package bl.blService.examineblService;

import java.util.ArrayList;

import message.OperationMessage;
import vo.FormVO;

public interface ExamineblManageService {
	
	public ArrayList<FormVO> getForms();
	
	public OperationMessage passForm(ArrayList<FormVO> form);
	
	public OperationMessage deleteForm(ArrayList<FormVO> form);
	
	public FormVO getForm(FormVO form);
	
	public ArrayList<FormVO> getFormHistory();
}
