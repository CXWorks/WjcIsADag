package blService.orderblService;

import java.util.ArrayList;

import message.CheckFormMessage;
import message.OperationMessage;
import po.orderdata.OrderPO;
import po.orderdata.PredictPO;

/**
 * 
 * @author mx
 *2015/10/25
 */
public interface OrderBLService {
	/**
	 * 检查新订单
	 * @param form 新订单信息
	 * @return 返回检查结果列表
	 */
	public ArrayList<CheckFormMessage> checkFormat(OrderPO form);
	
	/**
	 * 提交新订单
	 * @param form 新订单信息
	 * @return 返回操作结果
	 */
	public OperationMessage submit(OrderPO form);
	
	/**
	 * 保存新订单草稿
	 * @return 返回操作结果
	 */
	public OperationMessage saveDraft();
	
	/**
	 * 载入新订单草稿
	 * @return 派件单信息
	 */
	public OrderPO loadDraft();
	
	/**
	 * 计算运费和预计到达日期
	 * @param po 新订单中的信息
	 * @return 运费和预计到达日期
	 */
	public PredictPO predict(OrderPO po);
}
