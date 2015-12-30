package rmiImpl.memberdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;

import message.OperationMessage;
import po.memberdata.DriverPO;
import po.memberdata.StaffPO;
import po.memberdata.StaffTypeEnum;
import po.systemdata.LogPO;
import po.transportdata.CenterOutPO;
import rmi.memberdata.MemberDataService;
import database.ConnecterHelper;
import database.MySql;
import database.RMIHelper;
import database.enums.TableEnum;

public class DriverDataImpl extends UnicastRemoteObject implements MemberDataService<DriverPO> {
	private static final long serialVersionUID = 1L;

	private Connection conn = null;
	private PreparedStatement statement = null;

	public DriverDataImpl() throws RemoteException {
		super();
		conn = ConnecterHelper.getConn();
	}

	@Override
	public ArrayList<DriverPO> getStaff(StaffTypeEnum staffTypeEnum) throws RemoteException {
		String selectAll = MySql.select(TableEnum.DRIVER, null);
		ResultSet rs = null;
		DriverPO temp = null;
		ArrayList<DriverPO> result = new ArrayList<DriverPO>();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				temp = new DriverPO(rs.getTimestamp("birth"), rs.getString("tel"), rs.getTimestamp("licence_period"),
						rs.getString("ID"), rs.getString("name"), rs.getInt("age"), rs.getString("personID"),
						rs.getString("sex"), rs.getString("love"), rs.getString("institutionID"));
				result.add(temp);

			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public OperationMessage modifyStaff(DriverPO after) throws RemoteException {
		OperationMessage result = new OperationMessage();
		if (!this.dismissStaff(after).operationResult)
			return result = new OperationMessage(false, "数据库中没有对应司机");
		if (!this.addStaff(after).operationResult)
			return result = new OperationMessage(false, "更新失败");
		else
			return result;
	}

	@Override
	public OperationMessage addStaff(DriverPO po) throws RemoteException {
		OperationMessage result = new OperationMessage();
		String add = MySql.insert(TableEnum.DRIVER, new HashMap<String, String>() {
			{
				put("ID", po.getID());
				put("name", po.getName());
				put("age", po.getAge() + "");
				put("personID", po.getPersonID());
				put("sex", po.getSex().toString());
				put("love", po.getLove());
				put("institutionID", po.getInititutionID());
				put("birth", po.getBirthForSQL().toString());
				put("tel", po.getTel());
				put("licence_period", po.getLicence_periodForSQL().toString());
			}
		});
		try {
			statement = conn.prepareStatement(add);
			statement.executeUpdate();
		} catch (SQLException e) {
			result = new OperationMessage(false, "新建时出错：");
			System.err.println("新建时出错：");
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public OperationMessage dismissStaff(DriverPO po) throws RemoteException {
		OperationMessage result = new OperationMessage();
		String dismiss = MySql.delete(TableEnum.DRIVER, new HashMap<String, String>() {
			{
				put("ID", po.getID());
			}
		});
		try {
			statement = conn.prepareStatement(dismiss);
			statement.executeUpdate();
		} catch (SQLException e) {
			result = new OperationMessage(false, "删除时出错：");
			System.err.println("删除时出错：");
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public String newStaffID(StaffTypeEnum staffType, String institutionID) throws RemoteException {
		String selectAll = MySql.select(TableEnum.DRIVER, new HashMap<String, String>() {
			{
				put("institutionID", institutionID);
			}
		});
		ResultSet rs = null;
		int ID_MAX = 0;
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				ID_MAX = Math.max(ID_MAX, Integer.parseInt(rs.getString("ID").substring(7)));// 最后3位编号
			}
		} catch (SQLException e) {
			System.err.println("访问数据库时出错：");
			e.printStackTrace();
		}

		ID_MAX++;// 将该数字加一
		if (ID_MAX > 999)
			return null;
		String added = String.format("%03d", ID_MAX);

		return institutionID + added;
	}

//	public static void main(String[] args) {
//		MemberDataService<DriverPO> t;
//		try {
//			t = new DriverDataImpl();
//			DriverPO po = t.getPerson("0251001001");
//			System.out.println("hhh:" + po.getLove());
//
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
//	}

	@Override
	public DriverPO getPerson(String ID) throws RemoteException {
		String select = MySql.select(TableEnum.DRIVER, new HashMap<String, String>() {
			{
				put("ID", ID);
			}
		});
		ResultSet rs = null;
		DriverPO result = null;
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			result = new DriverPO(rs.getTimestamp("birth"), rs.getString("tel"), rs.getTimestamp("licence_period"),
					rs.getString("ID"), rs.getString("name"), rs.getInt("age"), rs.getString("personID"),
					rs.getString("sex"), rs.getString("love"), rs.getString("institutionID"));
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
			return null;
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * rmi.memberdata.MemberDataService#getStaffByInstitution(java.lang.String)
	 */
	@Override
	public ArrayList<DriverPO> getStaffByInstitution(String institutionID) {
		String selectAll = MySql.select(TableEnum.DRIVER, new HashMap<String, String>() {
			{
				put("institutionID", institutionID);
			}
		});
		ResultSet rs = null;
		ArrayList<DriverPO> result = new ArrayList<DriverPO>();
		DriverPO temp = null;
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				temp = new DriverPO(rs.getTimestamp("birth"), rs.getString("tel"), rs.getTimestamp("licence_period"),
						rs.getString("ID"), rs.getString("name"), rs.getInt("age"), rs.getString("personID"),
						rs.getString("sex"), rs.getString("love"), rs.getString("institutionID"));
				result.add(temp);
			}
		} catch (SQLException e) {
			System.err.println("访问数据库时出错：");
			e.printStackTrace();
		}
		return result;
	}

}
