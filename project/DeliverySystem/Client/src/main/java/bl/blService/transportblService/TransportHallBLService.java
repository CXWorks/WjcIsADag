package bl.blService.transportblService;

import java.util.ArrayList;

import bl.blService.FormBLService;
import message.CheckFormMessage;
import message.OperationMessage;
import po.transportdata.LoadPO;
import vo.transitvo.CenterOutVO;
import vo.transitvo.LoadVO;

/**
 * 
 * @author mx
 *2015/10/25
 */
public interface TransportHallBLService extends FormBLService<LoadVO> {

//	/**
//	 * 检查装车单
//	 * @param form 装车单信息
//	 * @return 返回检查结果列表
//	 */
//	public ArrayList<CheckFormMessage> checkFormat(LoadPO form);
//	
//	/**
//	 * 提交装车单
//	 * @param form 装车单信息
//	 * @return 返回操作结果
//	 */
//	public OperationMessage submit(LoadPO form);
//	
//	/**
//	 * 保存装车单草稿
//	 * @return 返回操作结果
//	 */
//	public OperationMessage saveDraft();
//	
//	/**
//	 * 载入装车单草稿
//	 * @return 装车单信息
//	 */
//	public LoadVO loadDraft();
}
