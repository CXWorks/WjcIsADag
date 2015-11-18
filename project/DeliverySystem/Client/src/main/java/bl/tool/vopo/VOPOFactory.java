package bl.tool.vopo;

import po.FormPO;
import util.DataType;
import vo.CommonVO;
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

	public static CommonVO transPOtoVO(CommonPO po) {
		// TODO Auto-generated method stub
		if (po.dataType==DataType.DATA) {
			InfoPO info=(InfoPO)po;
			switch (info.getInfoEnum()) {
			case ACCOUNT:
				
				break;

			default:
				break;
			}
			return null;
		} else {
			return null;
		}
		
	}

	public static CommonPO transVOtoPO(CommonVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

}
