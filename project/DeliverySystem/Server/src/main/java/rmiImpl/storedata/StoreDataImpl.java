package rmiImpl.storedata;

import message.OperationMessage;
import model.store.StoreModel;
import model.store.StoreModelOperation;
import po.storedata.StoreInPO;
import po.storedata.StoreOutPO;
import rmi.storedata.StoreFormDataService;
import rmi.storedata.StoreModelDataService;
import rmiImpl.ConnecterHelper;
import util.R;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sissel on 2015/10/25.
 */
public class StoreDataImpl extends UnicastRemoteObject implements
		StoreFormDataService, StoreModelDataService {

	/** 接口的名称，RMI绑定时候的名称 */
	public static final String NAME = "StoreData";

	private String Store_In;
	private String Store_Out;
	private Connection conn = null;
	private PreparedStatement statement = null;

	public StoreDataImpl() throws RemoteException, MalformedURLException {
		// TODO Auto-generated constructor stub
		super();
		Store_In = "store_in";
		Store_Out = "store_out";
		conn = ConnecterHelper.connSQL(conn);
	}

	public Connection getConn() {
		return conn;
	}

	public static void main(String[] args) throws MalformedURLException,
			RemoteException {
		new StoreDataImpl();
	}

	public StoreInPO getStoreInPO(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public StoreOutPO getStoreOutPO(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public LinkedList<StoreInPO> updateStoreInPOs(String staffID) {
		// TODO Auto-generated method stub
		return new LinkedList<StoreInPO>();
	}

	public LinkedList<StoreInPO> downloadAllStoreInPOs(String centerID) {
		// TODO Auto-generated method stub
		return new LinkedList<StoreInPO>();
	}

	public LinkedList<StoreOutPO> updateStoreOutPOs(String centerID) {
		// TODO Auto-generated method stub
		return new LinkedList<StoreOutPO>();
	}

	public LinkedList<StoreOutPO> downloadAllStoreOutPOs(String staffID) {
		// TODO Auto-generated method stub
		return new LinkedList<StoreOutPO>();
	}

	public OperationMessage uploadModelOperations(String centerID,
			String staffID, List<StoreModelOperation> operations) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public LinkedList<StoreModelOperation> updateModelOperations(
			String centerID, String staffID) {
		// TODO Auto-generated method stub
		return new LinkedList<StoreModelOperation>();
	}

	public StoreModel downloadStoreModel(String centerID) {
		// TODO Auto-generated method stub
		return new StoreModel();
	}
}
