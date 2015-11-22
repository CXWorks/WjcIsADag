package rmiImpl.transportdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import message.OperationMessage;
import po.FormEnum;
import po.deliverdata.DeliverPO;
import po.orderdata.OrderPO;
import po.receivedata.ReceivePO;
import po.transportdata.CenterOutPO;
import po.transportdata.LoadPO;
import po.transportdata.TransportPO;
import rmi.transportdata.CenterOutDataService;
import rmiImpl.CommonData;
import rmiImpl.ConnecterHelper;

public class CenterOutDataImpl extends CommonData<CenterOutPO> implements
		CenterOutDataService {

	private String Table_Name;
	private Connection conn = null;
	private PreparedStatement statement = null;

	public CenterOutDataImpl() throws RemoteException {
		// TODO Auto-generated constructor stub
		super();
		Table_Name = "centerout";
		conn = ConnecterHelper.connSQL(conn);
	}

	public Connection getConn() {
		return conn;
	}

	@Override
	public OperationMessage insert(CenterOutPO po) throws RemoteException {
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
				+ "peopleSee,expense,IDs,placeFrom,shelfNum,transitState) "
				+ "values('" + po.getFormID() + "','"
				+ po.getFormState().toString() + "','"
				+ po.getLoadDateForSQL().toString() + "','"
				+ po.getTransportID() + "','" + po.getPlaceTo() + "','"
				+ po.getPeopleSee() + "','" + po.getExpense() + "','" + IDs
				+ "','" + po.getPlaceFrom() + "','" + po.getShelfNum() + "','"
				+ po.getTransitState().toString() + "')";

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
	public CenterOutPO getFormPO(String id) throws RemoteException {
		// TODO Auto-generated method stub
		String select = "select * from " + Table_Name + " where formID= '" + id
				+ "'";
		ResultSet rs = null;
		CenterOutPO result = null;
		ArrayList<String> IDs = null;

		try {
			IDs = (ArrayList<String>) Arrays.asList(rs.getString("IDs").split(
					" "));
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			result = new CenterOutPO(rs.getString("placeFrom"),
					rs.getString("shelfNum"), rs.getString("transitState"),
					rs.getTimestamp("LoadDate"), rs.getString("TransportID"),
					rs.getString("placeTo"), rs.getString("peopleSee"),
					rs.getString("expense"), IDs);
			result.setFormType(FormEnum.TRANSPORT_CENTER);
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
	public OperationMessage update(CenterOutPO po) throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		if(!this.delete(po.getFormID()).operationResult)
			return result = new OperationMessage(false,"数据库中没有对应表单");
		if(!this.insert(po).operationResult)
			return result = new OperationMessage(false,"更新失败");
		else
			return result;
	}

	@Override
	public String newID() throws RemoteException {// 返回最大值
		// TODO Auto-generated method stub
		String selectAll = "select * from " + Table_Name;
		ResultSet rs = null;
		String ID_MAX = "";
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				ID_MAX = rs.getString("formID");
			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}

		return ID_MAX;
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
	public ArrayList<CenterOutPO> getAll() throws RemoteException {
		// TODO Auto-generated method stub
		String select = "select * from " + Table_Name;
		ResultSet rs = null;
		CenterOutPO temp = null;
		ArrayList<String> IDs = null;
		ArrayList<CenterOutPO> result = new ArrayList<CenterOutPO>();
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			while (rs.next()) {
				IDs = (ArrayList<String>) Arrays.asList(rs.getString("IDs")
						.split(" "));
				temp = new CenterOutPO(rs.getString("placeFrom"),
						rs.getString("shelfNum"), rs.getString("transitState"),
						rs.getTimestamp("LoadDate"),
						rs.getString("TransportID"), rs.getString("placeTo"),
						rs.getString("peopleSee"), rs.getString("expense"), IDs);
				temp.setFormType(FormEnum.TRANSPORT_CENTER);
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
