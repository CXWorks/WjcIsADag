package rmiImpl.companydata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import database.ConnecterHelper;
import database.MySql;
import database.enums.TableEnum;
import message.OperationMessage;
import operation.Operation;
import po.companydata.HallPO;
import rmi.companydata.CompanyDataHallService;

public class CompanyDataHallImpl extends UnicastRemoteObject implements CompanyDataHallService {
	private static final long serialVersionUID = 1L;

	private Connection conn = null;
	private PreparedStatement statement = null;

	public CompanyDataHallImpl() throws RemoteException {
		super();
		conn = ConnecterHelper.getConn();
	}

	public Connection getConn() {
		return conn;
	}

	public ArrayList<HallPO> getHall() throws RemoteException {
		String select = MySql.select(TableEnum.HALL, null);
		ResultSet rs = null;
		HallPO temp = null;
		ArrayList<HallPO> result = new ArrayList<HallPO>();
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			while (rs.next()) {
				temp = new HallPO(rs.getString("hallID"), rs.getString("cityID"), rs.getString("area"),
						rs.getString("nearCenterID"), rs.getString("city"));
				result.add(temp);
			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}
		return result;
	}

	public OperationMessage addHall(HallPO po) throws RemoteException {
		OperationMessage result = new OperationMessage();
		String insert = MySql.insert(TableEnum.HALL, new HashMap<String, String>() {
			{
				put("hallID", po.getHallID());
				put("city", po.getCityName());
				put("area", po.getArea());
				put("nearCenterID", po.getNearCenterID());
				put("cityID", po.getHallID().substring(0, 3));
			}
		});

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
		String delete = MySql.delete(TableEnum.HALL, new HashMap<String, String>() {
			{
				put("hallID", hall.getHallID());
			}
		});
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
		String selectAll = MySql.select(TableEnum.HALL, new HashMap<String, String>() {
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
		String select = MySql.select(TableEnum.HALL, new HashMap<String, String>() {
			{
				put("hallID", ID);
			}
		});
		ResultSet rs = null;
		HallPO result = null;
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			result = new HallPO(rs.getString("hallID"), rs.getString("city"), rs.getString("area"),
					rs.getString("nearCenterID"), rs.getString("cityID"));

		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public long getLatestVersionID() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Operation> getOperation(long localVersion) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
