package rmi.memberdata;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.ArrayList;

import message.OperationMessage;
import po.memberdata.StaffPO;
import po.memberdata.StaffTypeEnum;
import rmi.DataService;

/**
 * 
 * @author cxworks
 * 2015/10/24
 */
public interface MemberDataService<PO extends StaffPO> extends DataService<StaffPO>{
		
	/** 接口的名称，RMI绑定时候的名称 */
	public static final String NAME = "MemberData";
	
	/**
	 * 获取所有员工信息
	 * @param staffTypeEnum 员工类型
	 * @return 员工信息
	 */
	public PO getPerson(String ID) throws RemoteException;
	/**
	 * 获取所有员工信息
	 * @param staffTypeEnum 员工类型
	 * @return 员工信息
	 */
	public ArrayList<PO> getStaff(StaffTypeEnum staffTypeEnum) throws RemoteException;
	/**
	 * 修改员工信息
	 * @param after 修改后的员工信息
	 * @return 返回操作结果
	 */
	public OperationMessage modifyStaff(PO after) throws RemoteException;
	/**
	 * 新雇佣员工的信息
	 * @param staff 新的员工信息
	 * @return
	 */
	public OperationMessage addStaff(PO staff) throws RemoteException;
	/**
	 * 解雇员工
	 * @param staff
	 * @return
	 */
	public OperationMessage dismissStaff(PO staff) throws RemoteException;
	/**
	 * 获得新的员工ID
	 * @param staffType 工种,unitID 单位编号（无则为null）
	 * @return
	 */
	public String newStaffID(StaffTypeEnum staffType,String unitID) throws RemoteException;
}
