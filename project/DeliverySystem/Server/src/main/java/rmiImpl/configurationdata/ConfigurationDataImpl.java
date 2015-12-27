package rmiImpl.configurationdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import database.ConnecterHelper;
import database.MySql;
import database.enums.TableEnum;
import message.OperationMessage;
import po.configurationdata.City2DPO;
import po.configurationdata.PackPO;
import po.configurationdata.PricePO;
import po.configurationdata.ProportionPO;
import po.configurationdata.SalaryStrategyPO;
import po.configurationdata.enums.PackEnum;
import po.orderdata.DeliverTypeEnum;
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

	private Connection conn = null;
	private PreparedStatement statement = null;

	public ConfigurationDataImpl() throws RemoteException {
		super();
		conn = ConnecterHelper.getConn();
	}

	public Connection getConn() {
		return conn;
	}

	@Override
	public OperationMessage newCity2D(City2DPO po) throws RemoteException {
		OperationMessage result = new OperationMessage();
		String insert = MySql.insert(TableEnum.CITY2D, new HashMap<String, String>() {
			{
				put("name", po.getName());
				put("x", po.getX() + "");
				put("y", po.getY() + "");
				put("ID", po.getID());
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

	@Override
	public OperationMessage deleteCity2D(String name) throws RemoteException {
		OperationMessage result = new OperationMessage();
		String delete = MySql.delete(TableEnum.CITY2D, new HashMap<String, String>() {
			{
				put("name", name);
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
		String select = MySql.select(TableEnum.CITY2D, new HashMap<String, String>() {
			{
				put("name", name);
			}
		});
		ResultSet rs = null;
		City2DPO result = null;
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			result = new City2DPO(rs.getString("name"), rs.getDouble("x"), rs.getDouble("y"), rs.getString("ID"));
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
			return null;
		}
		return result;
	}

	@Override
	public ArrayList<City2DPO> getAllCity2D() throws RemoteException {
		String selectAll = MySql.select(TableEnum.CITY2D, null);
		ResultSet rs = null;
		City2DPO temp = null;
		ArrayList<City2DPO> result = new ArrayList<City2DPO>();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				temp = new City2DPO(rs.getString("name"), rs.getDouble("x"), rs.getDouble("y"), rs.getString("ID"));
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
		String clear = MySql.delete(TableEnum.CITY2D, null);
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
		String selectAll = MySql.select(TableEnum.SALARY_STRATEGY, null);
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
		String insert = MySql.insert(TableEnum.SALARY_STRATEGY, new HashMap<String, String>() {
			{
				put("staff", po.getStaff().toString());
				put("base", po.getBase() + "");
				put("commission", po.getCommission() + "");
				put("bonus", po.getBonus() + "");
			}
		});

		try {
			statement = conn.prepareStatement(insert);
			statement.executeUpdate();
		} catch (SQLException e) {
			result = new OperationMessage(false, "新建时出错");
			e.printStackTrace();
		}

		return result;
	}

	public OperationMessage modifySalaryStrategy(SalaryStrategyPO po) throws RemoteException {
		String delete = MySql.delete(TableEnum.SALARY_STRATEGY, new HashMap<String, String>() {
			{
				put("staff", po.getStaff().toString());
			}
		});
		try {
			statement = conn.prepareStatement(delete);
			statement.executeUpdate();
		} catch (SQLException e) {
			return this.newSalaryStrategy(po);
		}
		return this.newSalaryStrategy(po);
	}

	public PackPO getPack() throws RemoteException {
		String selectAll = MySql.select(TableEnum.PACK, null);
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
		operations.add(MySql.update(TableEnum.PACK, "money", pack.getByType(PackEnum.OTHER) + "",
				new HashMap<String, String>() {
					{
						put("name", "OTHER");
					}
				}));
		operations.add(MySql.update(TableEnum.PACK, "money", pack.getByType(PackEnum.PACKAGE) + "",
				new HashMap<String, String>() {
					{
						put("name", "PACKAGE");
					}
				}));
		operations.add(MySql.update(TableEnum.PACK, "money", pack.getByType(PackEnum.PAPER) + "",
				new HashMap<String, String>() {
					{
						put("name", "PAPER");
					}
				}));
		operations.add(MySql.update(TableEnum.PACK, "money", pack.getByType(PackEnum.WOOD) + "",
				new HashMap<String, String>() {
					{
						put("name", "WOOD");
					}
				}));
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

		return result;
	}

	public PricePO getPrice() throws RemoteException {
		String selectAll = MySql.select(TableEnum.PRICE, null);
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
		operations.add(MySql.update(TableEnum.PRICE, "money", price.getByType(DeliverTypeEnum.FAST) + "",
				new HashMap<String, String>() {
					{
						put("name", "FAST");
					}
				}));
		operations.add(MySql.update(TableEnum.PRICE, "money", price.getByType(DeliverTypeEnum.NORMAL) + "",
				new HashMap<String, String>() {
					{
						put("name", "NORMAL");
					}
				}));
		operations.add(MySql.update(TableEnum.PRICE, "money", price.getByType(DeliverTypeEnum.SLOW) + "",
				new HashMap<String, String>() {
					{
						put("name", "SLOW");
					}
				}));
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

		return result;
	}

	public ProportionPO getProportion() throws RemoteException {
		String selectAll = MySql.select(TableEnum.PROPORTION, null);
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
		operations.add(MySql.update(TableEnum.PROPORTION, "num", proportion.getByType(DeliverTypeEnum.FAST) + "",
				new HashMap<String, String>() {
					{
						put("name", "FAST");
					}
				}));
		operations.add(MySql.update(TableEnum.PROPORTION, "num", proportion.getByType(DeliverTypeEnum.NORMAL) + "",
				new HashMap<String, String>() {
					{
						put("name", "NORMAL");
					}
				}));
		operations.add(MySql.update(TableEnum.PROPORTION, "num", proportion.getByType(DeliverTypeEnum.SLOW) + "",
				new HashMap<String, String>() {
					{
						put("name", "SLOW");
					}
				}));
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

		return result;
	}

	@Override
	public OperationMessage newSalaryStrategy(List<SalaryStrategyPO> pos) throws RemoteException {
		OperationMessage result = new OperationMessage();
		for (SalaryStrategyPO tmp : pos) {
			String insert = MySql.insert(TableEnum.SALARY_STRATEGY, new HashMap<String, String>() {
				{
					put("staff", tmp.getStaff().toString());
					put("base", tmp.getBase() + "");
					put("commission", tmp.getCommission() + "");
					put("bonus", tmp.getBonus() + "");
				}
			});
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
		String new1 = MySql.insert(TableEnum.PACK, new HashMap<String, String>() {
			{
				put("name", PackEnum.PACKAGE + "");
				put("money", po.getByType(PackEnum.PACKAGE) + "");
			}
		});
		String new2 = MySql.insert(TableEnum.PACK, new HashMap<String, String>() {
			{
				put("name", PackEnum.PAPER + "");
				put("money", po.getByType(PackEnum.PAPER) + "");
			}
		});
		String new3 = MySql.insert(TableEnum.PACK, new HashMap<String, String>() {
			{
				put("name", PackEnum.WOOD + "");
				put("money", po.getByType(PackEnum.WOOD) + "");
			}
		});
		String new4 = MySql.insert(TableEnum.PACK, new HashMap<String, String>() {
			{
				put("name", PackEnum.OTHER + "");
				put("money", po.getByType(PackEnum.OTHER) + "");
			}
		});
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
		String new1 = MySql.insert(TableEnum.PRICE, new HashMap<String, String>() {
			{
				put("name", DeliverTypeEnum.FAST + "");
				put("money", po.getByType(DeliverTypeEnum.FAST) + "");
			}
		});
		String new2 = MySql.insert(TableEnum.PRICE, new HashMap<String, String>() {
			{
				put("name", DeliverTypeEnum.NORMAL + "");
				put("money", po.getByType(DeliverTypeEnum.NORMAL) + "");
			}
		});
		String new3 = MySql.insert(TableEnum.PRICE, new HashMap<String, String>() {
			{
				put("name", DeliverTypeEnum.SLOW + "");
				put("money", po.getByType(DeliverTypeEnum.SLOW) + "");
			}
		});
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
		String new1 = MySql.insert(TableEnum.PROPORTION, new HashMap<String, String>() {
			{
				put("name", DeliverTypeEnum.SLOW + "");
				put("num", po.getByType(DeliverTypeEnum.SLOW) + "");
			}
		});
		String new2 = MySql.insert(TableEnum.PROPORTION, new HashMap<String, String>() {
			{
				put("name", DeliverTypeEnum.FAST + "");
				put("num", po.getByType(DeliverTypeEnum.FAST) + "");
			}
		});
		String new3 = MySql.insert(TableEnum.PROPORTION, new HashMap<String, String>() {
			{
				put("name", DeliverTypeEnum.NORMAL + "");
				put("num", po.getByType(DeliverTypeEnum.NORMAL) + "");
			}
		});
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
		String select = MySql.select(TableEnum.WARNINGLINE, new HashMap<String, String>() {
			{
				put("name", centerID);
			}
		});
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
			String operation = MySql.update(TableEnum.WARNINGLINE, "value", value + "", new HashMap<String, String>() {
				{
					put("name", centerID);
				}
			});
			try {
				statement = conn.prepareStatement(operation);
				statement.executeUpdate();
			} catch (SQLException e) {
				result = new OperationMessage(false, "更新时出错：");
				System.err.println("更新时出错：");
				e.printStackTrace();
			}
		} else {
			String insert = MySql.insert(TableEnum.WARNINGLINE, new HashMap<String, String>() {
				{
					put("name", centerID);
					put("value", value + "");
				}
			});
			try {
				statement = conn.prepareStatement(insert);
				statement.executeUpdate();
			} catch (SQLException e) {
				result = new OperationMessage(false, "更新时出错：");
				System.err.println("更新时出错：");
				e.printStackTrace();
			}
		}

		return result;
	}

}
