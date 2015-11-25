package rmiImpl.storedata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.tools.DocumentationTool.Location;

import po.receivedata.ReceivePO;
import message.OperationMessage;
import model.store.StoreArea;
import model.store.StoreAreaCode;
import model.store.StoreLocation;
import model.store.StoreModel;
import model.store.StoreModelOperation;
import rmi.storedata.StoreModelDataService;
import rmiImpl.ConnecterHelper;

public class StoreModelDataImpl extends UnicastRemoteObject implements
		StoreModelDataService {

	private String Table_Name = "";
	private Connection conn = null;
	private PreparedStatement statement = null;
	/*一个架子中position的数量*/
	private final static int NUM = 50;

	protected StoreModelDataImpl() throws RemoteException {
		// TODO Auto-generated constructor stub
		super();
		conn = ConnecterHelper.connSQL(conn);
	}

	@Override
	public Connection getConn() throws RemoteException {
		// TODO Auto-generated method stub
		return conn;
	}

	public void setTableName(StoreAreaCode code) throws RemoteException {
		// TODO Auto-generated method stub
		if (code.equals(StoreAreaCode.AIR))
			Table_Name = "store_model_air";
		else if (code.equals(StoreAreaCode.RAIL))
			Table_Name = "store_model_rail";
		else if (code.equals(StoreAreaCode.ROAD))
			Table_Name = "store_model_road";
		else if (code.equals(StoreAreaCode.FLEX))
			Table_Name = "store_model_flex";
		return;
	}

	@Override
	public StoreArea getArea(StoreAreaCode code) throws RemoteException {
		// TODO Auto-generated method stub
		this.setTableName(code);
		String selectAll = "select * from " + Table_Name;
		ResultSet rs = null;
		StoreArea result = null;
		StoreLocation tmp = null;
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				tmp = new StoreLocation(code, rs.getInt("row"),
						rs.getInt("shelf"), rs.getInt("position"),
						rs.getString("orderID"));
			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public StoreModel getModel() throws RemoteException {
		// TODO Auto-generated method stub
		return new StoreModel(this.getArea(StoreAreaCode.AIR),
				this.getArea(StoreAreaCode.RAIL),
				this.getArea(StoreAreaCode.ROAD),
				this.getArea(StoreAreaCode.FLEX));
	}

	@Override
	public OperationMessage newShelf(StoreAreaCode code, int row)
			throws RemoteException {
		// TODO Auto-generated method stub
		this.setTableName(code);
		return null;
	}

	@Override
	public OperationMessage moveShelf(StoreAreaCode code_now, int row_now,
			int shelf_now, StoreAreaCode code, int row,int shelf) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperationMessage setLocation(StoreLocation location)
			throws RemoteException {
		// TODO Auto-generated method stub
		this.setTableName(location.getArea());
		OperationMessage result = new OperationMessage();
		String info = location.getRow() + "-" + location.getShelf() + "-"
				+ location.getPosition();
		String delete = "delete from " + Table_Name + " where store_info= '"
				+ info + "'";
		try {
			statement = conn.prepareStatement(delete);
			statement.executeUpdate();
			String search = "insert into " + Table_Name
					+ "(store_info,row,shelf,position,orderID) " + "values('"
					+ info + "','" + location.getRow() + "','"
					+ location.getShelf() + "','" + location.getPosition()
					+ "','" + location.getOrderID() + "')";
			statement = conn.prepareStatement(search);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = new OperationMessage(false, "修改时出错：");
			System.err.println("修改时出错：");
			e.printStackTrace();
		}
		return result;
	}

}
