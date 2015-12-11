package rmiImpl.storedata;

import message.OperationMessage;
import model.store.StoreModel;
import model.store.StoreModelOperation;
import po.FormPO;
import po.receivedata.ReceivePO;
import po.storedata.StoreInPO;
import po.storedata.StoreOutPO;
import rmi.storedata.StoreFormDataService;
import rmi.storedata.StoreModelDataService;
import rmiImpl.financedata.PaymentDataImpl;
import database.ConnecterHelper;
import util.R;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sissel on 2015/10/25.
 */
public class StoreFormDataImpl extends UnicastRemoteObject implements StoreFormDataService {

	/** 接口的名称，RMI绑定时候的名称 */
	public static final String NAME = "StoreData";

	private String Store_In;
	private String Store_Out;
	private Connection conn = null;
	private PreparedStatement statement = null;

	public StoreFormDataImpl() throws RemoteException, MalformedURLException {
		// TODO Auto-generated constructor stub
		super();
		Store_In = "store_in";
		Store_Out = "store_out";
		conn = ConnecterHelper.getConn();
	}

	public Connection getConn() {
		return conn;
	}

	@Override
	public OperationMessage insertStoreInPO(StoreInPO po) throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String insert = "insert into `" + Store_In
				+ "`(formID,formState,orderID,date,destination,location,money,date_and_unit) " + "values('"
				+ po.getFormID() + "','" + po.getFormState().toString() + "','" + po.getOrderID() + "','"
				+ po.getDateForSQL().toString() + "','" + po.getDestination() + "','"
				+ po.getLocation().getLocationForSQL() + "','" + po.getMoney() + "','" + po.getFormID().substring(2, 17)
				+ "')";

		try {
			statement = conn.prepareStatement(insert);
			statement.executeUpdate();
		} catch (SQLException e) {
			if (this.getStoreInPO(po.getFormID()) != null) {
				po.setFormID(this.newIDStoreInPO(po.getFormID().substring(9, 17)));
				this.insertStoreInPO(po);
			} else {
				result = new OperationMessage(false, "新建时出错：");
				System.err.println("新建时出错：");
				e.printStackTrace();
			}
		}

		return result;
	}

	@Override
	public OperationMessage insertStoreOutPO(StoreOutPO po) throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String insert = "insert into `" + Store_Out
				+ "`(formID,formState,orderID,date,destination,transportation,transID,money,location,date_and_unit) "
				+ "values('" + po.getFormID() + "','" + po.getFormState().toString() + "','" + po.getOrderID() + "','"
				+ po.getDateForSQL().toString() + "','" + po.getDestination() + "','"
				+ po.getTransportation().toString() + "','" + po.getTransID() + "','" + po.getMoney() + "','"
				+ po.getLocation().getLocationForSQL() + "','" + po.getFormID().substring(2, 17) + "')";

		try {
			statement = conn.prepareStatement(insert);
			statement.executeUpdate();
		} catch (SQLException e) {
			if (this.getStoreOutPO(po.getFormID()) != null) {
				po.setFormID(this.newIDStoreOutPO(po.getFormID().substring(9, 17)));
				this.insertStoreOutPO(po);
			} else {
				result = new OperationMessage(false, "新建时出错：");
				System.err.println("新建时出错：");
				e.printStackTrace();
			}
		}

		return result;
	}

	@Override
	public OperationMessage deleteStoreInPO(String id) throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String delete = "delete from `" + Store_In + "` where `formID` = '" + id + "'";
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

	@Override
	public OperationMessage deleteStoreOutPO(String id) throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String delete = "delete from `" + Store_Out + "` where `formID` = '" + id + "'";
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

	@Override
	public OperationMessage updateStoreInPO(StoreInPO po) throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		if (!this.deleteStoreInPO(po.getFormID()).operationResult)
			return result = new OperationMessage(false, "数据库中没有对应表单");
		if (!this.insertStoreInPO(po).operationResult)
			return result = new OperationMessage(false, "更新失败");
		else
			return result;
	}

	@Override
	public OperationMessage updateStoreOutPO(StoreOutPO po) throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		if (!this.deleteStoreOutPO(po.getFormID()).operationResult)
			return result = new OperationMessage(false, "数据库中没有对应表单");
		if (!this.insertStoreOutPO(po).operationResult)
			return result = new OperationMessage(false, "更新失败");
		else
			return result;
	}

	@Override
	public OperationMessage clearStoreInPO() throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String clear = "delete from `" + Store_In + "`";
		try {
			statement = conn.prepareStatement(clear);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = new OperationMessage(false, "清空数据库时出错：");
			System.err.println("清空数据库时出错：");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public OperationMessage clearStoreOutPO() throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String clear = "delete from `" + Store_Out + "`";
		try {
			statement = conn.prepareStatement(clear);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = new OperationMessage(false, "清空数据库时出错：");
			System.err.println("清空数据库时出错：");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String newIDStoreInPO(String unitID) throws RemoteException {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		int ID_MAX = 0;
		String temp = new Timestamp(System.currentTimeMillis()).toString().substring(0, 10);
		String target = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8);
		target = unitID + target;// 开具单位编号+当天日期
		String selectAll = "select * from `" + Store_In + "` where `date_and_unit` = '" + target + "'";
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				ID_MAX = Math.max(ID_MAX, Integer.parseInt(rs.getString("formID").substring(17)));// 最后7位编号
			}
		} catch (SQLException e) {
			System.err.println("访问数据库时出错：");
			e.printStackTrace();
		}

		ID_MAX++;// 将该数字加一
		if (ID_MAX > 9999999)
			return null;
		String added = String.format("%07d", ID_MAX);

		return "05" + target + added;
	}

	@Override
	public String newIDStoreOutPO(String unitID) throws RemoteException {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		int ID_MAX = 0;
		String temp = new Timestamp(System.currentTimeMillis()).toString().substring(0, 10);
		String target = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8);
		target = unitID + target;// 开具单位编号+当天日期
		String selectAll = "select * from `" + Store_Out + "` where `date_and_unit` = '" + target + "'";
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				ID_MAX = Math.max(ID_MAX, Integer.parseInt(rs.getString("formID").substring(17)));// 最后7位编号
			}
		} catch (SQLException e) {
			System.err.println("访问数据库时出错：");
			e.printStackTrace();
		}

		ID_MAX++;// 将该数字加一
		if (ID_MAX > 9999999)
			return null;
		String added = String.format("%07d", ID_MAX);

		return "06" + target + added;
	}

	@Override
	public StoreInPO getStoreInPO(String id) throws RemoteException {
		// TODO Auto-generated method stub
		String select = "select * from `" + Store_In + "` where `formID` = '" + id + "'";
		ResultSet rs = null;
		StoreInPO result = null;
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			result = new StoreInPO(rs.getString("formID"), rs.getString("orderID"), rs.getTimestamp("date"),
					rs.getString("destination"), rs.getString("location"));
			result.setFormState(rs.getString("formState"));
			result.setMoney(rs.getString("money"));
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
			return null;
		}
		return result;
	}

	@Override
	public StoreOutPO getStoreOutPO(String id) throws RemoteException {
		// TODO Auto-generated method stub
		String select = "select * from `" + Store_Out + "` where `formID` = '" + id + "'";
		ResultSet rs = null;
		StoreOutPO result = null;
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			result = new StoreOutPO(rs.getString("formID"), rs.getString("orderID"), rs.getTimestamp("date"),
					rs.getString("destination"), rs.getString("transportation"), rs.getString("transID"));
			result.setFormState(rs.getString("formState"));
			result.setMoney(rs.getString("money"));
			result.setLocation(rs.getString("location"));
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
			return null;
		}
		return result;
	}

	@Override
	public ArrayList<StoreInPO> getAllStoreInPO() throws RemoteException {
		// TODO Auto-generated method stub
		String selectAll = "select * from `" + Store_In + "`";
		ResultSet rs = null;
		StoreInPO temp = null;
		ArrayList<StoreInPO> result = new ArrayList<StoreInPO>();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				temp = new StoreInPO(rs.getString("formID"), rs.getString("orderID"), rs.getTimestamp("date"),
						rs.getString("destination"), rs.getString("location"));
				temp.setFormState(rs.getString("formState"));
				temp.setMoney(rs.getString("money"));
				result.add(temp);

			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public ArrayList<StoreOutPO> getAllStoreOutPO() throws RemoteException {
		// TODO Auto-generated method stub
		String selectAll = "select * from `" + Store_Out + "`";
		ResultSet rs = null;
		StoreOutPO temp = null;
		ArrayList<StoreOutPO> result = new ArrayList<StoreOutPO>();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				temp = new StoreOutPO(rs.getString("formID"), rs.getString("orderID"), rs.getTimestamp("date"),
						rs.getString("destination"), rs.getString("transportation"), rs.getString("transID"));
				temp.setFormState(rs.getString("formState"));
				temp.setMoney(rs.getString("money"));
				temp.setLocation(rs.getString("location"));
				result.add(temp);

			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public ArrayList<FormPO> getInOutInfo(Calendar start, Calendar end) throws RemoteException {
		String selectIn = "select * from `" + Store_In + "` where '" + start.getTime().getTime() / 1000
				+ "' < UNIX_TIMESTAMP(date) " + "and '" + end.getTime().getTime() / 1000
				+ "' > UNIX_TIMESTAMP(date)";
		String selectOut = "select * from `" + Store_Out + "` where '" + start.getTime().getTime() / 1000
				+ "' < UNIX_TIMESTAMP(date) " + "and '" + end.getTime().getTime() / 1000
				+ "' > UNIX_TIMESTAMP(date)";
		ResultSet rs = null;
		StoreInPO in = null;
		StoreOutPO out = null;
		ArrayList<FormPO> result = new ArrayList<FormPO>();
		try {
			statement = conn.prepareStatement(selectIn);
			rs = statement.executeQuery(selectIn);
			while (rs.next()) {
				in = new StoreInPO(rs.getString("formID"), rs.getString("orderID"), rs.getTimestamp("date"),
						rs.getString("destination"), rs.getString("location"));
				in.setFormState(rs.getString("formState"));
				in.setMoney(rs.getString("money"));
				result.add(in);
			}
			statement = conn.prepareStatement(selectOut);
			rs = statement.executeQuery(selectOut);
			while (rs.next()) {
				out = new StoreOutPO(rs.getString("formID"), rs.getString("orderID"), rs.getTimestamp("date"),
						rs.getString("destination"), rs.getString("transportation"), rs.getString("transID"));
				out.setFormState(rs.getString("formState"));
				out.setMoney(rs.getString("money"));
				out.setLocation(rs.getString("location"));
				result.add(out);
			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}

		return result;
	}
}
