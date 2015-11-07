package bl.blService.transportblService;

import java.util.ArrayList;

import bl.blService.FormBLService;
import message.CheckFormMessage;
import message.OperationMessage;
import po.transportdata.CenterOutPO;
import vo.transitvo.CenterOutVO;


/**
 * 
 * @author mx
 *2015/10/25
 */
public interface TransportCenterBLService extends FormBLService<CenterOutVO> {


	/**
	 * 检查中转单
	 * @param form 中转单信息
	 * @return 返回检查结果列表
	 */
	public ArrayList<CheckFormMessage> checkFormat(CenterOutPO form);
	
	/**
	 * 提交中转单
	 * @param form 中转单信息
	 * @return 返回操作结果
	 */
	public OperationMessage submit(CenterOutPO form);
	
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
