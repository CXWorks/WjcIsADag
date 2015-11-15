package bl.tool.vopo;

import po.FormPO;
import vo.FormVO;

/** 
 * Client//bl.tool.vopo//VOPOTransfer.java
 * @author CXWorks
 * @date 2015年11月15日 下午8:08:21
 * @version 1.0 
 */
public interface VOPOTransferService {
	public FormVO transPOtoVO(FormPO po);
	
	public FormPO transVOtoPO(FormVO vo);

}
