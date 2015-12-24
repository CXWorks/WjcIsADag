package rmiImpl.memberdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import message.OperationMessage;
import po.memberdata.DriverPO;
import po.memberdata.StaffPO;
import po.memberdata.StaffTypeEnum;
import po.receivedata.ReceivePO;
import po.systemdata.LogPO;
import rmi.memberdata.MemberDataService;
import database.ConnecterHelper;
import database.MySql;
import database.RMIHelper;
import database.enums.TableEnum;
import rmiImpl.receivedata.ReceiveDataImpl;

public class StaffDataImpl extends UnicastRemoteObject implements MemberDataService<StaffPO> {
	private static final long serialVersionUID = 1L;

	private Connection conn = null;
	private PreparedStatement statement = null;

	public StaffDataImpl() throws RemoteException {
		super();
		conn = ConnecterHelper.getConn();
	}

	public Connection getConn() {
		return conn;
	}

	public ArrayList<StaffPO> getStaff(StaffTypeEnum staffTypeEnum) {
		String selectAll = MySql.select(TableEnum.STAFF, null);
		ResultSet rs = null;
		StaffPO temp = null;
		ArrayList<StaffPO> result = new ArrayList<StaffPO>();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				if (!rs.getString("staff").equalsIgnoreCase(staffTypeEnum.toString()))
					continue;
				temp = new StaffPO(rs.getString("staff"), rs.getString("ID"), rs.getString("name"), rs.getInt("age"),
						rs.getString("personID"), rs.getString("sex"), rs.getString("love"),
						rs.getString("institutionID"));
				result.add(temp);

			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}

		return result;
	}

	public OperationMessage modifyStaff(StaffPO after) throws RemoteException {
		OperationMessage result = new OperationMessage();
		if (!this.dismissStaff(after).operationResult)
			return result = new OperationMessage(false, "数据库中没有对应员工");
		if (!this.addStaff(after).operationResult)
			return result = new OperationMessage(false, "更新失败");
		else
			return result;
	}

	public OperationMessage addStaff(StaffPO po) throws RemoteException {
		OperationMessage result = new OperationMessage();
		String add = MySql.insert(TableEnum.STAFF, new HashMap<String, String>() {
			{
				put("ID", po.getID());
				put("staff", po.getStaff().toString());
				put("name", po.getName());
				put("age", po.getAge() + "");
				put("personID", po.getPersonID());
				put("sex", po.getSex().toString());
				put("love", po.getLove());
				put("institutionID", po.getInititutionID());
				put("typeID", po.getID().substring(0, 2));
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

	public OperationMessage dismissStaff(StaffPO staff) throws RemoteException {
		OperationMessage result = new OperationMessage();
		String dismiss = MySql.delete(TableEnum.STAFF, new HashMap<String, String>() {
			{
				put("ID", staff.getID());
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
	public String newStaffID(StaffTypeEnum staffType, String unitID) throws RemoteException {
		ResultSet rs = null;
		int ID_MAX = 0;
		String target = "";
		switch (staffType.toString()) {
		case "BURSAR":
			target = "02";
			break;
		case "CENTER_COUNTERMAN":
			target = "04";
			break;
		case "DELIVER":
			target = "06";
			break;
		case "HALL_COUNTERMAN":
			target = "05";
			break;
		case "MANAGER":
			target = "01";
		case "STOREMAN":
			target = "03";
			break;
		default:
			return null;
		}
		final String target_type = target;
		String selectAll = MySql.select(TableEnum.STAFF, new HashMap<String, String>() {
			{
				put("typeID", target_type);
			}
		});
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				ID_MAX = Math.max(ID_MAX, Integer.parseInt(rs.getString("ID").substring(2)));// 最后6位编号
			}
		} catch (SQLException e) {
			System.err.println("访问数据库时出错：");
			e.printStackTrace();
		}

		ID_MAX++;// 将该数字加一
		if (ID_MAX > 999999)
			return null;
		String added = String.format("%06d", ID_MAX);

		return target + added;
	}

	@Override
	public StaffPO getPerson(String ID) throws RemoteException {
		String select = MySql.select(TableEnum.STAFF, new HashMap<String, String>() {
			{
				put("ID", ID);
			}
		});
		ResultSet rs = null;
		StaffPO result = null;
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			result = new StaffPO(rs.getString("staff"), rs.getString("ID"), rs.getString("name"), rs.getInt("age"),
					rs.getString("personID"), rs.getString("sex"), rs.getString("love"), rs.getString("institutionID"));
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
			return null;
		}
		return result;
	}

	@Override
	public ArrayList<StaffPO> getStaffByInstitution(String institutionID) {
		String selectAll = MySql.select(TableEnum.STAFF, new HashMap<String, String>() {
			{
				put("institutionID", institutionID);
			}
		});
		ResultSet rs = null;
		ArrayList<StaffPO> result = new ArrayList<StaffPO>();
		StaffPO temp = null;
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				temp = new StaffPO(rs.getString("staff"), rs.getString("ID"), rs.getString("name"), rs.getInt("age"),
						rs.getString("personID"), rs.getString("sex"), rs.getString("love"), rs.getString("institutionID"));
				result.add(temp);
			}
		} catch (SQLException e) {
			System.err.println("访问数据库时出错：");
			e.printStackTrace();
		}
		return result;
	}

}
