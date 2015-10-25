package blService.receiveblService;

import java.util.ArrayList;

import message.CheckFormMessage;
import message.OperationMessage;
import po.deliverdata.DeliverPO;
import po.receivedata.ReceivePO;
import vo.ordervo.OrderVO;
import vo.transitvo.TransitVO;

public interface ReceiveBLService {
	/**
	 * 检查到达单
	 * @param form 到达单信息
	 * @return 返回检查结果列表
	 */
	public ArrayList<CheckFormMessage> checkFormat(ReceivePO form);
	
	/**
	 * 提交到达单
	 * @param form 到达单信息
	 * @return 返回操作结果
	 */
	public OperationMessage submit(ReceivePO form);
	
	/**
	 * 保存到达单草稿
	 * @return 返回操作结果
	 */
	public OperationMessage saveDraft();
	
	/**
	 * 载入到达单草稿
	 * @return 到达单信息
	 */
	public ReceivePO loadDraft();
	
	/**
	 * 载入订单信息
	 * @param orderID 订单号
	 * @return 订单信息
	 */
	//public OrderVO getOrderVO(String orderID); TODO
	
	/**
	 * 载入中转单信息（已经载入订单信息）
	 * @return 中转单信息
	 */
	//public TransitVO getTransitVO(); TODO
}
