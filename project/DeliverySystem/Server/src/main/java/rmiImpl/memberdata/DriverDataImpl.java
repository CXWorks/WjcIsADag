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

import message.OperationMessage;
import po.memberdata.DriverPO;
import po.memberdata.StaffPO;
import po.memberdata.StaffTypeEnum;
import po.systemdata.LogPO;
import po.transportdata.CenterOutPO;
import rmi.memberdata.MemberDataService;
import database.ConnecterHelper;
import database.RMIHelper;

public class DriverDataImpl extends UnicastRemoteObject implements MemberDataService<DriverPO> {
	private static final long serialVersionUID = 1L;

	private String Table_Name;
	private Connection conn = null;
	private PreparedStatement statement = null;

	public DriverDataImpl() throws RemoteException {
		super();
		Table_Name = "driver";
		conn = ConnecterHelper.getConn();
	}

	@Override
	public Connection getConn() throws RemoteException {
		return conn;
	}

	@Override
	public ArrayList<DriverPO> getStaff(StaffTypeEnum staffTypeEnum) throws RemoteException {
		String selectAll = "select * from `" + Table_Name + "`";
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
		String add = "insert into `" + Table_Name
				+ "`(ID,name,age,personID,sex,love,institutionID,birth,tel,licence_period) " + "values('" + po.getID()
				+ "','" + po.getName() + "','" + po.getAge() + "','" + po.getPersonID() + "','" + po.getSex() + "','"
				+ po.getLove() + "','" + po.getInititutionID() + "','" + po.getBirthForSQL() + "','" + po.getTel()
				+ "','" + po.getLicence_periodForSQL() + "')";

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
		String dismiss = "delete from `" + Table_Name + "` where `ID` = '" + po.getID() + "'";
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
		String selectAll = "select * from `" + Table_Name + "` where `institutionID` = '" + institutionID + "'";
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
		String select = "select * from `" + Table_Name + "` where `ID` = '" + ID + "'";
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
		String selectAll = "select * from `" + Table_Name + "` where `institutionID` = '" + institutionID + "'";
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
