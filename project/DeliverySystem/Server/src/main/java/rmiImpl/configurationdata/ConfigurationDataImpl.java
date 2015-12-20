package rmiImpl.configurationdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import operation.Operation;
import database.ConnecterHelper;
import database.RMIHelper;
import message.OperationMessage;
import po.configurationdata.City2DPO;
import po.configurationdata.PackPO;
import po.configurationdata.PricePO;
import po.configurationdata.ProportionPO;
import po.configurationdata.SalaryStrategyPO;
import po.configurationdata.enums.PackEnum;
import po.orderdata.DeliverTypeEnum;
import po.systemdata.LogPO;
import rmi.configurationdata.ConfigurationDataService;

/**
 * 目前全是stub
 *
 * @author cxworks 2015/10/25
 */
public class ConfigurationDataImpl extends UnicastRemoteObject implements ConfigurationDataService {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String City2D;
	private String SalaryStrategy;
	private String Pack;
	private String Price;
	private String Proportion;
	private String Warningline;
	private Connection conn = null;
	private PreparedStatement statement = null;

	public ConfigurationDataImpl() throws RemoteException {
		super();
		City2D = "city2d";
		SalaryStrategy = "salary_strategy";
		Pack = "pack";
		Price = "price";
		Proportion = "proportion";
		Warningline = "warningline";
		conn = ConnecterHelper.getConn();
	}

	public Connection getConn() {
		return conn;
	}

	// public static void main(String[] args) throws RemoteException {
	// ConfigurationDataImpl t = new ConfigurationDataImpl();
	// String insert = "insert into `city2d`(name,x,y) " +
	// "values('广州','113.27','23.12')";
	// try {
	// t.statement = t.conn.prepareStatement(insert);
	// t.statement.executeUpdate();
	// } catch (SQLException e) {
	// System.err.println("新建时出错：");
	// e.printStackTrace();
	// }
	// }

	@Override
	public OperationMessage newCity2D(City2DPO po) throws RemoteException {
		OperationMessage result = new OperationMessage();
		String insert = "insert into `" + City2D + "`(name,x,y,ID) " + "values('" + po.getName() + "','" + po.getX()
				+ "','" + po.getY()+ "','" + po.getID() + "')";
		try {
			statement = conn.prepareStatement(insert);
			statement.executeUpdate();
		} catch (SQLException e) {
			result = new OperationMessage(false, "新建时出错：");
			System.err.println("新建时出错：");
			e.printStackTrace();
		}

		// 系统日志
		if (result.operationResult == true)
			RMIHelper.getLogDataService().insert(new LogPO("总经理", Calendar.getInstance(), "新增城市信息" + po.getName()));

		return result;
	}

	@Override
	public OperationMessage deleteCity2D(String name) throws RemoteException {
		OperationMessage result = new OperationMessage();
		String delete = "delete from `" + City2D + "` where `name` = '" + name + "'";
		try {
			statement = conn.prepareStatement(delete);
			statement.executeUpdate();
		} catch (SQLException e) {
			result = new OperationMessage(false, "删除时出错：");
			System.err.println("删除时出错：");
			e.printStackTrace();
		}

		// 系统日志
		if (result.operationResult == true)
			RMIHelper.getLogDataService().insert(new LogPO("总经理", Calendar.getInstance(), "删除城市信息" + name));

		return result;
	}

	@Override
	public OperationMessage modifyCity2D(City2DPO po) throws RemoteException {
		OperationMessage result = new OperationMessage();
		if (!this.deleteCity2D(po.getName()).operationResult)
			return result = new OperationMessage(false, "数据库中没有对应城市");
		if (!this.newCity2D(po).operationResult)
			return result = new OperationMessage(false, "更新失败");
		else
			return result;
	}

	@Override
	public City2DPO getCity2D(String name) throws RemoteException {
		String select = "select * from `" + City2D + "` where `name` = '" + name + "'";
		ResultSet rs = null;
		City2DPO result = null;
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			result = new City2DPO(rs.getString("name"), rs.getDouble("x"), rs.getDouble("y"),rs.getString("ID"));
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
			return null;
		}
		return result;
	}

	@Override
	public ArrayList<City2DPO> getAllCity2D() throws RemoteException {
		String selectAll = "select * from `" + City2D + "`";
		ResultSet rs = null;
		City2DPO temp = null;
		ArrayList<City2DPO> result = new ArrayList<City2DPO>();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				temp = new City2DPO(rs.getString("name"), rs.getDouble("x"), rs.getDouble("y"),rs.getString("ID"));
				result.add(temp);
			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public OperationMessage clearCity2D() throws RemoteException {
		OperationMessage result = new OperationMessage();
		String clear = "delete from `" + City2D + "`";
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

	public ArrayList<SalaryStrategyPO> getSalaryStrategy() throws RemoteException {
		String selectAll = "select * from `" + SalaryStrategy + "`";
		ResultSet rs = null;
		SalaryStrategyPO temp = null;
		ArrayList<SalaryStrategyPO> result = new ArrayList<SalaryStrategyPO>();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				temp = new SalaryStrategyPO(rs.getInt("base"), rs.getInt("commission"), rs.getInt("bonus"),
						rs.getString("staff"));
				result.add(temp);
			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}

		return result;
	}

	private OperationMessage newSalaryStrategy(SalaryStrategyPO po) throws RemoteException {
		OperationMessage result = new OperationMessage();
		String insert = "insert into " + SalaryStrategy + "(staff,base,commission,bonus) " + "values('"
				+ po.getStaff().toString() + "','" + po.getBase() + "','" + po.getCommission() + "','" + po.getBonus()
				+ "')";

		try {
			statement = conn.prepareStatement(insert);
			statement.executeUpdate();
		} catch (SQLException e) {
			result = new OperationMessage(false, "新建时出错");
			e.printStackTrace();
		}

		// 系统日志
		if (result.operationResult == true)
			RMIHelper.getLogDataService().insert(new LogPO("总经理", Calendar.getInstance(), "修改薪水策略"));

		return result;
	}

	public OperationMessage modifySalaryStrategy(SalaryStrategyPO po) throws RemoteException {
		String delete = "delete from `" + SalaryStrategy + "` where `staff` = '" + po.getStaff().toString() + "'";
		try {
			statement = conn.prepareStatement(delete);
			statement.executeUpdate();
		} catch (SQLException e) {
			return this.newSalaryStrategy(po);
		}
		return this.newSalaryStrategy(po);
	}

	public PackPO getPack() throws RemoteException {
		String selectAll = "select * from `" + Pack + "`";
		ResultSet rs = null;
		PackPO result = null;
		HashMap<PackEnum, Double> packPrice = new HashMap();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				switch (rs.getString("name")) {
				case "PAPER":
					packPrice.put(PackEnum.PAPER, rs.getDouble("money"));
					break;
				case "PACKAGE":
					packPrice.put(PackEnum.PACKAGE, rs.getDouble("money"));
					break;
				case "WOOD":
					packPrice.put(PackEnum.WOOD, rs.getDouble("money"));
					break;
				case "OTHER":
					packPrice.put(PackEnum.OTHER, rs.getDouble("money"));
					break;
				}

			}
			result = new PackPO(packPrice);
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}

		return result;
	}

	public OperationMessage modifyPack(PackPO pack) throws RemoteException {
		OperationMessage result = new OperationMessage();
		ArrayList<String> operations = new ArrayList<String>();
		operations.add(
				"update `" + Pack + "` set `money` ='" + pack.getByType(PackEnum.OTHER) + "' where `name` = 'OTHER'");
		operations.add("update `" + Pack + "` set `money` ='" + pack.getByType(PackEnum.PACKAGE)
				+ "' where `name` = 'PACKAGE'");
		operations.add(
				"update `" + Pack + "` set `money` ='" + pack.getByType(PackEnum.PAPER) + "' where `name` = 'PAPER'");
		operations.add(
				"update `" + Pack + "` set `money` ='" + pack.getByType(PackEnum.WOOD) + "' where `name` = 'WOOD'");
		try {
			for (String tmp : operations) {
				statement = conn.prepareStatement(tmp);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			result = new OperationMessage(false, "更新时出错：");
			System.err.println("更新时出错：");
			e.printStackTrace();
		}

		// 系统日志
		if (result.operationResult == true)
			RMIHelper.getLogDataService().insert(new LogPO("总经理", Calendar.getInstance(), "修改包装价格"));

		return result;
	}

	public PricePO getPrice() throws RemoteException {
		String selectAll = "select * from `" + Price + "`";
		ResultSet rs = null;
		PricePO result = null;
		HashMap<DeliverTypeEnum, Integer> price = new HashMap();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				switch (rs.getString("name")) {
				case "SLOW":
					price.put(DeliverTypeEnum.SLOW, rs.getInt("money"));
					break;
				case "NORMAL":
					price.put(DeliverTypeEnum.NORMAL, rs.getInt("money"));
					break;
				case "FAST":
					price.put(DeliverTypeEnum.FAST, rs.getInt("money"));
					break;
				}

			}
			result = new PricePO(price);
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}

		return result;
	}

	public OperationMessage modifyPrice(PricePO price) throws RemoteException {
		OperationMessage result = new OperationMessage();
		ArrayList<String> operations = new ArrayList<String>();
		operations.add("update `" + Price + "` set `money` ='" + price.getByType(DeliverTypeEnum.SLOW)
				+ "' where `name` = 'SLOW'");
		operations.add("update `" + Price + "` set `money` ='" + price.getByType(DeliverTypeEnum.NORMAL)
				+ "' where `name` = 'NORMAL'");
		operations.add("update `" + Price + "` set `money` ='" + price.getByType(DeliverTypeEnum.FAST)
				+ "' where `name` = 'FAST'");
		try {
			for (String tmp : operations) {
				statement = conn.prepareStatement(tmp);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			result = new OperationMessage(false, "更新时出错：");
			System.err.println("更新时出错：");
			e.printStackTrace();
		}

		// 系统日志
		if (result.operationResult == true)
			RMIHelper.getLogDataService().insert(new LogPO("总经理", Calendar.getInstance(), "修改运费价格"));

		return result;
	}

	public ProportionPO getProportion() throws RemoteException {
		String selectAll = "select * from `" + Proportion + "`";
		ResultSet rs = null;
		ProportionPO result = null;
		HashMap<DeliverTypeEnum, Integer> target = new HashMap();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				switch (rs.getString("name")) {
				case "SLOW":
					target.put(DeliverTypeEnum.SLOW, rs.getInt("num"));
					break;
				case "NORMAL":
					target.put(DeliverTypeEnum.NORMAL, rs.getInt("num"));
					break;
				case "FAST":
					target.put(DeliverTypeEnum.FAST, rs.getInt("num"));
					break;
				}

			}
			result = new ProportionPO(target);
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}

		return result;
	}

	public OperationMessage modifyProportion(ProportionPO proportion) throws RemoteException {
		OperationMessage result = new OperationMessage();
		ArrayList<String> operations = new ArrayList<String>();
		operations.add("update `" + Proportion + "` set `num` ='" + proportion.getByType(DeliverTypeEnum.SLOW)
				+ "' where `name` = 'SLOW'");
		operations.add("update `" + Proportion + "` set `num` ='" + proportion.getByType(DeliverTypeEnum.NORMAL)
				+ "' where `name` = 'NORMAL'");
		operations.add("update `" + Proportion + "` set `num` ='" + proportion.getByType(DeliverTypeEnum.FAST)
				+ "' where `name` = 'FAST'");
		try {
			for (String tmp : operations) {
				statement = conn.prepareStatement(tmp);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			result = new OperationMessage(false, "更新时出错：");
			System.err.println("更新时出错：");
			e.printStackTrace();
		}

		// 系统日志
		if (result.operationResult == true)
			RMIHelper.getLogDataService().insert(new LogPO("总经理", Calendar.getInstance(), "修改运费比例"));

		return result;
	}


	@Override
	public OperationMessage newSalaryStrategy(List<SalaryStrategyPO> pos) throws RemoteException {
		OperationMessage result = new OperationMessage();
		for (SalaryStrategyPO tmp : pos) {
			String insert = "insert into `" + SalaryStrategy + "`(staff,base,commission,bonus) " + "values('"
					+ tmp.getStaff() + "','" + tmp.getBase() + "','" + tmp.getBonus() + "','" + tmp.getBonus() + "')";
			try {
				statement = conn.prepareStatement(insert);
				statement.executeUpdate();
			} catch (SQLException e) {
				result = new OperationMessage(false, "新建时出错：");
				System.err.println("新建时出错：");
				e.printStackTrace();
				break;
			}
		}
		return result;
	}

	@Override
	public OperationMessage newPack(PackPO po) throws RemoteException {
		OperationMessage result = new OperationMessage();
		String new1 = "insert into `" + Pack + "`(name,money) " + "values('" + PackEnum.PACKAGE + "','"
				+ po.getByType(PackEnum.PACKAGE) + "')";
		String new2 = "insert into `" + Pack + "`(name,money) " + "values('" + PackEnum.PAPER + "','"
				+ po.getByType(PackEnum.PAPER) + "')";
		String new3 = "insert into `" + Pack + "`(name,money) " + "values('" + PackEnum.WOOD + "','"
				+ po.getByType(PackEnum.WOOD) + "')";
		String new4 = "insert into `" + Pack + "`(name,money) " + "values('" + PackEnum.OTHER + "','"
				+ po.getByType(PackEnum.OTHER) + "')";
		try {
			statement = conn.prepareStatement(new1);
			statement.executeUpdate();
			statement = conn.prepareStatement(new2);
			statement.executeUpdate();
			statement = conn.prepareStatement(new3);
			statement.executeUpdate();
			statement = conn.prepareStatement(new4);
			statement.executeUpdate();
		} catch (SQLException e) {
			result = new OperationMessage(false, "新建时出错：");
			System.err.println("新建时出错：");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public OperationMessage newPrice(PricePO po) throws RemoteException {
		OperationMessage result = new OperationMessage();
		String new1 = "insert into `" + Price + "`(name,money) " + "values('" + DeliverTypeEnum.FAST + "','"
				+ po.getByType(DeliverTypeEnum.FAST) + "')";
		String new2 = "insert into `" + Price + "`(name,money) " + "values('" + DeliverTypeEnum.NORMAL + "','"
				+ po.getByType(DeliverTypeEnum.NORMAL) + "')";
		String new3 = "insert into `" + Price + "`(name,money) " + "values('" + DeliverTypeEnum.SLOW + "','"
				+ po.getByType(DeliverTypeEnum.SLOW) + "')";
		try {
			statement = conn.prepareStatement(new1);
			statement.executeUpdate();
			statement = conn.prepareStatement(new2);
			statement.executeUpdate();
			statement = conn.prepareStatement(new3);
			statement.executeUpdate();
		} catch (SQLException e) {
			result = new OperationMessage(false, "新建时出错：");
			System.err.println("新建时出错：");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public OperationMessage newProportion(ProportionPO po) throws RemoteException {
		OperationMessage result = new OperationMessage();
		String new1 = "insert into `" + Proportion + "`(name,num) " + "values('" + DeliverTypeEnum.FAST + "','"
				+ po.getByType(DeliverTypeEnum.FAST) + "')";
		String new2 = "insert into `" + Proportion + "`(name,num) " + "values('" + DeliverTypeEnum.NORMAL + "','"
				+ po.getByType(DeliverTypeEnum.NORMAL) + "')";
		String new3 = "insert into `" + Proportion + "`(name,num) " + "values('" + DeliverTypeEnum.SLOW + "','"
				+ po.getByType(DeliverTypeEnum.SLOW) + "')";
		try {
			statement = conn.prepareStatement(new1);
			statement.executeUpdate();
			statement = conn.prepareStatement(new2);
			statement.executeUpdate();
			statement = conn.prepareStatement(new3);
			statement.executeUpdate();
		} catch (SQLException e) {
			result = new OperationMessage(false, "新建时出错：");
			System.err.println("新建时出错：");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public double getWarningline(String centerID) throws RemoteException {
		String select = "select * from `" + Warningline + "` where `name` = '" + centerID + "'";
		ResultSet rs = null;
		double result = -1;
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			result = rs.getDouble("value");
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public OperationMessage setWarningline(String centerID, double value) throws RemoteException {
		OperationMessage result = new OperationMessage();
		if (this.getWarningline(centerID) != -1) {
			String operation = "update `" + Warningline + "` set `value` ='" + value + "' where `centerID` = "
					+ centerID + "'";
			try {
				statement = conn.prepareStatement(operation);
				statement.executeUpdate();
			} catch (SQLException e) {
				result = new OperationMessage(false, "更新时出错：");
				System.err.println("更新时出错：");
				e.printStackTrace();
			}
		} else {
			String insert = "insert into `" + Warningline + "`(centerID,value) " + "values('" + centerID + "','"
					+ value + "')";
			try {
				statement = conn.prepareStatement(insert);
				statement.executeUpdate();
			} catch (SQLException e) {
				result = new OperationMessage(false, "更新时出错：");
				System.err.println("更新时出错：");
				e.printStackTrace();
			}
		}
		// 系统日志
		if (result.operationResult == true)
			RMIHelper.getLogDataService().insert(new LogPO("库存管理人员", Calendar.getInstance(), "修改库存警戒比例"));

		return result;
	}

	/* (non-Javadoc)
	 * @see rmi.cachedata.CacheDataService#getLatestVersionID()
	 */
	@Override
	public long getLatestVersionID() throws RemoteException {
		return 0;
	}

	/* (non-Javadoc)
	 * @see rmi.cachedata.CacheDataService#getOperation(long)
	 */
	@Override
	public List<Operation> getOperation(long localVersion)
			throws RemoteException {
		return null;
	}

}
