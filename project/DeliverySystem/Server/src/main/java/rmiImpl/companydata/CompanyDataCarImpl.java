package rmiImpl.companydata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import message.OperationMessage;
import po.companydata.CarPO;
import po.receivedata.ReceivePO;
import po.systemdata.LogPO;
import rmi.companydata.CompanyDataCarService;
import database.ConnecterHelper;
import database.MySql;
import database.RMIHelper;
import database.enums.TableEnum;

/**
 * Server//rmiImpl.companydata//CompanyDataCarImpl.java
 *
 * @author CXWorks
 * @date 2015年10月25日 下午2:56:26
 * @version 1.0
 */
public class CompanyDataCarImpl extends UnicastRemoteObject implements CompanyDataCarService {
	private static final long serialVersionUID = 1L;

	private Connection conn = null;
	private PreparedStatement statement = null;

	public CompanyDataCarImpl() throws RemoteException {
		super();
		conn = ConnecterHelper.getConn();
	}

	public Connection getConn() {
		return conn;
	}

	public ArrayList<CarPO> getCars(String unitID) throws RemoteException {
		String selectAll = MySql.select(TableEnum.CAR, new HashMap<String, String>() {
			{
				put("unitID", unitID);
			}
		});
		ResultSet rs = null;
		CarPO temp = null;
		ArrayList<CarPO> result = new ArrayList<CarPO>();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				temp = new CarPO(rs.getBoolean("free"), rs.getString("carID"), rs.getTimestamp("useTime"), null,
						rs.getString("engineID"), rs.getString("nameID"), rs.getString("chassisID"),
						rs.getTimestamp("buyTime"));
				result.add(temp);
			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}

		return result;
	}

	public String newCarID(String unitID) {
		String selectAll = MySql.select(TableEnum.CAR, new HashMap<String, String>() {
			{
				put("unitID", unitID);
			}
		});
		ResultSet rs = null;
		int ID_MAX = 0;
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				ID_MAX = Math.max(ID_MAX, Integer.parseInt(rs.getString("carID").substring(7)));// 最后3位编号
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

	public OperationMessage addCar(CarPO po) throws RemoteException {
		OperationMessage result = new OperationMessage();
		String insert = MySql.insert(TableEnum.CAR, new HashMap<String, String>() {
			{
				put("carID", po.getCarID());
				put("free", po.isFreeForSql() + "");
				put("useTime", po.getUseTimeForSQL().toString());
				put("nameID", po.getNameID());
				put("chassisID", po.getChassisID());
				put("buyTime", po.getBuyTimeForSQL().toString());
				put("unitID", po.getCarID().substring(0, 7));
				put("engineID", po.getEngineID());
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

	public OperationMessage deleteCar(CarPO car) throws RemoteException {
		OperationMessage result = new OperationMessage();
		String delete = MySql.delete(TableEnum.CAR, new HashMap<String, String>() {
			{
				put("carID", car.getCarID());
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

	public OperationMessage modifyCar(CarPO car) throws RemoteException {
		OperationMessage result = new OperationMessage();
		if (!this.deleteCar(car).operationResult)
			return result = new OperationMessage(false, "数据库中没有对应车辆");
		if (!this.addCar(car).operationResult)
			return result = new OperationMessage(false, "更新失败");
		else
			return result;
	}

	@Override
	public ArrayList<CarPO> availableCar(String unitID) throws RemoteException {
		String selectAll = MySql.select(TableEnum.CAR, new HashMap<String, String>() {
			{
				put("unitID", unitID);
			}
		});
		ResultSet rs = null;
		CarPO temp = null;
		ArrayList<CarPO> result = new ArrayList<CarPO>();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				if (rs.getBoolean("free") == true) {
					temp = new CarPO(true, rs.getString("carID"), rs.getTimestamp("useTime"), null,
							rs.getString("engineID"), rs.getString("nameID"), rs.getString("chassisID"),
							rs.getTimestamp("buyTime"));
					result.add(temp);
				}

			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public CarPO getCar(String carID) throws RemoteException {
		String select = MySql.select(TableEnum.CAR, new HashMap<String, String>() {
			{
				put("carID", carID);
			}
		});
		ResultSet rs = null;
		CarPO result = null;
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			result = new CarPO(rs.getBoolean("free"), rs.getString("carID"), rs.getTimestamp("useTime"), null,
					rs.getString("engineID"), rs.getString("nameID"), rs.getString("chassisID"),
					rs.getTimestamp("buyTime"));
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
			return null;
		}
		return result;
	}

}
