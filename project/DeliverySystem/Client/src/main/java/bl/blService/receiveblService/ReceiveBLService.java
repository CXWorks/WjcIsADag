package bl.blService.receiveblService;

import java.util.ArrayList;
import java.util.Calendar;

import bl.blService.FormBLService;
import message.CheckFormMessage;
import message.OperationMessage;
import vo.ordervo.OrderVO;
import vo.receivevo.ReceiveVO;
import vo.transitvo.TransitVO;

/**
 * 
 * @author wjc
 *2015/10/24
 */
public interface ReceiveBLService extends FormBLService<ReceiveVO>{
	
	
	public CheckFormMessage checkOrderID(String orderID,boolean isFinal);
	public CheckFormMessage checkTransitID(String transitID,boolean isFinal);
	public CheckFormMessage checkDate(Calendar date,boolean isFinal);
	
	/**
	 * 提交到达单
	 * @param form 到达单信息
	 * @return 返回操作结果
	 */
	public OperationMessage submit(ReceiveVO form);
	
	/**
	 * 保存到达单草稿
	 * @return 返回操作结果
	 */
	public OperationMessage saveDraft(ReceiveVO form);
	
	/**
	 * 载入到达单草稿
	 * @return 到达单信息
	 */
	public ReceiveVO loadDraft();
	
	/**
	 * 载入订单信息
	 * @param orderID 订单号
	 * @return 订单信息
	 */
	public OrderVO getOrderVO(String orderID);
	
	/**
	 * 载入中转单信息（已经载入订单信息）
	 * @return 中转单信息
	 */
//	public TransitVO getTransitVO();
}
