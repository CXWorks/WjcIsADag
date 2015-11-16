package bl.blService.transportblService;

import java.util.ArrayList;

import bl.blService.FormBLService;
import message.CheckFormMessage;
import message.OperationMessage;
import po.transportdata.CenterOutPO;
import vo.ordervo.OrderVO;
import vo.transitvo.CenterOutVO;


/**
 * 
 * @author mx
 *2015/10/25
 */
public interface TransportCenterBLService extends FormBLService<CenterOutVO> {
	/**
	 * 获取对应订单信息
	 * @param orderID
	 * @return
	 */
	public OrderVO getOrder(String orderID);
	
	/**
	 * 保存中转单草稿
	 * @return 返回操作结果
	 */
	public OperationMessage saveDraft();
	
	/**
	 * 载入中转单草稿
	 * @return 中转单信息
	 */
	public CenterOutVO loadDraft();
}
