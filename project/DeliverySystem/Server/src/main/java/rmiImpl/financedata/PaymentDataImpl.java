package rmiImpl.financedata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Year;
import java.util.ArrayList;

import message.OperationMessage;
import po.financedata.PaymentPO;
import po.receivedata.ReceivePO;
import rmi.financedata.PaymentDataService;
import database.ConnecterHelper;

public class PaymentDataImpl extends UnicastRemoteObject implements PaymentDataService {

	private String Table_Name;
	private Connection conn = null;
	private PreparedStatement statement = null;

	public PaymentDataImpl() throws RemoteException {
		// TODO Auto-generated constructor stub
		super();
		Table_Name = "payment";
		conn = ConnecterHelper.getConn();
	}

	@Override
	public Connection getConn() throws RemoteException {
		// TODO Auto-generated method stub
		return conn;
	}

	@Override
	public OperationMessage insert(PaymentPO po) throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String insert = "insert into `" + Table_Name + "`(formID,formState,date,amount,payerAccID,payerName,"
				+ "payerAccount,receiverAccID,receiverName,receiverAccount,item,note,date_and_unit) " + "values('"
				+ po.getFormID() + "','" + po.getFormState().toString() + "','" + po.getDateForSQL() + "','"
				+ po.getAmount() + "','" + po.getPayerAccID() + "','" + po.getPayerName() + "','" + po.getPayerAccount()
				+ "','" + po.getReceiverAccID() + "','" + po.getReceiverName() + "','" + po.getReceiverAccount()
				+ "','" + po.getItem() + "','" + po.getNote() + "','" + po.getFormID().substring(2, 17) + "')";

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
	public PaymentPO getFormPO(String id) throws RemoteException {
		// TODO Auto-generated method stub
		String select = "select * from `" + Table_Name + "` where `formID` = '" + id + "'";
		ResultSet rs = null;
		PaymentPO result = null;
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			result = new PaymentPO(rs.getString("formID"), rs.getTimestamp("date"), rs.getString("amount"),
					rs.getString("payerAccID"), rs.getString("payerName"), rs.getString("payerAccount"),
					rs.getString("receiverAccID"), rs.getString("receiverName"), rs.getString("receiverAccount"),
					rs.getString("item"), rs.getString("note"));
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

	@Override
	public OperationMessage update(PaymentPO po) throws RemoteException {
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
		ResultSet rs = null;
		int ID_MAX = 0;
		String temp = new Timestamp(System.currentTimeMillis()).toString().substring(0, 10);
		String target = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8);
		target = unitID + target;// 单位编号+当天日期
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

		return "01" + target + added;
	}

	@Override
	public OperationMessage clear() throws RemoteException {
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

	@Override
	public ArrayList<PaymentPO> getAll() throws RemoteException {
		// TODO Auto-generated method stub
		String selectAll = "select * from `" + Table_Name + "`";
		ResultSet rs = null;
		PaymentPO temp = null;
		ArrayList<PaymentPO> result = new ArrayList<PaymentPO>();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				temp = new PaymentPO(rs.getString("formID"), rs.getTimestamp("date"), rs.getString("amount"),
						rs.getString("payerAccID"), rs.getString("payerName"), rs.getString("payerAccount"),
						rs.getString("receiverAccID"), rs.getString("receiverName"), rs.getString("receiverAccount"),
						rs.getString("item"), rs.getString("note"));
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
