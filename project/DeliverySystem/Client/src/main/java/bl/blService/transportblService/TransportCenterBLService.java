package bl.blService.transportblService;

import java.util.ArrayList;

import bl.blService.FormBLService;
import message.CheckFormMessage;
import message.OperationMessage;
import po.transportdata.CenterOutPO;
import vo.managevo.institution.InstitutionVO;
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
	 * 
	 * @param hallID
	 * @return 当前城市营业厅和全国各地的中转中心编号列表 （还是给我Sring吧，，VO 里也没有InstitutionID啊？
	 */
	
	public ArrayList<String> getLocation(String centerID);
}
