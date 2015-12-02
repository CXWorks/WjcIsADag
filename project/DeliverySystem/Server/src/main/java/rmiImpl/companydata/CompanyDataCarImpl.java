package rmiImpl.companydata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import message.OperationMessage;
import po.companydata.CarPO;
import po.receivedata.ReceivePO;
import rmi.companydata.CompanyDataCarService;
import rmiImpl.ConnecterHelper;

/**
 * Server//rmiImpl.companydata//CompanyDataCarImpl.java
 *
 * @author CXWorks
 * @date 2015年10月25日 下午2:56:26
 * @version 1.0
 */
public class CompanyDataCarImpl extends UnicastRemoteObject implements CompanyDataCarService {
	private static final long serialVersionUID = 1L;

	private String Table_Name;
	private Connection conn = null;
	private PreparedStatement statement = null;

	public CompanyDataCarImpl() throws RemoteException {
		// TODO Auto-generated constructor stub
		super();
		Table_Name = "car";
		conn = ConnecterHelper.connSQL(conn);
	}

	public Connection getConn() {
		return conn;
	}

	public ArrayList<CarPO> getCars(String unitID) throws RemoteException {
		// TODO Auto-generated method stub
		String selectAll = "select * from `" + Table_Name + "` where `unitID` = '" + unitID + "'";
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
		// TODO Auto-generated method stub
		String selectAll = "select * from `" + Table_Name + "` where `unitID` = '" + unitID + "'";
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

	public OperationMessage addCar(CarPO po) {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String insert = "insert into `" + Table_Name + "`(carID,free,useTime,nameID,chassisID,buyTime,unitID) "
				+ "values('" + po.getCarID() + "','" + po.isFree() + "','" + po.getUseTimeForSQL() + "','"
				+ po.getNameID() + "','" + po.getChassisID() + "','" + po.getBuyTimeForSQL() + "','"
				+ po.getCarID().substring(0, 7) + "')";

		try {
			statement = conn.prepareStatement(insert);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = new OperationMessage(false, "新建时出错：");
			System.err.println("新建时出错：");
			e.printStackTrace();
		}

		return result;
	}

	public OperationMessage deleteCar(CarPO car) {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String delete = "delete from `" + Table_Name + "` where `carID` = '" + car.getCarID() + "'";
		try {
			statement = conn.prepareStatement(delete);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = new OperationMessage(false, "删除时出错：");
			System.err.println("删除时出错：");
			e.printStackTrace();
		}
		return result;
	}

	public OperationMessage modifyCar(CarPO car) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		String selectAll = "select * from `" + Table_Name + "` where `unitID` = '" + unitID + "'";
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
		// TODO Auto-generated method stub
		String select = "select * from `" + Table_Name + "` where `carID` = '" + carID + "'";
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
