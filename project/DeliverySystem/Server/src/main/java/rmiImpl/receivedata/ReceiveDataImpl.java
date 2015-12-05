package rmiImpl.receivedata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import message.OperationMessage;
import po.FormEnum;
import po.receivedata.ReceivePO;
import rmi.receivedata.ReceiveDataService;
import rmiImpl.CommonData;
import database.ConnecterHelper;
import rmiImpl.orderdata.OrderDataImpl;

/**
 *
 * @author wjc 2015/10/24
 */

public class ReceiveDataImpl extends CommonData<ReceivePO> implements ReceiveDataService {

	private String Table_Name;
	private Connection conn = null;
	private PreparedStatement statement = null;

	public ReceiveDataImpl() throws RemoteException {
		// TODO Auto-generated constructor stub
		super();
		Table_Name = "receive";
		conn = ConnecterHelper.getConn();
	}

	public Connection getConn() {
		return conn;
	}

	public OperationMessage insert(ReceivePO po) {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String insert = "insert into `" + Table_Name
				+ "`(formID,formState,orderID,transitID,date,depature,state,date_and_unit) " + "values('"
				+ po.getFormID() + "','" + po.getFormState().toString() + "','" + po.getOrderID() + "','"
				+ po.getTransitID() + "','" + po.getDateForSQL().toString() + "','" + po.getDepature() + "','"
				+ po.getState() + "','" + po.getFormID().substring(2, 17) + "')";

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

	public OperationMessage delete(String id) {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String delete = "delete from `" + Table_Name + "` where `formID` = '" + id + "'";
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

	public OperationMessage update(ReceivePO po) {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		if (!this.delete(po.getFormID()).operationResult)
			return result = new OperationMessage(false, "数据库中没有对应表单");
		if (!this.insert(po).operationResult)
			return result = new OperationMessage(false, "更新失败");
		else
			return result;

	}

	public String newID(String unitID) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		int ID_MAX = 0;
		String temp = new Timestamp(System.currentTimeMillis()).toString().substring(0, 10);
		String target = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8);
		target = unitID + target;// 开具单位编号+当天日期
		String selectAll = "select * from `" + Table_Name + "` where `date_and_unit` = '" + target + "'";
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

		return "03" + target + added;
	}

	public OperationMessage clear() {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String clear = "delete from `" + Table_Name + "`";
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

	public ReceivePO getFormPO(String id) throws RemoteException {
		// TODO Auto-generated method stub
		String select = "select * from `" + Table_Name + "` where `formID` = '" + id + "'";
		ResultSet rs = null;
		ReceivePO result = null;
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			result = new ReceivePO(rs.getString("formID"), rs.getString("orderID"), rs.getString("transitID"),
					rs.getTimestamp("date"), rs.getString("depature"), rs.getString("state"));
			result.setFormState(rs.getString("formState"));
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
			return null;
		}
		return result;
	}

	public ArrayList<ReceivePO> getAll() throws RemoteException {
		// TODO Auto-generated method stub
		String selectAll = "select * from `" + Table_Name + "`";
		ResultSet rs = null;
		ReceivePO temp = null;
		ArrayList<ReceivePO> result = new ArrayList<ReceivePO>();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				temp = new ReceivePO(rs.getString("formID"), rs.getString("orderID"), rs.getString("transitID"),
						rs.getTimestamp("date"), rs.getString("depature"), rs.getString("state"));
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
