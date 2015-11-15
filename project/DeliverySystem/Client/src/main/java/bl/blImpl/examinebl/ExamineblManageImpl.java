package bl.blImpl.examinebl;

import java.util.ArrayList;

import message.OperationMessage;
import vo.FormVO;
import bl.blService.examineblService.ExamineblManageService;

/** 
 * Client//blImpl.examinebl//ExamineBLManageImpl.java
 * @author CXWorks
 * @date 2015年10月26日 上午8:29:04
 * @version 1.0 
 */
public class ExamineBLManageImpl implements ExamineblManageService {

	/* (non-Javadoc)
	 * @see blService.examineblService.ExamineblManageService#getForms()
	 */
	public ArrayList<FormVO> getForms() {
		// TODO Auto-generated method stub
		ArrayList<FormVO> result=new ArrayList<FormVO>();
		FormVO stub=new FormVO();
		result.add(stub);
		System.out.println("used");
		return result;
	}

	/* (non-Javadoc)
	 * @see blService.examineblService.ExamineblManageService#passForm(java.util.ArrayList)
	 */
	public OperationMessage passForm(ArrayList<FormVO> form) {
		// TODO Auto-generated method stub
		System.out.println("used");
		return new OperationMessage();
	}

	/* (non-Javadoc)
	 * @see blService.examineblService.ExamineblManageService#deleteForm(java.util.ArrayList)
	 */
	public OperationMessage deleteForm(ArrayList<FormVO> form) {
		// TODO Auto-generated method stub
		System.out.println("used");
		return new OperationMessage();
	}

	/* (non-Javadoc)
	 * @see blService.examineblService.ExamineblManageService#getForm(vo.FormVO)
	 */
	public FormVO getForm(FormVO form) {
		// TODO Auto-generated method stub
		System.out.println("used");
		return new FormVO();
	}

	/* (non-Javadoc)
	 * @see blService.examineblService.ExamineblManageService#getFormHistory()
	 */
	public ArrayList<FormVO> getFormHistory() {
		// TODO Auto-generated method stub
		ArrayList<FormVO> result=new ArrayList<FormVO>();
		FormVO stub=new FormVO();
		result.add(stub);
		System.out.println("used");
		return result;
	}

}
