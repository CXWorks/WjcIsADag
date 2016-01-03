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
import java.util.List;

import database.ConnecterHelper;
import database.MySql;
import database.enums.TableEnum;
import message.OperationMessage;
import po.transportdata.CenterOutPO;
import rmi.transportdata.CenterOutDataService;
import rmiImpl.CommonData;

public class CenterOutDataImpl extends CommonData<CenterOutPO> implements CenterOutDataService {

	private Connection conn = null;
	private PreparedStatement statement = null;
	private String today = "";// 格式eg.2015-11-22
	private int ID_MAX;

	public CenterOutDataImpl() throws RemoteException {
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
	public OperationMessage insert(CenterOutPO po) throws RemoteException {
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
		String insert = MySql.insert(TableEnum.CENTEROUT, new HashMap<String, String>() {
			{
				put("formID", po.getFormID());
				put("formState", po.getFormState().toString());
				put("LoadDate", po.getLoadDateForSQL().toString());
				put("TransportID", po.getTransportID());
				put("placeTo", po.getPlaceTo().toString());
				put("peopleSee", po.getPeopleSee());
				put("expense", po.getExpense());
				put("IDs", final_IDs);
				put("placeFrom", po.getPlaceFrom());
				put("shelfNum", po.getShelfNum());
				put("transitState", po.getTransitState().toString());
				put("date_and_unit", po.getFormID().substring(2, 17));
				put("creatorID", po.getCreatorID());
				put("truckID", po.getNumberOfIndex());
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
	public CenterOutPO getFormPO(String id) throws RemoteException {
		String select = MySql.select(TableEnum.CENTEROUT, new HashMap<String, String>() {
			{
				put("formID", id);
			}
		});
		ResultSet rs = null;
		CenterOutPO result = null;

		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			ArrayList<String> IDs = new ArrayList<String>();
			if (!rs.getString("IDs").equalsIgnoreCase("")) {
				IDs = new ArrayList<String>(Arrays.asList(rs.getString("IDs").split(" ")));
			}
			result = new CenterOutPO(rs.getString("formID"), rs.getString("placeFrom"), rs.getString("shelfNum"),
					rs.getString("transitState"), rs.getTimestamp("LoadDate"), rs.getString("TransportID"),
					rs.getString("placeTo"), rs.getString("peopleSee"), rs.getString("expense"), IDs,
					rs.getString("creatorID"), rs.getString("truckID"));
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
		String delete = MySql.delete(TableEnum.CENTEROUT, new HashMap<String, String>() {
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
	public OperationMessage update(CenterOutPO po) throws RemoteException {
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
			return "07" + target + added;
		}

		// 当前日期与缓存日期不一致
		today = temp;
		String selectAll = MySql.select(TableEnum.CENTEROUT, new HashMap<String, String>() {
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

		return "07" + target + added;
	}

	@Override
	public OperationMessage clear() throws RemoteException {
		OperationMessage result = new OperationMessage();
		String clear = MySql.delete(TableEnum.CENTEROUT, null);
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
	public ArrayList<CenterOutPO> getAll() throws RemoteException {
		String select = MySql.select(TableEnum.CENTEROUT, null);
		ResultSet rs = null;
		CenterOutPO temp = null;
		ArrayList<CenterOutPO> result = new ArrayList<CenterOutPO>();
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			while (rs.next()) {
				ArrayList<String> IDs = new ArrayList<String>();
				if (!rs.getString("IDs").equalsIgnoreCase("")) {
					IDs = new ArrayList<String>(Arrays.asList(rs.getString("IDs").split(" ")));
				}
				temp = new CenterOutPO(rs.getString("formID"), rs.getString("placeFrom"), rs.getString("shelfNum"),
						rs.getString("transitState"), rs.getTimestamp("LoadDate"), rs.getString("TransportID"),
						rs.getString("placeTo"), rs.getString("peopleSee"), rs.getString("expense"), IDs,
						rs.getString("creatorID"), rs.getString("truckID"));
				temp.setFormState(rs.getString("formState"));
				result.add(temp);
			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) throws RemoteException {
		CenterOutDataImpl t = new CenterOutDataImpl();
		System.out.println(t.newTransID("0210001"));
	}

	@Override
	public String newTransID(String unitID) throws RemoteException {
		ResultSet rs = null;
		Timestamp date = null;
		String temp = new Timestamp(System.currentTimeMillis()).toString().substring(0, 10);
		String today = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8);
		String select = MySql.select(TableEnum.TRANS, new HashMap<String, String>() {
			{
				put("unitID", unitID);
			}
		});
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			if (rs != null) {
				date = rs.getTimestamp("date");
			}
		} catch (SQLException e) {
			System.err.println("访问数据库时出错：");
			e.printStackTrace();
		}
		if (date == null) {
			String insert = MySql.insert(TableEnum.TRANS, new HashMap<String, String>() {
				{
					put("unitID", unitID);
					put("num", 0 + "");
				}
			});
			try {
				statement = conn.prepareStatement(insert);
				statement.executeUpdate();
			} catch (SQLException e) {
				System.err.println("新建时出错：");
				e.printStackTrace();
			}
			return unitID + today + String.format("%05d", 1);
		}
		String date_in_sql = date.toString().substring(0, 10);
		date_in_sql = date_in_sql.substring(0, 4) + date_in_sql.substring(5, 7) + date_in_sql.substring(8);

		// update date
		String update = MySql.update(TableEnum.TRANS, "date", new Timestamp(System.currentTimeMillis()).toString(),
				new HashMap<String, String>() {
					{
						put("unitID", unitID);
					}
				});
		try {
			statement = conn.prepareStatement(update);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("更新日期时出错：");
			e.printStackTrace();
		}

		// 当前日期与缓存日期一致
		if (date_in_sql.equalsIgnoreCase(today)) {
			int num = 0;
			try {
				num = Integer.parseInt(rs.getString("num"));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			this.setNum(unitID, ++num + "");
			return unitID + today + String.format("%05d", num);
		} else {// 当前日期与缓存日期不一致
			this.setNum(unitID, 1 + "");
			return unitID + today + String.format("%05d", 1);
		}
	}

	private boolean setNum(String unitID, String num) throws RemoteException {
		// update num
		String update = MySql.update(TableEnum.TRANS, "num", num, new HashMap<String, String>() {
			{
				put("unitID", unitID);
			}
		});
		try {
			statement = conn.prepareStatement(update);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("更新编号时出错：");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<CenterOutPO> getHistory(String creatorID) throws RemoteException {
		String select = MySql.select(TableEnum.CENTEROUT, new HashMap<String, String>() {
			{
				put("creatorID", creatorID);
			}
		});
		ResultSet rs = null;
		CenterOutPO temp = null;
		ArrayList<CenterOutPO> result = new ArrayList<CenterOutPO>();
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			while (rs.next()) {
				ArrayList<String> IDs = new ArrayList<String>();
				if (!rs.getString("IDs").equalsIgnoreCase("")) {
					IDs = new ArrayList<String>(Arrays.asList(rs.getString("IDs").split(" ")));
				}
				temp = new CenterOutPO(rs.getString("formID"), rs.getString("placeFrom"), rs.getString("shelfNum"),
						rs.getString("transitState"), rs.getTimestamp("LoadDate"), rs.getString("TransportID"),
						rs.getString("placeTo"), rs.getString("peopleSee"), rs.getString("expense"), IDs,
						rs.getString("creatorID"), rs.getString("truckID"));
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
