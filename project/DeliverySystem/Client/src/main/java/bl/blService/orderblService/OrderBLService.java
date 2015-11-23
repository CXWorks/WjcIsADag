package bl.blService.orderblService;

import java.util.ArrayList;

import bl.blService.FormBLService;
import message.CheckFormMessage;
import message.OperationMessage;
import po.orderdata.OrderPO;
import vo.ordervo.OrderVO;
import vo.ordervo.PredictVO;

/**
 * 
 * @author mx
 *2015/10/25
 */
public interface OrderBLService extends FormBLService<OrderVO>{
	
	/**
	 * 计算运费和预计到达日期
	 * @param po 新订单中的信息
	 * @return 运费和预计到达日期
	 */
	public PredictVO predict(OrderVO vo);
}
