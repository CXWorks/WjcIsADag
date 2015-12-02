package rmiImpl.companydata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import message.OperationMessage;
import po.companydata.CenterPO;
import po.memberdata.StaffPO;
import po.memberdata.StaffTypeEnum;
import po.transportdata.CenterOutPO;
import rmi.companydata.CompanyDataCenterService;
import rmi.memberdata.MemberDataService;
import rmiImpl.ConnecterHelper;
import rmiImpl.memberdata.DriverDataImpl;
import rmiImpl.memberdata.StaffDataImpl;

public class CompanyDataCenterImpl extends UnicastRemoteObject implements CompanyDataCenterService {
	private static final long serialVersionUID = 1L;

	private String Table_Name;
	private Connection conn = null;
	private PreparedStatement statement = null;

	public CompanyDataCenterImpl() throws RemoteException {
		// TODO Auto-generated constructor stub
		super();
		Table_Name = "center";
		conn = ConnecterHelper.connSQL(conn);
	}

	public Connection getConn() {
		return conn;
	}

	public ArrayList<CenterPO> getCenter() {
		// TODO Auto-generated method stub
		String select = "select * from `" + Table_Name + "`";
		ResultSet rs = null;
		CenterPO temp = null;
		ArrayList<CenterPO> result = new ArrayList<CenterPO>();
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			while (rs.next()) {
				ArrayList<StaffPO> storeman = new ArrayList<StaffPO>();
				ArrayList<StaffPO> counterman = new ArrayList<StaffPO>();
				ArrayList<String> t1 = new ArrayList<String>(Arrays.asList(rs.getString("storeman").split(" ")));
				ArrayList<String> t2 = new ArrayList<String>(Arrays.asList(rs.getString("counterman").split(" ")));
				MemberDataService staff = new StaffDataImpl();
				for (String tmp : t1) {
					storeman.add(staff.getPerson(tmp));
				}
				for (String tmp : t2) {
					counterman.add(staff.getPerson(tmp));
				}
				temp = new CenterPO(rs.getString("centerID"), rs.getString("city"), storeman, counterman);
				result.add(temp);
				ConnecterHelper.deconnSQL(staff.getConn());
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

	public String newCenterID(String cityID) {
		// TODO Auto-generated method stub
		String selectAll = "select * from `" + Table_Name + "` where `cityID` = '" + cityID + "'";
		ResultSet rs = null;
		int ID_MAX = 0;
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				ID_MAX = Math.max(ID_MAX, Integer.parseInt(rs.getString("centerID").substring(4)));// 最后3位编号
			}
		} catch (SQLException e) {
			System.err.println("访问数据库时出错：");
			e.printStackTrace();
		}

		ID_MAX++;// 将该数字加一
		if (ID_MAX > 999)
			return null;
		String added = String.format("%03d", ID_MAX);

		return cityID + "0" + added;
	}

	public OperationMessage addCenter(CenterPO po) {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String s = "";
		String c = "";
		ArrayList<StaffPO> storeman = po.getStoreman();
		ArrayList<StaffPO> counterman = po.getCounterman();
		for (int i = 0; i < storeman.size(); i++) {
			StaffPO tmp = storeman.get(i);
			if (i != storeman.size() - 1)
				s += tmp.getID() + " ";
			else
				s += tmp.getID();
		}
		for (int i = 0; i < counterman.size(); i++) {
			StaffPO tmp = counterman.get(i);
			if (i != counterman.size() - 1)
				c += tmp.getID() + " ";
			else
				c += tmp.getID();
		}

		String insert = "insert into `" + Table_Name + "`(centerID,city,storeman,counterman,cityID) " + "values('"
				+ po.getCenterID() + "','" + po.getCity() + "','" + s + "','" + c + "','"
				+ po.getCenterID().substring(0, 3) + "')";

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

	public OperationMessage deleteCenter(CenterPO center) {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String delete = "delete from `" + Table_Name + "` where `centerID` = '" + center.getCenterID() + "'";
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

	public OperationMessage modifyCenter(CenterPO center) {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		if (!this.deleteCenter(center).operationResult)
			return result = new OperationMessage(false, "数据库中没有对应中转中心");
		if (!this.addCenter(center).operationResult)
			return result = new OperationMessage(false, "更新失败");
		else
			return result;
	}

}
