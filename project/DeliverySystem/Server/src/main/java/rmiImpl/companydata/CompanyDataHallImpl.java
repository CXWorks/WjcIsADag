package rmiImpl.companydata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import message.OperationMessage;
import po.companydata.CenterPO;
import po.companydata.HallPO;
import po.memberdata.DriverPO;
import po.memberdata.StaffPO;
import po.systemdata.LogPO;
import rmi.companydata.CompanyDataHallService;
import rmi.memberdata.MemberDataService;
import database.ConnecterHelper;
import database.RMIHelper;
import rmiImpl.memberdata.DriverDataImpl;
import rmiImpl.memberdata.StaffDataImpl;

public class CompanyDataHallImpl extends UnicastRemoteObject implements CompanyDataHallService {
	private static final long serialVersionUID = 1L;

	private String Table_Name;
	private Connection conn = null;
	private PreparedStatement statement = null;

	public CompanyDataHallImpl() throws RemoteException {
		super();
		Table_Name = "hall";
		conn = ConnecterHelper.getConn();
	}

	public Connection getConn() {
		return conn;
	}

	public ArrayList<HallPO> getHall() {
		// TODO Auto-generated method stub
		String select = "select * from `" + Table_Name + "`";
		ResultSet rs = null;
		HallPO temp = null;
		ArrayList<HallPO> result = new ArrayList<HallPO>();
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			while (rs.next()) {
				ArrayList<DriverPO> driver = new ArrayList<DriverPO>();
				ArrayList<StaffPO> deliver = new ArrayList<StaffPO>();
				ArrayList<StaffPO> counterman = new ArrayList<StaffPO>();
				ArrayList<String> t1;
				ArrayList<String> t2;
				ArrayList<String> t3;
				MemberDataService<StaffPO> s1 = new StaffDataImpl();
				MemberDataService<DriverPO> s2 = new DriverDataImpl();
				t1 = new ArrayList<String>(Arrays.asList(rs.getString("driver").split(" ")));
				t2 = new ArrayList<String>(Arrays.asList(rs.getString("deliver").split(" ")));
				t3 = new ArrayList<String>(Arrays.asList(rs.getString("counterman").split(" ")));
				if (!rs.getString("driver").equalsIgnoreCase("")) {
					t1 = new ArrayList<String>(Arrays.asList(rs.getString("driver").split(" ")));
					for (String tmp : t1) {
						driver.add(s2.getPerson(tmp));
					}
				}
				if (!rs.getString("deliver").equalsIgnoreCase("")) {
					t2 = new ArrayList<String>(Arrays.asList(rs.getString("deliver").split(" ")));
					for (String tmp : t2) {
						deliver.add(s1.getPerson(tmp));
					}
				}
				if (!rs.getString("counterman").equalsIgnoreCase("")) {
					t3 = new ArrayList<String>(Arrays.asList(rs.getString("counterman").split(" ")));
					for (String tmp : t3) {
						counterman.add(s1.getPerson(tmp));
					}
				}
				temp = new HallPO(rs.getString("hallID"), rs.getString("city"), rs.getString("area"), driver, deliver,
						counterman, rs.getString("nearCenterID"));
				result.add(temp);
			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		} catch (RemoteException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}
		return result;
	}

	public OperationMessage addHall(HallPO po) throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String dr = "";
		String de = "";
		String co = "";
		ArrayList<DriverPO> driver = po.getDriver();
		ArrayList<StaffPO> deliver = po.getDeliver();
		ArrayList<StaffPO> counterman = po.getCounterman();
		for (int i = 0; i < driver.size(); i++) {
			DriverPO tmp = driver.get(i);
			if (i != driver.size() - 1)
				dr += tmp.getID() + " ";
			else
				dr += tmp.getID();
		}
		for (int i = 0; i < deliver.size(); i++) {
			StaffPO tmp = deliver.get(i);
			if (i != deliver.size() - 1)
				de += tmp.getID() + " ";
			else
				de += tmp.getID();
		}
		for (int i = 0; i < counterman.size(); i++) {
			StaffPO tmp = counterman.get(i);
			if (i != counterman.size() - 1)
				co += tmp.getID() + " ";
			else
				co += tmp.getID();
		}
		String insert = "insert into `" + Table_Name
				+ "`(hallID,city,area,driver,deliver,counterman,nearCenterID,cityID) " + "values('" + po.getHallID()
				+ "','" + po.getCity() + "','" + po.getArea() + "','" + dr + "','" + de + "','" + co + "','"
				+ po.getNearCenterID() + "','" + po.getCity().substring(0, 3) + "')";

		try {
			statement = conn.prepareStatement(insert);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = new OperationMessage(false, "新建时出错：");
			System.err.println("新建时出错：");
			e.printStackTrace();
		}

		//系统日志
		if(result.operationResult==true)
			RMIHelper.getLogDataService().insert(new LogPO("总经理", Calendar.getInstance(), "新建营业厅:" + po.getHallID()));

		return result;
	}

	public OperationMessage deleteHall(HallPO hall) throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String delete = "delete from `" + Table_Name + "` where `hallID` = '" + hall.getHallID() + "'";
		try {
			statement = conn.prepareStatement(delete);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = new OperationMessage(false, "删除时出错：");
			System.err.println("删除时出错：");
			e.printStackTrace();
		}

		//系统日志
		if(result.operationResult==true)
			RMIHelper.getLogDataService().insert(new LogPO("总经理", Calendar.getInstance(), "删除营业厅:" + hall.getHallID()));

		return result;
	}

	public OperationMessage modifyHall(HallPO hall) throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		if (!this.deleteHall(hall).operationResult)
			return result = new OperationMessage(false, "数据库中没有对应营业厅");
		if (!this.addHall(hall).operationResult)
			return result = new OperationMessage(false, "更新失败");
		else
			return result;
	}

	public String newHallID(String cityID) {
		// TODO Auto-generated method stub
		String selectAll = "select * from `" + Table_Name + "` where `cityID` = '" + cityID + "'";
		ResultSet rs = null;
		int ID_MAX = 0;
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				ID_MAX = Math.max(ID_MAX, Integer.parseInt(rs.getString("hallID").substring(4)));// 最后3位编号
			}
		} catch (SQLException e) {
			System.err.println("访问数据库时出错：");
			e.printStackTrace();
		}

		ID_MAX++;// 将该数字加一
		if (ID_MAX > 999)
			return null;
		String added = String.format("%03d", ID_MAX);

		return cityID + "1" + added;
	}

}
