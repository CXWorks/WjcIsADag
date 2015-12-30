package bl.blService.orderblService;

import java.util.ArrayList;
import java.util.List;

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
	 * @param vo 新订单中的信息
	 * @return 运费和预计到达日期
	 */
	public PredictVO predict(OrderVO vo);
	/**
	 * 获得当前城市
	 * @return 城市名，不是代号
	 */
	public String localCity();
	/**
	 * 获得所有城市信息
	 * @return 城市名
	 */
	public List<String> getAvaliableCity();
}
