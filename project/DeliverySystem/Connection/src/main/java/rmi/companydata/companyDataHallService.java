package rmi.companydata;
import java.util.ArrayList;

import message.OperationMessage;
import po.companydata.HallPO;
/**
 * 
 * @author cxworks
 *2015/10/24
 */
public interface companyDataHallService {
	/**
	 * 获取营业厅
	 * @return
	 */
	public ArrayList<HallPO> getHall();
	/**
	 * 新建营业厅
	 * @param hall
	 * @return
	 */
	public OperationMessage addHall(HallPO hall);
	/**
	 * 删除营业厅
	 * @param hall
	 * @return
	 */
	public OperationMessage deleteHall(HallPO hall);
	/**
	 * 修改营业厅
	 * @param hall
	 * @return
	 */
	public OperationMessage modifyHall(HallPO hall);
	/**
	 * 查找营业厅
	 * @param hall
	 * @return
	 */
	public HallPO searchHall(HallPO hall);
}
