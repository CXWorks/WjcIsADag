package bl.tool.vopo;

import po.FormPO;
import vo.FormVO;
import vo.delivervo.DeliverVO;
import vo.ordervo.OrderVO;
import po.*;
import po.orderdata.OrderPO;
/** 
 * Client//bl.tool.vopo//VOPOFactory.java
 * @author CXWorks
 * @date 2015年11月18日 下午2:19:31
 * @version 1.0 
 */
public class VOPOFactory {

	public static FormVO transPOtoVO(FormPO po) {
		// TODO Auto-generated method stub
		switch (po.getFormType()) {
		case ORDER:
			return new OrderVO((OrderPO)po);
		case DELIVER:
			return new DeliverVO();

		default:
			return null;
		}
		
	}

	public static FormPO transVOtoPO(FormVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

}
