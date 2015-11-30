package rmiImpl.configurationdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import message.OperationMessage;
import po.companydata.CarPO;
import po.configurationdata.City2DPO;
import po.configurationdata.CityDistancePO;
import po.configurationdata.PackPO;
import po.configurationdata.PricePO;
import po.configurationdata.ProportionPO;
import po.configurationdata.SalaryStrategyPO;
import po.configurationdata.enums.PackEnum;
import po.financedata.PaymentPO;
import po.orderdata.DeliverTypeEnum;
import po.receivedata.ReceivePO;
import rmi.configurationdata.ConfigurationDataService;
import rmiImpl.ConnecterHelper;

/**
 * 目前全是stub
 * 
 * @author cxworks 2015/10/25
 */
public class ConfigurationDataImpl extends UnicastRemoteObject implements
		ConfigurationDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String City2D;
	private String SalaryStrategy;
	private String Pack;
	private String Price;
	private String Proportion;
	private Connection conn = null;
	private PreparedStatement statement = null;

	public ConfigurationDataImpl() throws RemoteException {
		// TODO Auto-generated constructor stub
		super();
		City2D = "city2d";
		SalaryStrategy = "salary_strategy";
		Pack = "pack";
		Price = "price";
		Proportion = "proportion";
		conn = ConnecterHelper.connSQL(conn);
	}

	public Connection getConn() {
		return conn;
	}

	@Override
	public OperationMessage newCity2D(City2DPO po) throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String insert = "insert into `" + City2D + "`(name,x,data,y) "
				+ "values('" + po.getName() + "','" + po.getX() + "','"
				+ po.getY() + "')";
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
	public OperationMessage deleteCity2D(String name) throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String delete = "delete from `" + City2D + "` where `name` = '" + name + "'";
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
	public OperationMessage modifyCity2D(City2DPO po) throws RemoteException {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		String select = "select * from `" + City2D + "` where `name` = '" + name
				+ "'";
		ResultSet rs = null;
		City2DPO result = null;
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			result = new City2DPO(rs.getString("name"), rs.getDouble("x"),
					rs.getDouble("y"));
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
			return null;
		}
		return result;
	}

	@Override
	public ArrayList<City2DPO> getAllCity2D() throws RemoteException {
		// TODO Auto-generated method stub
		String selectAll = "select * from `" + City2D + "`";
		ResultSet rs = null;
		City2DPO temp = null;
		ArrayList<City2DPO> result = new ArrayList<City2DPO>();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				temp = new City2DPO(rs.getString("name"), rs.getDouble("x"),
						rs.getDouble("y"));
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
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String clear = "delete from `" + City2D + "`";
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

	public ArrayList<SalaryStrategyPO> getSalaryStrategy()
			throws RemoteException {
		// TODO Auto-generated method stub
		String selectAll = "select * from `" + SalaryStrategy + "`";
		ResultSet rs = null;
		SalaryStrategyPO temp = null;
		ArrayList<SalaryStrategyPO> result = new ArrayList<SalaryStrategyPO>();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				temp = new SalaryStrategyPO(rs.getInt("base"),
						rs.getInt("commission"), rs.getInt("bonus"),
						rs.getString("staff"));
				result.add(temp);
			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}

		return result;
	}

	public OperationMessage modifySalaryStrategy(SalaryStrategyPO po)
			throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String delete = "delete from `" + SalaryStrategy + "` where `staff` = '"
				+ po.getStaff().toString() + "'";
		try {
			statement = conn.prepareStatement(delete);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = new OperationMessage(false, "数据库中没有对应信息");
			e.printStackTrace();
			return result;
		}
		String insert = "insert into " + SalaryStrategy
				+ "(staff,base,commission,bonus) " + "values('"
				+ po.getStaff().toString() + "','" + po.getBase() + "','"
				+ po.getCommission() + "','" + po.getBonus() + "')";

		try {
			statement = conn.prepareStatement(insert);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = new OperationMessage(false, "修改的信息有误");
			e.printStackTrace();
		}

		return result;
	}

	public PackPO getPack() throws RemoteException {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		ArrayList<String> operations = new ArrayList<String>();
		operations.add("update `" + Pack + "` set `money` ='"
				+ pack.getByType(PackEnum.OTHER) + "' where `name` = 'OTHER'");
		operations.add("update `" + Pack + "` set `money` ='"
				+ pack.getByType(PackEnum.PACKAGE) + "' where `name` = 'PACKAGE'");
		operations.add("update `" + Pack + "` set `money` ='"
				+ pack.getByType(PackEnum.PAPER) + "' where `name` = 'PAPER'");
		operations.add("update `" + Pack + "` set `money` ='"
				+ pack.getByType(PackEnum.WOOD) + "' where `name` = 'WOOD'");
		try {
			for (String tmp : operations) {
				statement = conn.prepareStatement(tmp);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = new OperationMessage(false, "更新时出错：");
			System.err.println("更新时出错：");
			e.printStackTrace();
		}
		return result;
	}

	public PricePO getPrice() throws RemoteException {
		// TODO Auto-generated method stub
		String selectAll = "select * from `" + Price + "`";
		ResultSet rs = null;
		PricePO result = null;
		HashMap<DeliverTypeEnum, Integer> price = new HashMap();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				switch (rs.getString("name")) {
				case "ECONOMIC":
					price.put(DeliverTypeEnum.SLOW, rs.getInt("money"));
					break;
				case "USUAL":
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
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		ArrayList<String> operations = new ArrayList<String>();
		operations.add("update `" + Price + "` set `money` ='"
				+ price.getByType(DeliverTypeEnum.SLOW)
				+ "' where `name` = 'ECONOMIC'");
		operations.add("update `" + Price + "` set `money` ='"
				+ price.getByType(DeliverTypeEnum.NORMAL)
				+ "' where `name` = 'USUAL'");
		operations.add("update `" + Price + "` set `money` ='"
				+ price.getByType(DeliverTypeEnum.FAST)
				+ "' where `name` = 'FAST'");
		try {
			for (String tmp : operations) {
				statement = conn.prepareStatement(tmp);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = new OperationMessage(false, "更新时出错：");
			System.err.println("更新时出错：");
			e.printStackTrace();
		}
		return result;
	}

	public ProportionPO getProportion() throws RemoteException {
		// TODO Auto-generated method stub
		String selectAll = "select * from `" + Proportion + "`";
		ResultSet rs = null;
		ProportionPO result = null;
		HashMap<DeliverTypeEnum, Integer> target = new HashMap();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				switch (rs.getString("name")) {
				case "ECONOMIC":
					target.put(DeliverTypeEnum.SLOW, rs.getInt("num"));
					break;
				case "USUAL":
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

	public OperationMessage modifyProportion(ProportionPO proportion)
			throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		ArrayList<String> operations = new ArrayList<String>();
		operations.add("update `" + Proportion + "` set `num` ='"
				+ proportion.getByType(DeliverTypeEnum.SLOW)
				+ "' where `name` = 'ECONOMIC'");
		operations.add("update `" + Proportion + "` set `num` ='"
				+ proportion.getByType(DeliverTypeEnum.NORMAL)
				+ "' where `name` = 'USUAL'");
		operations.add("update `" + Proportion + "` set `num` ='"
				+ proportion.getByType(DeliverTypeEnum.FAST)
				+ "' where `name` = 'FAST'");
		try {
			for (String tmp : operations) {
				statement = conn.prepareStatement(tmp);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = new OperationMessage(false, "更新时出错：");
			System.err.println("更新时出错：");
			e.printStackTrace();
		}
		return result;
	}

	public Object getInstitutionDistance() throws RemoteException {
		// TODO Auto-generated method stub
		return new Object();
	}

	public OperationMessage modifyInstitutionDistance(String ID, Object ob)
			throws RemoteException {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public Object[] newInstitutionDistanceSearch(String ID)
			throws RemoteException {
		// TODO Auto-generated method stub
		Object[] stub = new Object[2];
		return stub;
	}

	public OperationMessage newInstitutionDistanceInsert(String ID, Object[] ob)
			throws RemoteException {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

}
