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
import java.util.List;

import operation.Operation;
import message.OperationMessage;
import po.companydata.CenterPO;
import po.companydata.HallPO;
import po.memberdata.DriverPO;
import po.memberdata.StaffPO;
import po.systemdata.LogPO;
import rmi.companydata.CompanyDataHallService;
import rmi.memberdata.MemberDataService;
import database.ConnecterHelper;
import database.RMIHelper;
import rmiImpl.memberdata.DriverDataImpl;
import rmiImpl.memberdata.StaffDataImpl;

public class CompanyDataHallImpl extends UnicastRemoteObject implements CompanyDataHallService {
	private static final long serialVersionUID = 1L;

	private String Table_Name;
	private Connection conn = null;
	private PreparedStatement statement = null;

	public CompanyDataHallImpl() throws RemoteException {
		super();
		Table_Name = "hall";
		conn = ConnecterHelper.getConn();
	}

	public Connection getConn() {
		return conn;
	}

	public ArrayList<HallPO> getHall() throws RemoteException {
		String select = "select * from `" + Table_Name + "`";
		ResultSet rs = null;
		HallPO temp = null;
		ArrayList<HallPO> result = new ArrayList<HallPO>();
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			while (rs.next()) {
				temp = new HallPO(rs.getString("hallID"), rs.getString("city"), rs.getString("area"),
						rs.getString("nearCenterID"));
				result.add(temp);
			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}
		return result;
	}

	// public static void main(String[] args) throws RemoteException {
	// CompanyDataHallImpl tCompanyDataHallImpl = new CompanyDataHallImpl();
	// HallPO po = new HallPO(0251001, "南京", "鼓楼", driver2, deliver, counterman,
	// nearCenterID)
	// }

	public OperationMessage addHall(HallPO po) throws RemoteException {
		OperationMessage result = new OperationMessage();
		String insert = "insert into `" + Table_Name + "`(hallID,city,area,nearCenterID,cityID) " + "values('"
				+ po.getHallID() + "','" + po.getCity() + "','" + po.getArea() + "','" + po.getNearCenterID() + "','"
				+ po.getCity().substring(0, 3) + "')";

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

	public OperationMessage deleteHall(HallPO hall) throws RemoteException {
		OperationMessage result = new OperationMessage();
		String delete = "delete from `" + Table_Name + "` where `hallID` = '" + hall.getHallID() + "'";
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

	public OperationMessage modifyHall(HallPO hall) throws RemoteException {
		OperationMessage result = new OperationMessage();
		if (!this.deleteHall(hall).operationResult)
			return result = new OperationMessage(false, "数据库中没有对应营业厅");
		if (!this.addHall(hall).operationResult)
			return result = new OperationMessage(false, "更新失败");
		else
			return result;
	}

	public String newHallID(String cityID) {
		String selectAll = "select * from `" + Table_Name + "` where `cityID` = '" + cityID + "'";
		ResultSet rs = null;
		int ID_MAX = 0;
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				ID_MAX = Math.max(ID_MAX, Integer.parseInt(rs.getString("hallID").substring(4)));// 最后3位编号
			}
		} catch (SQLException e) {
			System.err.println("访问数据库时出错：");
			e.printStackTrace();
		}

		ID_MAX++;// 将该数字加一
		if (ID_MAX > 999)
			return null;
		String added = String.format("%03d", ID_MAX);

		return cityID + "1" + added;
	}

	@Override
	public HallPO getHallByID(String ID) throws RemoteException {
		String select = "select * from `" + Table_Name + "`";
		ResultSet rs = null;
		HallPO result = null;
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			result = new HallPO(rs.getString("hallID"), rs.getString("city"), rs.getString("area"),
					rs.getString("nearCenterID"));

		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see rmi.cachedata.CacheDataService#getLatestVersionID()
	 */
	@Override
	public long getLatestVersionID() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see rmi.cachedata.CacheDataService#getOperation(long)
	 */
	@Override
	public List<Operation> getOperation(long localVersion)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
