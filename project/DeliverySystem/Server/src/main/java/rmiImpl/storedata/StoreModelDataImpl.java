package rmiImpl.storedata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.LinkedList;
import java.util.List;

import message.OperationMessage;
import model.store.StoreArea;
import model.store.StoreAreaCode;
import model.store.StoreModel;
import model.store.StoreModelOperation;
import rmi.storedata.StoreModelDataService;
import rmiImpl.ConnecterHelper;

public class StoreModelDataImpl extends UnicastRemoteObject implements
		StoreModelDataService {

	private String Table_Name = "";
	private Connection conn = null;
	private PreparedStatement statement = null;

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

	@Override
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
		return null;
	}

	@Override
	public StoreModel getModel() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperationMessage newShelf(StoreAreaCode code, int row)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperationMessage moveShelf(StoreAreaCode code_now, int row_now,
			int shelf_now, StoreAreaCode code, int row) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperationMessage checkStoreArea(StoreAreaCode code)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
