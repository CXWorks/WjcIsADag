package rmiImpl.storedata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.tools.DocumentationTool.Location;

import po.receivedata.ReceivePO;
import po.systemdata.LogPO;
import message.OperationMessage;
import model.store.StoreArea;
import model.store.StoreAreaCode;
import model.store.StoreLocation;
import model.store.StoreModel;
import model.store.StoreModelOperation;
import rmi.storedata.StoreModelDataService;
import database.ConnecterHelper;
import database.RMIHelper;

public class StoreModelDataImpl extends UnicastRemoteObject implements StoreModelDataService {

	private String Table_Name = "";
	private String area = "";
	private Connection conn = null;
	private PreparedStatement statement = null;
	/* 一个架子中position的数量 */
	private final static int NUM = 50;

	public StoreModelDataImpl() throws RemoteException {
		// TODO Auto-generated constructor stub
		super();
		Table_Name = "model_store";
		conn = ConnecterHelper.getConn();
	}

	// public static void main(String[] args) {
	// try {
	// StoreModelDataImpl tDataImpl = new StoreModelDataImpl();
	// tDataImpl.newShelf(StoreAreaCode.ROAD, 1, 1);
	//
	// } catch (RemoteException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }

	@Override
	public Connection getConn() throws RemoteException {
		// TODO Auto-generated method stub
		return conn;
	}

	public void setTableName(StoreAreaCode code) throws RemoteException {
		// TODO Auto-generated method stub
		if (code.equals(StoreAreaCode.AIR))
			area = "AIR";
		else if (code.equals(StoreAreaCode.RAIL))
			area = "RAIL";
		else if (code.equals(StoreAreaCode.ROAD))
			area = "ROAD";
		else if (code.equals(StoreAreaCode.FLEX))
			area = "FLEX";
		return;
	}

	@Override
	public StoreArea getArea(String centerID, StoreAreaCode code) throws RemoteException {
		// TODO Auto-generated method stub
		this.setTableName(code);
		String selectAll = "select * from `" + Table_Name + "` where `centerID` = '" + centerID + "' and `area` = '"
				+ area + "'";
		ResultSet rs = null;
		StoreArea result = null;
		StoreLocation tmp = null;
		ArrayList<StoreLocation> list = new ArrayList<StoreLocation>();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				tmp = new StoreLocation(code, rs.getInt("row"), rs.getInt("shelf"), rs.getInt("position"),
						rs.getString("orderID"));
				list.add(tmp);
			}
			result = new StoreArea(code, list);
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public StoreModel getModel(String centerID) throws RemoteException {
		// TODO Auto-generated method stub
		return new StoreModel(centerID, this.getArea(centerID, StoreAreaCode.AIR),
				this.getArea(centerID, StoreAreaCode.RAIL), this.getArea(centerID, StoreAreaCode.ROAD),
				this.getArea(centerID, StoreAreaCode.FLEX));
	}

	@Override
	public OperationMessage newShelf(String centerID, StoreAreaCode code, int row, int shelf) throws RemoteException {
		// TODO Auto-generated method stub
		this.setTableName(code);
		OperationMessage result = new OperationMessage();
		try {
			for (int i = 1; i <= NUM; i++) {
				String insert = "insert into " + Table_Name + "(centerID,area,row,shelf,position,orderID) " + "values('"
						+ centerID + "','" + area + "','" + row + "','" + shelf + "','" + i + "','" + "" + "')";
				statement = conn.prepareStatement(insert);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = new OperationMessage(false, "新建时出错：");
			System.err.println("新建时出错：");
			e.printStackTrace();
		}

		// 系统日志
		if (result.operationResult == true)
			RMIHelper.getLogDataService().insert(new LogPO("仓库管理人员", Calendar.getInstance(), "新增货架"));

		return result;
	}

	@Override
	public OperationMessage removeShelf(String centerID, StoreAreaCode code, int row, int shelf)
			throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		this.setTableName(code);
		String delete = "delete from `" + Table_Name + "` where `centerID` = '" + centerID + "' and `area` = '" + area
				+ "' and `row` = '" + row + "' and `shelf` = '" + shelf + "'";
		try {
			statement = conn.prepareStatement(delete);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("删除时出错：");
			result = new OperationMessage(false, "删除时出错：");
			e.printStackTrace();
		}

		// 系统日志
		if (result.operationResult == true)
			RMIHelper.getLogDataService().insert(new LogPO("仓库管理人员", Calendar.getInstance(), "移除货架"));

		return result;
	}

	@Override
	public OperationMessage moveShelf(String centerID, StoreAreaCode code_now, int row_now, int shelf_now,
			StoreAreaCode code, int row, int shelf) throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		if (!this.removeShelf(centerID, code_now, row_now, shelf_now).operationResult)
			return result = new OperationMessage(false, "数据库中没有对应表单");
		if (!this.newShelf(centerID, code, row, shelf).operationResult)
			return result = new OperationMessage(false, "更新失败");
		else
			return result;
	}

	@Override
	public OperationMessage setLocation(String centerID, StoreLocation location) throws RemoteException {
		// TODO Auto-generated method stub
		this.setTableName(location.getArea());
		OperationMessage result = new OperationMessage();
		String updata = "update " + Table_Name + " set orderID= '" + location.getOrderID() + "` where `centerID` = '"
				+ centerID + "' and `area` = '" + area + "' and `row` = '" + location.getRow() + "' and `shelf` = '"
				+ location.getShelf() + "' and `position` = '" + location.getPosition() + "'";
		try {
			statement = conn.prepareStatement(updata);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = new OperationMessage(false, "修改时出错：");
			System.err.println("修改时出错：");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String getLocation(String centerID, StoreAreaCode code, int row, int shelf, int position)
			throws RemoteException {
		// TODO Auto-generated method stub
		this.setTableName(code);
		ResultSet rs = null;
		String select = "select * from " + Table_Name + "` where `centerID` = '" + centerID + "' and `area` = '" + area
				+ "' and `row` = '" + row + "' and `shelf` = '" + shelf + "' and `position` = '" + position + "'";
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			return rs.getString("orderID");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("查找时出错：");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<StoreModel> getModels() throws RemoteException {
		// TODO Auto-generated method stub
		String selectAll = "select * from `" + Table_Name + "`";
		ResultSet rs = null;
		StoreModel temp = null;
		List<StoreModel> result = new ArrayList<StoreModel>();
		ArrayList<String> list = new ArrayList<String>();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				if (list.indexOf(rs.getString("centerID"))==-1){
					list.add(rs.getString("centerID"));
				}
			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}
		for (String tmp : list) {
			temp = new StoreModel(tmp, this.getArea(tmp, StoreAreaCode.AIR),
					this.getArea(tmp, StoreAreaCode.RAIL), this.getArea(tmp, StoreAreaCode.ROAD),
					this.getArea(tmp, StoreAreaCode.FLEX));
			result.add(temp);
		}
		return result;
	}

}
