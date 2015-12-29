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
import database.MySql;
import database.enums.TableEnum;
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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sissel on 2015/10/25.
 */
public class StoreFormDataImpl extends UnicastRemoteObject implements StoreFormDataService {

	/** 接口的名称，RMI绑定时候的名称 */
	public static final String NAME = "StoreData";

	private Connection conn = null;
	private PreparedStatement statement = null;
	private String today = "";// 格式eg.2015-11-22
	private int ID_MAX_In;
	private int ID_MAX_Out;

	public StoreFormDataImpl() throws RemoteException, MalformedURLException {
		super();
		conn = ConnecterHelper.getConn();

		// 为today和ID_MAX初始化
		this.newIDStoreInPO(null);
		this.newIDStoreOutPO(null);
		ID_MAX_In--;
		ID_MAX_Out--;
	}

	public Connection getConn() {
		return conn;
	}

	@Override
	public OperationMessage insertStoreInPO(StoreInPO po) throws RemoteException {
		OperationMessage result = new OperationMessage();
		String insert = MySql.insert(TableEnum.STORE_IN, new HashMap<String, String>() {
			{
				put("formID", po.getFormID());
				put("formState", po.getFormState().toString());
				put("orderID", po.getOrderID());
				put("date", po.getDateForSQL().toString());
				put("destination", po.getDestination());
				put("location", po.getLocation().getLocationForSQL());
				put("money", po.getMoney());
				put("date_and_unit", po.getFormID().substring(2, 17));
				put("creatorID", po.getCreatorID());
			}
		});
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
		OperationMessage result = new OperationMessage();
		String insert = MySql.insert(TableEnum.STORE_OUT, new HashMap<String, String>() {
			{
				put("formID", po.getFormID());
				put("formState", po.getFormState().toString());
				put("orderID", po.getOrderID());
				put("date", po.getDateForSQL().toString());
				put("destination", po.getDestination());
				put("transportation", po.getTransportation().toString());
				put("transID", po.getTransID());
				put("money", po.getMoney());
				put("location", po.getLocation().getLocationForSQL());
				put("date_and_unit", po.getFormID().substring(2, 17));
				put("creatorID", po.getCreatorID());
			}
		});
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
		OperationMessage result = new OperationMessage();
		String delete = MySql.delete(TableEnum.STORE_IN, new HashMap<String, String>() {
			{
				put("formID", id);
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

	@Override
	public OperationMessage deleteStoreOutPO(String id) throws RemoteException {
		OperationMessage result = new OperationMessage();
		String delete = MySql.delete(TableEnum.STORE_OUT, new HashMap<String, String>() {
			{
				put("formID", id);
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

	@Override
	public OperationMessage updateStoreInPO(StoreInPO po) throws RemoteException {
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
		OperationMessage result = new OperationMessage();
		String clear = MySql.delete(TableEnum.STORE_IN, null);
		try {
			statement = conn.prepareStatement(clear);
			statement.executeUpdate();
		} catch (SQLException e) {
			result = new OperationMessage(false, "清空数据库时出错：");
			System.err.println("清空数据库时出错：");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public OperationMessage clearStoreOutPO() throws RemoteException {
		OperationMessage result = new OperationMessage();
		String clear = MySql.delete(TableEnum.STORE_OUT, null);
		try {
			statement = conn.prepareStatement(clear);
			statement.executeUpdate();
		} catch (SQLException e) {
			result = new OperationMessage(false, "清空数据库时出错：");
			System.err.println("清空数据库时出错：");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String newIDStoreInPO(String unitID) throws RemoteException {
		ResultSet rs = null;
		String temp = new Timestamp(System.currentTimeMillis()).toString().substring(0, 10);
		final String target = unitID + temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8);

		// 当前日期与缓存日期一致
		if (temp.equalsIgnoreCase(today)) {
			this.ID_MAX_In++;
			String added = String.format("%07d", ID_MAX_In);
			return "05" + target + added;
		}

		// 当前日期与缓存日期不一致
		today = temp;
		String selectAll = MySql.select(TableEnum.STORE_IN, new HashMap<String, String>() {
			{
				put("date_and_unit", target);
			}
		});
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				ID_MAX_In = Math.max(ID_MAX_In, Integer.parseInt(rs.getString("formID").substring(17)));// 最后7位编号
			}
		} catch (SQLException e) {
			System.err.println("访问数据库时出错：");
			e.printStackTrace();
		}

		ID_MAX_In++;// 将该数字加一
		if (ID_MAX_In > 9999999)
			return null;
		String added = String.format("%07d", ID_MAX_In);

		return "05" + target + added;
	}

	@Override
	public String newIDStoreOutPO(String unitID) throws RemoteException {
		ResultSet rs = null;
		String temp = new Timestamp(System.currentTimeMillis()).toString().substring(0, 10);
		final String target = unitID + temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8);

		// 当前日期与缓存日期一致
		if (temp.equalsIgnoreCase(today)) {
			this.ID_MAX_Out++;
			String added = String.format("%07d", ID_MAX_Out);
			return "06" + target + added;
		}

		// 当前日期与缓存日期不一致
		today = temp;
		String selectAll = MySql.select(TableEnum.STORE_OUT, new HashMap<String, String>() {
			{
				put("date_and_unit", target);
			}
		});
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				ID_MAX_Out = Math.max(ID_MAX_Out, Integer.parseInt(rs.getString("formID").substring(17)));// 最后7位编号
			}
		} catch (SQLException e) {
			System.err.println("访问数据库时出错：");
			e.printStackTrace();
		}

		ID_MAX_Out++;// 将该数字加一
		if (ID_MAX_Out > 9999999)
			return null;
		String added = String.format("%07d", ID_MAX_Out);

		return "06" + target + added;
	}

	@Override
	public StoreInPO getStoreInPO(String id) throws RemoteException {
		String select = MySql.select(TableEnum.STORE_IN, new HashMap<String, String>() {
			{
				put("formID", id);
			}
		});
		ResultSet rs = null;
		StoreInPO result = null;
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			result = new StoreInPO(rs.getString("formID"), rs.getString("orderID"), rs.getTimestamp("date"),
					rs.getString("destination"), rs.getString("location"), rs.getString("creatorID"));
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
		String select = MySql.select(TableEnum.STORE_OUT, new HashMap<String, String>() {
			{
				put("formID", id);
			}
		});
		ResultSet rs = null;
		StoreOutPO result = null;
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			result = new StoreOutPO(rs.getString("formID"), rs.getString("orderID"), rs.getTimestamp("date"),
					rs.getString("destination"), rs.getString("transportation"), rs.getString("transID"),
					rs.getString("creatorID"));
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
		String selectAll = MySql.select(TableEnum.STORE_IN, null);
		ResultSet rs = null;
		StoreInPO temp = null;
		ArrayList<StoreInPO> result = new ArrayList<StoreInPO>();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				temp = new StoreInPO(rs.getString("formID"), rs.getString("orderID"), rs.getTimestamp("date"),
						rs.getString("destination"), rs.getString("location"), rs.getString("creatorID"));
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
		String selectAll = MySql.select(TableEnum.STORE_OUT, null);
		ResultSet rs = null;
		StoreOutPO temp = null;
		ArrayList<StoreOutPO> result = new ArrayList<StoreOutPO>();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				temp = new StoreOutPO(rs.getString("formID"), rs.getString("orderID"), rs.getTimestamp("date"),
						rs.getString("destination"), rs.getString("transportation"), rs.getString("transID"),
						rs.getString("creatorID"));
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
		String selectIn = MySql.time(TableEnum.STORE_IN, "date", start, end);
		String selectOut = MySql.time(TableEnum.STORE_OUT, "date", start, end);
		ResultSet rs = null;
		StoreInPO in = null;
		StoreOutPO out = null;
		ArrayList<FormPO> result = new ArrayList<FormPO>();
		try {
			statement = conn.prepareStatement(selectIn);
			rs = statement.executeQuery(selectIn);
			while (rs.next()) {
				in = new StoreInPO(rs.getString("formID"), rs.getString("orderID"), rs.getTimestamp("date"),
						rs.getString("destination"), rs.getString("location"), rs.getString("creatorID"));
				in.setFormState(rs.getString("formState"));
				in.setMoney(rs.getString("money"));
				result.add(in);
			}
			statement = conn.prepareStatement(selectOut);
			rs = statement.executeQuery(selectOut);
			while (rs.next()) {
				out = new StoreOutPO(rs.getString("formID"), rs.getString("orderID"), rs.getTimestamp("date"),
						rs.getString("destination"), rs.getString("transportation"), rs.getString("transID"),
						rs.getString("creatorID"));
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
