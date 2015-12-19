package rmiImpl.companydata;

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
import po.companydata.CenterPO;
import po.memberdata.StaffPO;
import po.memberdata.StaffTypeEnum;
import po.systemdata.LogPO;
import po.transportdata.CenterOutPO;
import rmi.companydata.CompanyDataCenterService;
import rmi.memberdata.MemberDataService;
import database.ConnecterHelper;
import database.RMIHelper;
import rmiImpl.memberdata.DriverDataImpl;
import rmiImpl.memberdata.StaffDataImpl;

public class CompanyDataCenterImpl extends UnicastRemoteObject implements CompanyDataCenterService {
	private static final long serialVersionUID = 1L;

	private String Table_Name;
	private Connection conn = null;
	private PreparedStatement statement = null;

	public CompanyDataCenterImpl() throws RemoteException {
		super();
		Table_Name = "center";
		conn = ConnecterHelper.getConn();
	}

	public Connection getConn() {
		return conn;
	}

	public ArrayList<CenterPO> getCenter() throws RemoteException {
		String select = "select * from `" + Table_Name + "`";
		ResultSet rs = null;
		CenterPO temp = null;
		ArrayList<CenterPO> result = new ArrayList<CenterPO>();
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			while (rs.next()) {
				temp = new CenterPO(rs.getString("centerID"), rs.getString("city"));
				result.add(temp);
			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}
		return result;
	}

	public String newCenterID(String cityID) {
		String selectAll = "select * from `" + Table_Name + "` where `cityID` = '" + cityID + "'";
		ResultSet rs = null;
		int ID_MAX = 0;
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				ID_MAX = Math.max(ID_MAX, Integer.parseInt(rs.getString("centerID").substring(4)));// 最后3位编号
			}
		} catch (SQLException e) {
			System.err.println("访问数据库时出错：");
			e.printStackTrace();
		}

		ID_MAX++;// 将该数字加一
		if (ID_MAX > 999)
			return null;
		String added = String.format("%03d", ID_MAX);

		return cityID + "0" + added;
	}

	public OperationMessage addCenter(CenterPO po) throws RemoteException {
		OperationMessage result = new OperationMessage();
		String s = "";
		String c = "";

		String insert = "insert into `" + Table_Name + "`(centerID,city,cityID) " + "values('" + po.getCenterID()
				+ "','" + po.getCity() + "','" + po.getCenterID().substring(0, 3) + "')";

		try {
			statement = conn.prepareStatement(insert);
			statement.executeUpdate();
		} catch (SQLException e) {
			result = new OperationMessage(false, "新建时出错：");
			System.err.println("新建时出错：");
			e.printStackTrace();
		}

		return result;
	}

	public OperationMessage deleteCenter(CenterPO center) throws RemoteException {
		OperationMessage result = new OperationMessage();
		String delete = "delete from `" + Table_Name + "` where `centerID` = '" + center.getCenterID() + "'";
		try {
			statement = conn.prepareStatement(delete);
			statement.executeUpdate();
		} catch (SQLException e) {
			result = new OperationMessage(false, "删除时出错：");
			System.err.println("删除时出错：");
			e.printStackTrace();
		}

		return result;
	}

	public OperationMessage modifyCenter(CenterPO center) throws RemoteException {
		OperationMessage result = new OperationMessage();
		if (!this.deleteCenter(center).operationResult)
			return result = new OperationMessage(false, "数据库中没有对应中转中心");
		if (!this.addCenter(center).operationResult)
			return result = new OperationMessage(false, "更新失败");
		else
			return result;
	}

	@Override
	public CenterPO getCenterByID(String ID) throws RemoteException {
		String select = "select * from `" + Table_Name + "` where `centerID` = '" + ID + "'";
		ResultSet rs = null;
		CenterPO result = null;
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			result = new CenterPO(ID, rs.getString("city"));
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}
		return result;
	}

}
