package rmiImpl.companydata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;

import message.OperationMessage;
import po.companydata.CenterPO;
import po.memberdata.StaffPO;
import po.memberdata.StaffTypeEnum;
import po.systemdata.LogPO;
import po.transportdata.CenterOutPO;
import rmi.companydata.CompanyDataCenterService;
import rmi.memberdata.MemberDataService;
import database.ConnecterHelper;
import database.MySql;
import database.RMIHelper;
import database.enums.TableEnum;
import rmiImpl.memberdata.DriverDataImpl;
import rmiImpl.memberdata.StaffDataImpl;

public class CompanyDataCenterImpl extends UnicastRemoteObject implements CompanyDataCenterService {
	private static final long serialVersionUID = 1L;

	private Connection conn = null;
	private PreparedStatement statement = null;

	public CompanyDataCenterImpl() throws RemoteException {
		super();
		conn = ConnecterHelper.getConn();
	}

	public Connection getConn() {
		return conn;
	}

	public ArrayList<CenterPO> getCenter() throws RemoteException {
		String select = MySql.select(TableEnum.CENTER, null);
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
		String selectAll = MySql.select(TableEnum.CENTER, new HashMap<String, String>() {
			{
				put("cityID", cityID);
			}
		});
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

		String insert = MySql.insert(TableEnum.CENTER, new HashMap<String, String>() {
			{
				put("centerID", po.getCenterID());
				put("city", po.getCityID());
				put("cityID", po.getCenterID().substring(0, 3));
			}
		});
		String tack = MySql.insert(TableEnum.TACK, new HashMap<String, String>() {
			{
				put("centerID", po.getCenterID());
				put("num", "0");
				put("date", new Timestamp(System.currentTimeMillis()).toString());
			}
		});
		String warningline = MySql.insert(TableEnum.WARNINGLINE, new HashMap<String, String>() {
			{
				put("name", po.getCenterID());
				put("value", "80");
			}
		});
		try {
			statement = conn.prepareStatement(insert);
			statement.executeUpdate();
			statement = conn.prepareStatement(tack);
			statement.executeUpdate();
			statement = conn.prepareStatement(warningline);
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
		String delete = MySql.delete(TableEnum.CENTER, new HashMap<String, String>() {
			{
				put("centerID", center.getCenterID());
			}
		});
		String warningline = MySql.delete(TableEnum.WARNINGLINE, new HashMap<String, String>() {
			{
				put("name", center.getCenterID());
			}
		});
		String tack = MySql.delete(TableEnum.TACK, new HashMap<String, String>() {
			{
				put("centerID", center.getCenterID());
			}
		});
		try {
			statement = conn.prepareStatement(delete);
			statement.executeUpdate();
			statement = conn.prepareStatement(warningline);
			statement.executeUpdate();
			statement = conn.prepareStatement(tack);
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
		String select = MySql.select(TableEnum.CENTER, new HashMap<String, String>() {
			{
				put("centerID", ID);
			}
		});
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
