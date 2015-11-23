package rmiImpl.memberdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import message.OperationMessage;
import po.memberdata.DriverPO;
import po.memberdata.StaffPO;
import po.memberdata.StaffTypeEnum;
import po.transportdata.CenterOutPO;
import rmi.memberdata.MemberDataService;
import rmiImpl.ConnecterHelper;

public class DriverDataImpl extends UnicastRemoteObject implements
		MemberDataService<DriverPO> {
	private static final long serialVersionUID = 1L;

	private String Table_Name;
	private Connection conn = null;
	private PreparedStatement statement = null;

	public DriverDataImpl() throws RemoteException {
		// TODO Auto-generated constructor stub
		super();
		Table_Name = "driver";
		conn = ConnecterHelper.connSQL(conn);
	}

	@Override
	public Connection getConn() throws RemoteException {
		// TODO Auto-generated method stub
		return conn;
	}

	@Override
	public ArrayList<DriverPO> getStaff(StaffTypeEnum staffTypeEnum)
			throws RemoteException {
		// TODO Auto-generated method stub
		String selectAll = "select * from " + Table_Name;
		ResultSet rs = null;
		StaffPO temp = null;
		ArrayList<DriverPO> result = new ArrayList<DriverPO>();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				temp = new DriverPO(rs.getTimestamp("birth"),
						rs.getString("tel"), rs.getTimestamp("licence_period"),
						rs.getString("ID"), rs.getString("name"),
						rs.getInt("age"), rs.getString("personID"),
						rs.getString("sex"), rs.getString("love"));
				result.add((DriverPO) temp);

			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public OperationMessage modifyStaff(DriverPO after) throws RemoteException {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String add = "insert into " + Table_Name
				+ "(ID,name,age,personID,sex,love,birth,tel,licence_period) "
				+ "values('" + po.getID() + "','" + po.getName() + "','"
				+ po.getAge() + "','" + po.getPersonID() + "','" + po.getSex()
				+ "','" + po.getLove() + "','" + po.getBirthForSQL() + "','"
				+ po.getTel() + "','" + po.getLicence_periodForSQL() + "')";

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

	@Override
	public OperationMessage dismissStaff(DriverPO po) throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String dismiss = "delete from " + Table_Name + " where ID= '"
				+ po.getID() + "'";
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
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				String temp = rs.getString("ID").substring(0, 7);
				if (unitID.equalsIgnoreCase(temp))
					ID_MAX = Math.max(ID_MAX, Integer.parseInt(rs.getString(
							"ID").substring(7)));// 最后3位编号
			}
		} catch (SQLException e) {
			System.err.println("访问数据库时出错：");
			e.printStackTrace();
		}

		ID_MAX++;// 将该数字加一
		if (ID_MAX > 999)
			return null;
		String added = String.format("%03d", ID_MAX);

		return unitID + added;
	}

	@Override
	public DriverPO getPerson(String ID) throws RemoteException {
		// TODO Auto-generated method stub
		String select = "select * from " + Table_Name + " where ID= '" + ID
				+ "'";
		ResultSet rs = null;
		DriverPO result = null;
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			result = new DriverPO(rs.getTimestamp("birth"),
					rs.getString("tel"), rs.getTimestamp("licence_period"),
					rs.getString("ID"), rs.getString("name"),
					rs.getInt("age"), rs.getString("personID"),
					rs.getString("sex"), rs.getString("love"));
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
			return null;
		}
		return result;
	}

}
