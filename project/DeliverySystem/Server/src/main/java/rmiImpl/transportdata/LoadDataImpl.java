package rmiImpl.transportdata;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

import message.OperationMessage;
import po.FormEnum;
import po.transportdata.CenterOutPO;
import po.transportdata.LoadPO;
import rmi.transportdata.LoadDataService;
import rmiImpl.CommonData;
import rmiImpl.ConnecterHelper;

public class LoadDataImpl extends CommonData<LoadPO> implements LoadDataService {

	private String Table_Name;
	private Connection conn = null;
	private PreparedStatement statement = null;

	public LoadDataImpl() throws RemoteException {
		// TODO Auto-generated constructor stub
		super();
		Table_Name = "centerout";
		conn = ConnecterHelper.connSQL(conn);
	}

	public Connection getConn() {
		return conn;
	}

	@Override
	public OperationMessage insert(LoadPO po) throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String IDs = "";
		ArrayList<String> list = po.getIDs();
		for (int i = 0; i < list.size(); i++)
			if (i == list.size() - 1)
				IDs += list.get(i);
			else
				IDs += list.get(i) + " ";
		;

		String insert = "insert into " + Table_Name
				+ "(formID,formState,LoadDate,TransportID,placeTo,"
				+ "peopleSee,expense,IDs,peopleTransport) " + "values('"
				+ po.getFormID() + "','" + po.getFormState().toString() + "','"
				+ po.getLoadDateForSQL().toString() + "','"
				+ po.getTransportID() + "','" + po.getPlaceTo() + "','"
				+ po.getPeopleSee() + "','" + po.getExpense() + "','" + IDs
				+ "','" + po.getPeopleTransport() + "')";

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

	@Override
	public LoadPO getFormPO(String id) throws RemoteException {
		// TODO Auto-generated method stub
		String select = "select * from " + Table_Name + " where formID= '" + id
				+ "'";
		ResultSet rs = null;
		LoadPO result = null;
		ArrayList<String> IDs = null;

		try {
			IDs = (ArrayList<String>) Arrays.asList(rs.getString("IDs").split(
					" "));
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			result = new LoadPO(rs.getString("peopleTransport"),
					rs.getTimestamp("LoadDate"), rs.getString("TransportID"),
					rs.getString("placeTo"), rs.getString("peopleSee"),
					rs.getString("expense"), IDs);
			result.setFormType(FormEnum.TRANSPORT_HALL);
			result.setFormID(rs.getString("formID"));
			result.setFormState(rs.getString("formState"));
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public OperationMessage delete(String id) throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String delete = "delete from " + Table_Name + " where formID= '" + id
				+ "'";
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
	public OperationMessage update(LoadPO po) throws RemoteException {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		String selectAll = "select * from " + Table_Name;
		ResultSet rs = null;
		int ID_MAX = 0;
		String temp = new Timestamp(System.currentTimeMillis()).toString()
				.substring(0, 10);
		String target = temp.substring(0, 4) + temp.substring(5, 7)
				+ temp.substring(8);
		target = unitID + target;// 开具单位编号+当天日期
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				temp = rs.getString("formID").substring(2, 17);
				if (target.equalsIgnoreCase(temp))
					ID_MAX = Math.max(ID_MAX, Integer.parseInt(rs.getString(
							"formID").substring(17)));// 最后6位编号
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
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String clear = "delete from " + Table_Name;
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
	public ArrayList<LoadPO> getAll() throws RemoteException {
		// TODO Auto-generated method stub
		String select = "select * from " + Table_Name;
		ResultSet rs = null;
		LoadPO temp = null;
		ArrayList<String> IDs = null;
		ArrayList<LoadPO> result = new ArrayList<LoadPO>();
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			while (rs.next()) {
				IDs = (ArrayList<String>) Arrays.asList(rs.getString("IDs")
						.split(" "));
				temp = new LoadPO(rs.getString("peopleTransport"),
						rs.getTimestamp("LoadDate"),
						rs.getString("TransportID"), rs.getString("placeTo"),
						rs.getString("peopleSee"), rs.getString("expense"), IDs);
				temp.setFormType(FormEnum.TRANSPORT_HALL);
				temp.setFormID(rs.getString("formID"));
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
