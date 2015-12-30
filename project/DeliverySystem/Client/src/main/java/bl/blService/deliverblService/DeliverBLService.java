package bl.blService.deliverblService;

import java.util.ArrayList;

import bl.blService.FormBLService;
import message.CheckFormMessage;
import message.OperationMessage;
import vo.delivervo.DeliverVO;
import vo.managevo.staff.StaffVO;
import vo.ordervo.OrderVO;

/**
 * 
 * @author wjc
 *2015/10/24
 */

public interface DeliverBLService extends FormBLService<DeliverVO>{
	/**
	 * 载入订单信息
	 * @param orderID 订单号
	 * @return 订单信息
	 */
	public OrderVO getOrderVO(String orderID);
	/**
	 * 获得营业厅的未派送订单
	 * @param hallID
	 * @return
	 */
	public ArrayList<String> getUnhandledOrderID(String hallID);
	
	/**
	 * 获得营业厅的快递员
	 */
	public ArrayList<String> getPostman(String hallID);
	
	
}
