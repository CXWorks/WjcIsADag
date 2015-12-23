package rmiImpl.transportdata;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import database.ConnecterHelper;
import database.MySql;
import database.enums.TableEnum;
import message.OperationMessage;
import po.transportdata.LoadPO;
import rmi.transportdata.LoadDataService;
import rmiImpl.CommonData;

public class LoadDataImpl extends CommonData<LoadPO> implements LoadDataService {

	private Connection conn = null;
	private PreparedStatement statement = null;
	private String today = "";// 格式eg.2015-11-22
	private int ID_MAX;

	public LoadDataImpl() throws RemoteException {
		super();
		conn = ConnecterHelper.getConn();

		// 为today和ID_MAX初始化
		this.newID(null);
		ID_MAX--;
	}

	public Connection getConn() {
		return conn;
	}

	@Override
	public OperationMessage insert(LoadPO po) throws RemoteException {
		OperationMessage result = new OperationMessage();
		String IDs = "";
		ArrayList<String> list = po.getIDs();
		for (int i = 0; i < list.size(); i++)
			if (i == list.size() - 1)
				IDs += list.get(i);
			else
				IDs += list.get(i) + " ";
		;
		final String final_IDs = IDs;
		String insert = MySql.insert(TableEnum.LOAD, new HashMap<String, String>() {
			{
				put("formID", po.getFormID());
				put("formState", po.getFormState().toString());
				put("LoadDate", po.getLoadDateForSQL().toString());
				put("TransportID", po.getTransportID());
				put("placeTo", po.getPlaceTo().toString());
				put("peopleSee", po.getPeopleSee());
				put("expense", po.getExpense());
				put("IDs", final_IDs);
				put("date_and_unit", po.getFormID().substring(2, 17));
				put("peopleTrans", po.getPeopleTransport());
			}
		});
		try {
			statement = conn.prepareStatement(insert);
			statement.executeUpdate();
		} catch (SQLException e) {
			if (this.getFormPO(po.getFormID()) != null) {
				po.setFormID(this.newID(po.getFormID().substring(9, 17)));
				this.insert(po);
			} else {
				result = new OperationMessage(false, "新建时出错：");
				System.err.println("新建时出错：");
				e.printStackTrace();
			}
		}

		return result;
	}

	@Override
	public LoadPO getFormPO(String id) throws RemoteException {
		String select = MySql.select(TableEnum.LOAD, new HashMap<String, String>() {
			{
				put("formID", id);
			}
		});
		ResultSet rs = null;
		LoadPO result = null;

		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			ArrayList<String> IDs = new ArrayList<String>();
			if (!rs.getString("IDs").equalsIgnoreCase("")) {
				IDs = new ArrayList<String>(Arrays.asList(rs.getString("IDs").split(" ")));
			}
			result = new LoadPO(rs.getString("formID"), rs.getString("peopleTrans"), rs.getTimestamp("LoadDate"),
					rs.getString("TransportID"), rs.getString("placeTo"), rs.getString("peopleSee"),
					rs.getString("expense"), IDs);
			result.setFormState(rs.getString("formState"));
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
			return null;
		}
		return result;
	}

	@Override
	public OperationMessage delete(String id) throws RemoteException {
		OperationMessage result = new OperationMessage();
		String delete = MySql.delete(TableEnum.LOAD, new HashMap<String, String>() {
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
	public OperationMessage update(LoadPO po) throws RemoteException {
		OperationMessage result = new OperationMessage();
		if (!this.delete(po.getFormID()).operationResult)
			return result = new OperationMessage(false, "数据库中没有对应表单");
		if (!this.insert(po).operationResult)
			return result = new OperationMessage(false, "更新失败");
		else
			return result;
	}

	@Override
	public String newID(String unitID) throws RemoteException {
		ResultSet rs = null;
		String temp = new Timestamp(System.currentTimeMillis()).toString().substring(0, 10);
		final String target = unitID + temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8);

		// 当前日期与缓存日期一致
		if (temp.equalsIgnoreCase(today)) {
			this.ID_MAX++;
			String added = String.format("%07d", ID_MAX);
			return "08" + target + added;
		}

		// 当前日期与缓存日期不一致
		today = temp;
		String selectAll = MySql.select(TableEnum.LOAD, new HashMap<String, String>() {
			{
				put("date_and_unit", target);
			}
		});
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

		return "08" + target + added;
	}

	@Override
	public OperationMessage clear() throws RemoteException {
		OperationMessage result = new OperationMessage();
		String clear = MySql.delete(TableEnum.LOAD, null);
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
	public ArrayList<LoadPO> getAll() throws RemoteException {
		String select = MySql.select(TableEnum.LOAD, null);
		ResultSet rs = null;
		LoadPO temp = null;
		ArrayList<LoadPO> result = new ArrayList<LoadPO>();
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			while (rs.next()) {
				ArrayList<String> IDs = new ArrayList<String>();
				if (!rs.getString("IDs").equalsIgnoreCase("")) {
					IDs = new ArrayList<String>(Arrays.asList(rs.getString("IDs").split(" ")));
				}
				temp = new LoadPO(rs.getString("formID"), rs.getString("peopleTransport"), rs.getTimestamp("LoadDate"),
						rs.getString("TransportID"), rs.getString("placeTo"), rs.getString("peopleSee"),
						rs.getString("expense"), IDs);
				temp.setFormState(rs.getString("formState"));
				result.add(temp);
			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}
		return result;
	}

}
