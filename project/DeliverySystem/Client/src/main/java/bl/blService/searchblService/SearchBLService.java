package bl.blService.searchblService;

import java.util.ArrayList;

import message.CheckFormMessage;
import po.deliverdata.DeliverPO;
import vo.logisticsvo.LogisticsVO;

/**
 * 
 * @author mx
 *2015/10/25
 */
public interface SearchBLService {

	/**
	 * 检查订单号
	 * @param orderID 订单号
	 * @return 返回检查结果
	 */
	public CheckFormMessage checkFormat (String orderID);

	/**
	 * 整理物流信息
	 * @param orderID 订单号
	 * @return 返回整理之后的物流信息
	 */
	public LogisticsVO  integrate (String orderID);

}
