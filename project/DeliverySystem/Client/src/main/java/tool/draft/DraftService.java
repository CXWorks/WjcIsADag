package tool.draft;

import po.FormEnum;
import vo.FormVO;
import message.OperationMessage;

/** 
 * Client//tool.draft//DraftService.java
 * @author CXWorks
 * @date 2015年11月15日 下午8:10:36
 * @version 1.0 
 */
public interface DraftService {
	public OperationMessage saveDraft(FormVO vo);
	/**
	 * 应该有一个枚举参数来寻找的
	 * @return
	 */
	public FormVO getDraft(FormEnum formEnum);

}
