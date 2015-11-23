package rmiImpl.memberdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import message.OperationMessage;
import po.memberdata.DriverPO;
import po.memberdata.StaffPO;
import po.memberdata.StaffTypeEnum;
import po.receivedata.ReceivePO;
import rmi.memberdata.MemberDataService;
import rmiImpl.ConnecterHelper;

public class StaffDataImpl extends UnicastRemoteObject implements
		MemberDataService<StaffPO> {
	private static final long serialVersionUID = 1L;

	private String Table_Name;
	private Connection conn = null;
	private PreparedStatement statement = null;

	public StaffDataImpl() throws RemoteException {
		// TODO Auto-generated constructor stub
		super();
		Table_Name = "staff";
		conn = ConnecterHelper.connSQL(conn);
	}

	public Connection getConn() {
		return conn;
	}

	public ArrayList<StaffPO> getStaff(StaffTypeEnum staffTypeEnum) {
		// TODO Auto-generated method stub
		String selectAll = "select * from " + Table_Name;
		ResultSet rs = null;
		StaffPO temp = null;
		ArrayList<StaffPO> result = new ArrayList<StaffPO>();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				if (!rs.getString("staff").equalsIgnoreCase(
						staffTypeEnum.toString()))
					continue;
				temp = new StaffPO(rs.getString("staff"), rs.getString("ID"),
						rs.getString("name"), rs.getInt("age"),
						rs.getString("personID"), rs.getString("sex"),
						rs.getString("love"));
				result.add(temp);

			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}

		return result;
	}

	public OperationMessage modifyStaff(StaffPO after) {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		if (!this.dismissStaff(after).operationResult)
			return result = new OperationMessage(false, "数据库中没有对应员工");
		if (!this.addStaff(after).operationResult)
			return result = new OperationMessage(false, "更新失败");
		else
			return result;
	}

	public OperationMessage addStaff(StaffPO po) {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String add = "insert into " + Table_Name
				+ "(ID,staff,name,age,personID,sex,love) " + "values('"
				+ po.getID() + "','" + po.getStaff().toString() + "','"
				+ po.getName() + "','" + po.getAge() + "','"
				+ po.getPersonID().toString() + "','" + po.getSex() + "','"
				+ po.getLove() + "')";

		try {
			statement = conn.prepareStatement(add);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = new OperationMessage(false, "新建时出错：");
			System.err.println("新建时出错：");
			e.printStackTrace();
		}

		return result;
	}

	public OperationMessage dismissStaff(StaffPO staff) {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String dismiss = "delete from " + Table_Name + " where ID= '"
				+ staff.getID() + "'";
		try {
			statement = conn.prepareStatement(dismiss);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = new OperationMessage(false, "删除时出错：");
			System.err.println("删除时出错：");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String newStaffID(StaffTypeEnum staffType, String unitID)
			throws RemoteException {
		// TODO Auto-generated method stub
		String selectAll = "select * from " + Table_Name;
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
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				String temp = rs.getString("ID").substring(0, 2);
				if (target.equalsIgnoreCase(temp))
					ID_MAX = Math.max(ID_MAX,
							Integer.parseInt(rs.getString("ID").substring(2)));// 最后6位编号
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
		// TODO Auto-generated method stub
		String select = "select * from " + Table_Name + " where ID= '" + ID
				+ "'";
		ResultSet rs = null;
		StaffPO result = null;
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			result = new StaffPO(rs.getString("staff"), rs.getString("ID"),
					rs.getString("name"), rs.getInt("age"),
					rs.getString("personID"), rs.getString("sex"),
					rs.getString("love"));
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
			return null;
		}
		return result;
	}

}
