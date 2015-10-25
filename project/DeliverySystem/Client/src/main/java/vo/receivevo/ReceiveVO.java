package vo.receivevo;

import po.receivedata.StateEnum;
import vo.FormVO;

/**
 * 
 * @author wjc
 *2015/10/24
 */

public class ReceiveVO extends FormVO{
	private String orderID;
	private String transitID;
	private String data;
	private String depature;
	private StateEnum state;
}
