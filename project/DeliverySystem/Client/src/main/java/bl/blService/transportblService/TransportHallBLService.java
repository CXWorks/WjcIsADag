package bl.blService.transportblService;

import java.util.ArrayList;

import bl.blService.FormBLService;
import message.CheckFormMessage;
import message.OperationMessage;
import po.transportdata.LoadPO;
import vo.managevo.car.CarVO;
import vo.managevo.staff.StaffVO;
import vo.ordervo.OrderVO;
import vo.transitvo.CenterOutVO;
import vo.transitvo.LoadVO;

/**
 * 
 * @author mx
 *2015/10/25
 */
public interface TransportHallBLService extends FormBLService<LoadVO> {
	/**
	 * 
	 * 根据营业厅信息获取可用的司机列表
	 * @param hallID
	 * @return
	 */
	public ArrayList<StaffVO> getDrivers(String hallID);
	/**
	 * 获取当前可用车辆信息
	 * @param hallID
	 * @return
	 */
	public ArrayList<CarVO> getCars(String hallID);
	
	public ArrayList<Object> getLocation(String hallID);
	
	public OrderVO getOrder(String orderID);

	/**
	 * 检查装车单
	 * @param form 装车单信息
	 * @return 返回检查结果列表
	 */
	public ArrayList<CheckFormMessage> checkFormat(LoadPO form);
	
	/**
	 * 提交装车单
	 * @param form 装车单信息
	 * @return 返回操作结果
	 */
	public OperationMessage submit(LoadPO form);
	
	/**
	 * 保存装车单草稿
	 * @return 返回操作结果
	 */
	public OperationMessage saveDraft();
	
	/**
	 * 载入装车单草稿
	 * @return 装车单信息
	 */
	public LoadVO loadDraft();
}
