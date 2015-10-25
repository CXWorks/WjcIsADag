package blService.deliverblService;

import java.util.ArrayList;

import message.CheckFormMessage;
import message.OperationMessage;
import po.deliverdata.DeliverPO;

/**
 * 
 * @author wjc
 *2015/10/24
 */

public interface DeliverBLService {
	/**
	 * 检查派件单
	 * @param form 派件信息
	 * @return 返回检查结果列表
	 */
	public ArrayList<CheckFormMessage> checkFormat(DeliverPO form);
	
	/**
	 * 提交派件单
	 * @param form 派件信息
	 * @return 返回操作结果
	 */
	public OperationMessage submit(DeliverPO form);
	
	/**
	 * 保存派件单草稿
	 * @return 返回操作结果
	 */
	public OperationMessage saveDraft();
	
	/**
	 * 载入派件单草稿
	 * @return 派件单信息
	 */
	public DeliverPO loadDraft();
	
	/**
	 * 载入订单信息
	 * @param orderID 订单号
	 * @return 订单信息
	 */
	public OrderVO getOrderVO(String orderID);
}
