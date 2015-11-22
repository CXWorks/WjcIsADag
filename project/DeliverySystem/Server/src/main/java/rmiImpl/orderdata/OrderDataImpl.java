package rmiImpl.orderdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

import message.OperationMessage;
import po.FormEnum;
import po.memberdata.StaffPO;
import po.memberdata.StaffTypeEnum;
import po.orderdata.DeliverTypeEnum;
import po.orderdata.OrderPO;
import po.receivedata.ReceivePO;
import rmi.memberdata.MemberDataService;
import rmi.orderdata.OrderDataService;
import rmiImpl.CommonData;
import rmiImpl.ConnecterHelper;

/**
 * 
 * @author mx 2015/10/25
 */

public class OrderDataImpl extends CommonData<OrderPO> implements
		OrderDataService {

	private String Table_Name;
	private Connection conn = null;
	private PreparedStatement statement = null;

	public OrderDataImpl() throws RemoteException {
		// TODO Auto-generated constructor stub
		super();
		Table_Name = "order";
		conn = ConnecterHelper.connSQL(conn);
	}

	public Connection getConn() {
		return conn;
	}

	public OperationMessage insert(OrderPO po) throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String formIDs = "";
		ArrayList<String> list = po.getFormIDs();
		for (int i = 0; i < list.size(); i++)
			if (i == list.size() - 1)
				formIDs += list.get(i);
			else
				formIDs += list.get(i) + " ";
		;
		String insert = "insert into "
				+ Table_Name
				+ "(formID,formState,nameFrom,nameTo,unitFrom,unitTo,phoneNumFrom,"
				+ "phoneNumTo,telNumFrom,telNumTo,goodsNum,goodsName,weight,volume,"
				+ "money,type,targetHallID,FormIDs) " + "values('"
				+ po.getFormID() + "','" + po.getFormState().toString() + "','"
				+ po.getNameFrom() + "','" + po.getNameTo() + "','"
				+ po.getUnitFrom() + "','" + po.getUnitTo() + "','"
				+ po.getPhoneNumFrom() + "','" + po.getPhoneNumTo() + "','"
				+ po.getTelNumFrom() + "','" + po.getTelNumTo() + "','"
				+ po.getGoodsNum() + "','" + po.getGoodsName() + "','"
				+ po.getWeight() + "','" + po.getVolume() + "','"
				+ po.getMoney() + "','" + po.getType().toString() + "','"
				+ po.getTargetHallID() + "','" + formIDs;

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

	public OperationMessage update(OrderPO po) throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		if (!this.delete(po.getFormID()).operationResult)
			return result = new OperationMessage(false, "数据库中没有对应表单");
		if (!this.insert(po).operationResult)
			return result = new OperationMessage(false, "更新失败");
		else
			return result;
	}

	public OperationMessage clear() throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String delete = "delete from " + Table_Name;
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

	public String newID(String unitID) throws RemoteException {
		// TODO Auto-generated method stub
		String selectAll = "select * from " + Table_Name;
		ResultSet rs = null;
		int ID_MAX = 0;
		String temp = new Timestamp(System.currentTimeMillis()).toString()
				.substring(0, 10);
		String date = temp.substring(5, 7) + temp.substring(8);// 当天日期
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				temp = rs.getString("formID").substring(0, 4);
				if (date.equalsIgnoreCase(temp))
					ID_MAX = Math.max(ID_MAX, Integer.parseInt(rs.getString(
							"formID").substring(4)));// 最后6位编号
			}
		} catch (SQLException e) {
			System.err.println("访问数据库时出错：");
			e.printStackTrace();
		}

		ID_MAX++;// 将该数字加一
		if (ID_MAX > 999999)
			return null;
		String added = String.format("%06d", ID_MAX);

		return date + added;
	}

	public OrderPO getFormPO(String id) throws RemoteException {
		// TODO Auto-generated method stub
		String select = "select * from " + Table_Name + " where formID= '" + id
				+ "'";
		ResultSet rs = null;
		OrderPO result = null;
		ArrayList<String> FormIDs = null;

		try {
			FormIDs = (ArrayList<String>) Arrays.asList(rs.getString("FormIDs")
					.split(" "));
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			result = new OrderPO(rs.getString("nameFrom"),
					rs.getString("nameTo"), rs.getString("unitFrom"),
					rs.getString("unitTo"), rs.getString("phoneNumFrom"),
					rs.getString("phoneNumTo"), rs.getString("telNumFrom"),
					rs.getString("telNumTo"), rs.getString("goodsNum"),
					rs.getString("goodsName"), rs.getString("weight"),
					rs.getString("volume"), rs.getString("money"),
					rs.getString("type"), FormIDs, rs.getString("targetHallID"));
			result.setFormType(FormEnum.ORDER);
			result.setFormID(rs.getString("formID"));
			result.setFormState(rs.getString("formState"));
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<OrderPO> getAll() throws RemoteException {
		// TODO Auto-generated method stub
		String select = "select * from " + Table_Name;
		ResultSet rs = null;
		OrderPO temp = null;
		ArrayList<String> FormIDs = null;
		ArrayList<OrderPO> result = new ArrayList<OrderPO>();
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			while (rs.next()) {
				FormIDs = (ArrayList<String>) Arrays.asList(rs.getString(
						"FormIDs").split(" "));
				temp = new OrderPO(rs.getString("nameFrom"),
						rs.getString("nameTo"), rs.getString("unitFrom"),
						rs.getString("unitTo"), rs.getString("phoneNumFrom"),
						rs.getString("phoneNumTo"), rs.getString("telNumFrom"),
						rs.getString("telNumTo"), rs.getString("goodsNum"),
						rs.getString("goodsName"), rs.getString("weight"),
						rs.getString("volume"), rs.getString("money"),
						rs.getString("type"), FormIDs,
						rs.getString("targetHallID"));
				temp.setFormType(FormEnum.ORDER);
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
