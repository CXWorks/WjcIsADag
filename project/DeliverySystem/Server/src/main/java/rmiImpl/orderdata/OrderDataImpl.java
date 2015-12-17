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
import java.util.List;

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
import database.ConnecterHelper;

/**
 *
 * @author mx 2015/10/25
 */

public class OrderDataImpl extends CommonData<OrderPO> implements OrderDataService {

	private String Table_Name;
	private Connection conn = null;
	private PreparedStatement statement = null;
	private String today = "";// 格式eg.2015-11-22
	private int ID_MAX;

	public OrderDataImpl() throws RemoteException {
		super();
		Table_Name = "order";
		conn = ConnecterHelper.getConn();

		// 为today和ID_MAX初始化
		this.newID(null);
		ID_MAX--;
	}

	public Connection getConn() {
		return conn;
	}

	public OperationMessage insert(OrderPO po) throws RemoteException {
		OperationMessage result = new OperationMessage();
		String formIDs = "";
		String insert = "insert into `" + Table_Name
				+ "`(formID,formState,nameFrom,nameTo,unitFrom,unitTo,addressFrom,addressTo,"
				+ "phoneNumFrom,phoneNumTo,telNumFrom,telNumTo,goodsNum,goodsName,weight,volume,"
				+ "money,type,targetHallID,FormIDs,goodsType,pack) " + "values('" + po.getFormID() + "','"
				+ po.getFormState().toString() + "','" + po.getNameFrom() + "','" + po.getNameTo() + "','"
				+ po.getUnitFrom() + "','" + po.getUnitTo() + "','" + po.getAddressFrom() + "','" + po.getAddressTo()
				+ "','" + po.getPhoneNumFrom() + "','" + po.getPhoneNumTo() + "','" + po.getTelNumFrom() + "','"
				+ po.getTelNumTo() + "','" + po.getGoodsNum() + "','" + po.getGoodsName() + "','" + po.getWeight()
				+ "','" + po.getVolume() + "','" + po.getMoney() + "','" + po.getType().toString() + "','"
				+ po.getTargetHallID() + "','" + formIDs + "','" + po.getGoodsType() + "','" + po.getPack().toString()
				+ "')";

		try {
			statement = conn.prepareStatement(insert);
			statement.executeUpdate();
		} catch (SQLException e) {
			result = new OperationMessage(false, "新建时出错：");
			System.err.println("新建时出错：");
			e.printStackTrace();
		}

		return result;
	}

	public OperationMessage delete(String id) throws RemoteException {
		OperationMessage result = new OperationMessage();
		String delete = "delete from `" + Table_Name + "` where `formID` = '" + id + "'";
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

	public OperationMessage update(OrderPO po) throws RemoteException {
		OperationMessage result = new OperationMessage();
		if (!this.delete(po.getFormID()).operationResult)
			return result = new OperationMessage(false, "数据库中没有对应表单");
		if (!this.insert(po).operationResult)
			return result = new OperationMessage(false, "更新失败");
		else
			return result;
	}

	public OperationMessage clear() throws RemoteException {
		OperationMessage result = new OperationMessage();
		String delete = "delete from `" + Table_Name + "`";
		try {
			statement = conn.prepareStatement(delete);
			statement.executeUpdate();
		} catch (SQLException e) {
			result = new OperationMessage(false, "清空时出错：");
			System.err.println("清空时出错：");
			e.printStackTrace();
		}
		return result;
	}

	public String newID(String unitID) throws RemoteException {// 成员变量成员持有ID信息
		String selectAll = "select * from `" + Table_Name + "`";
		ResultSet rs = null;
		String temp = new Timestamp(System.currentTimeMillis()).toString().substring(0, 10);
		String date = temp.substring(5, 7) + temp.substring(8);// 当天日期

		// 当前日期与缓存日期一致
		if (temp.equalsIgnoreCase(today)) {
			this.ID_MAX++;
			String added = String.format("%06d", ID_MAX);
			return date + added;
		}

		// 当前日期与缓存日期不一致
		today = temp;
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				temp = rs.getString("formID").substring(0, 4);
				if (date.equalsIgnoreCase(temp))
					ID_MAX = Math.max(ID_MAX, Integer.parseInt(rs.getString("formID").substring(4)));// 最后6位编号
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
		String select = "select * from `" + Table_Name + "` where `formID` = '" + id + "'";
		ResultSet rs = null;
		OrderPO result = null;

		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			ArrayList<String> FormIDs = new ArrayList<String>();
			if (!rs.getString("FormIDs").equalsIgnoreCase("")) {
				FormIDs = new ArrayList<String>(Arrays.asList(rs.getString("FormIDs").split(" ")));
			}
			result = new OrderPO(rs.getString("formID"), rs.getString("nameFrom"), rs.getString("nameTo"),
					rs.getString("unitFrom"), rs.getString("unitTo"), rs.getString("addressFrom"),
					rs.getString("addressTo"), rs.getString("phoneNumFrom"), rs.getString("phoneNumTo"),
					rs.getString("telNumFrom"), rs.getString("telNumTo"), rs.getString("goodsNum"),
					rs.getString("goodsName"), rs.getString("weight"), rs.getString("volume"), rs.getString("money"),
					rs.getString("goodsType"), rs.getString("type"), rs.getString("pack"), FormIDs,
					rs.getString("targetHallID"));
			result.setFormState(rs.getString("formState"));
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
			return null;
		}
		return result;
	}

	public ArrayList<OrderPO> getAll() throws RemoteException {
		String select = "select * from `" + Table_Name + "`";
		ResultSet rs = null;
		OrderPO temp = null;
		ArrayList<OrderPO> result = new ArrayList<OrderPO>();
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			while (rs.next()) {
				ArrayList<String> FormIDs = new ArrayList<String>();
				if (!rs.getString("FormIDs").equalsIgnoreCase("")) {
					FormIDs = new ArrayList<String>(Arrays.asList(rs.getString("FormIDs").split(" ")));
				}
				temp = new OrderPO(rs.getString("formID"), rs.getString("nameFrom"), rs.getString("nameTo"),
						rs.getString("unitFrom"), rs.getString("unitTo"), rs.getString("addressFrom"),
						rs.getString("addressTo"), rs.getString("phoneNumFrom"), rs.getString("phoneNumTo"),
						rs.getString("telNumFrom"), rs.getString("telNumTo"), rs.getString("goodsNum"),
						rs.getString("goodsName"), rs.getString("weight"), rs.getString("volume"),
						rs.getString("money"), rs.getString("goodsType"), rs.getString("type"), rs.getString("pack"),
						FormIDs, rs.getString("targetHallID"));
				;
				temp.setFormState(rs.getString("formState"));
				result.add(temp);
			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public OperationMessage addFormID(String orderID, String formID) throws RemoteException {
		OrderPO po = this.getFormPO(orderID);
		po.addFormID(formID);
		return this.update(po);
	}

}
