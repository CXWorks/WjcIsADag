package rmi.memberdata;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import message.OperationMessage;
import po.memberdata.StaffPO;
import po.memberdata.StaffTypeEnum;
/**
 * 
 * @author cxworks
 * 2015/10/24
 */
	public interface memberDataService extends Remote{
	/**
	 * 获取所有员工信息
	 * @param staffTypeEnum 员工类型
	 * @return 员工信息
	 */
	public ArrayList<StaffPO> getStaff(StaffTypeEnum staffTypeEnum) throws RemoteException;
	/**
	 * 修改员工信息
	 * @param after 修改后的员工信息
	 * @return 返回操作结果
	 */
	public OperationMessage modifyStaff(StaffPO after) throws RemoteException;
	/**
	 * 新雇佣员工的信息
	 * @param staff 新的员工信息
	 * @return
	 */
	public OperationMessage addStaff(StaffPO staff) throws RemoteException;
	/**
	 * 解雇员工
	 * @param staff
	 * @return
	 */
	public OperationMessage dismissStaff(StaffPO staff) throws RemoteException;
	/**
	 * 
	 * @return
	 */
	public String newStaffID(StaffTypeEnum staffType) throws RemoteException;
}
