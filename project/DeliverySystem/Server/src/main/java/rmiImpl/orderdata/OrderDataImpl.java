package rmiImpl.orderdata;

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
import po.orderdata.OrderPO;
import rmi.orderdata.OrderDataService;
import rmiImpl.CommonData;

/**
 *
 * @author mx 2015/10/25
 */

public class OrderDataImpl extends CommonData<OrderPO> implements OrderDataService {

	private Connection conn = null;
	private PreparedStatement statement = null;
	private String today = "";// 格式eg.2015-11-22
	private int ID_MAX;

	public OrderDataImpl() throws RemoteException {
		super();
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
		ArrayList<String> list = po.getFormIDs();
		for (int i = 0; i < list.size(); i++) {
			if (i != list.size() - 1) {
				formIDs = formIDs + list.get(i) + " ";
			} else {
				formIDs = formIDs + list.get(i);
			}
		}
		final String final_IDs = formIDs;
		String insert = MySql.insert(TableEnum.ORDER, new HashMap<String, String>() {
			{
				put("formID", po.getFormID());
				put("formState", po.getFormState().toString());
				put("nameFrom", po.getNameFrom());
				put("nameTo", po.getNameTo());
				put("unitFrom", po.getUnitFrom());
				put("unitTo", po.getUnitTo());
				put("addressFrom", po.getAddressFrom());
				put("addressTo", po.getAddressTo());
				put("phoneNumFrom", po.getPhoneNumFrom());
				put("phoneNumTo", po.getPhoneNumTo());
				put("telNumFrom", po.getTelNumFrom());
				put("telNumTo", po.getTelNumTo());
				put("goodsNum", po.getGoodsNum());
				put("goodsName", po.getGoodsName());
				put("weight", po.getWeight());
				put("volume", po.getVolume());
				put("money", po.getMoney());
				put("type", po.getType().toString());
				put("targetHallID", po.getTargetHallID());
				put("FormIDs", final_IDs);
				put("goodsType", po.getGoodsType());
				put("pack", po.getPack().toString());
			}
		});
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
		String delete = MySql.delete(TableEnum.ORDER, new HashMap<String, String>() {
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
		String clear = MySql.delete(TableEnum.ORDER, null);
		try {
			statement = conn.prepareStatement(clear);
			statement.executeUpdate();
		} catch (SQLException e) {
			result = new OperationMessage(false, "清空时出错：");
			System.err.println("清空时出错：");
			e.printStackTrace();
		}
		return result;
	}

	public String newID(String unitID) throws RemoteException {// 成员变量成员持有ID信息
		String selectAll = MySql.select(TableEnum.ORDER, null);
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
		String select = MySql.select(TableEnum.ORDER, new HashMap<String, String>() {
			{
				put("formID", id);
			}
		});
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
		String select = MySql.select(TableEnum.ORDER, null);
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

	@Override
	public OperationMessage setFinished(String orderID) throws RemoteException {
		OperationMessage result = new OperationMessage();
		String setFinished = MySql.update(TableEnum.ORDER, "finished", "1", new HashMap<String, String>() {
			{
				put("formID", orderID);
			}
		});
		try {
			statement = conn.prepareStatement(setFinished);
			statement.executeUpdate();
		} catch (SQLException e) {
			result = new OperationMessage(false, "修改order时时出错：");
			System.err.println("修改order时时出错：");
			e.printStackTrace();
		}
		return result;
	}

}
