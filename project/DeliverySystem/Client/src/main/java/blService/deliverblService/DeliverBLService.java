package blService.deliverblService;

import java.util.ArrayList;

import blService.FormBLService;
import message.CheckFormMessage;
import message.OperationMessage;
import vo.delivervo.DeliverVO;
import vo.ordervo.OrderVO;

/**
 * 
 * @author wjc
 *2015/10/24
 */

public interface DeliverBLService extends FormBLService<DeliverVO>{
	/**
	 * 检查派件单
	 * @param form 派件信息
	 * @return 返回检查结果列表
	 */
	public ArrayList<CheckFormMessage> checkFormat(DeliverVO form, boolean isFinal);
	
	/**
	 * 提交派件单
	 * @param form 派件信息
	 * @return 返回操作结果
	 */
	public OperationMessage submit(DeliverVO form);
	
	/**
	 * 保存派件单草稿
	 * @return 返回操作结果
	 */
	public OperationMessage saveDraft(DeliverVO form);
	
	/**
	 * 载入派件单草稿
	 * @return 派件单信息
	 */
	public DeliverVO loadDraft();
	
	/**
	 * 载入订单信息
	 * @param orderID 订单号
	 * @return 订单信息
	 */
	public OrderVO getOrderVO(String orderID);
}
