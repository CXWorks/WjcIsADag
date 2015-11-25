package serverMain;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;

import po.memberdata.StaffPO;
import po.memberdata.StaffTypeEnum;
import rmi.accountdata.AccountDataService;
import rmi.deliverdata.DeliverDataService;
import rmi.memberdata.MemberDataService;
import rmi.orderdata.OrderDataService;
import rmi.receivedata.ReceiveDataService;
import rmi.storedata.StoreModelDataService;
import rmi.transportdata.LoadDataService;
import rmiImpl.ConnecterHelper;
import rmiImpl.accountdata.AccountDataImpl;
import rmiImpl.deliverdata.DeliverDataImpl;
import rmiImpl.memberdata.StaffDataImpl;
import rmiImpl.orderdata.OrderDataImpl;
import rmiImpl.receivedata.ReceiveDataImpl;
import rmiImpl.storedata.StoreModelDataImpl;
import rmiImpl.transportdata.LoadDataImpl;
import database.SqlHelper;
import message.OperationMessage;
import model.store.StoreAreaCode;

/**
 * Created by Sissel on 2015/10/25.
 */
public class ServerMain {

    SqlHelper sql;

    OperationMessage setUpSystem(String username, char[] password, String dbName){
        return null;
    }

    OperationMessage recoverSystem(String username, char[] password, String dbName){
        return null;
    }

    public static void main(String[] args) {
    	try {
			LocateRegistry.createRegistry(2333);
//			DeliverDataService test = new DeliverDataImpl();
//			OrderDataService order= new OrderDataImpl();
//			ReceiveDataService test = new ReceiveDataImpl();
//			LoadDataService test = new LoadDataImpl();
			AccountDataService t1 = new AccountDataImpl();
			DeliverDataService t2 = new DeliverDataImpl();
//			Naming.rebind("rmi://localhost:2333/ReceiveDataService", test);
//			Naming.rebind("rmi://localhost:2333/OrderDataService", order);
//			Naming.rebind("rmi://localhost:2333/LoadDataService", test);
			Naming.rebind("rmi://localhost:2333/AccountDataService", t1);
			Naming.rebind("rmi://localhost:2333/DeliverDataService", t2);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			OrderDataService test = new OrderDataImpl();
////			ReceiveDataService t2 = new ReceiveDataImpl();
//			test.getFormPO("10000001");
//			ConnecterHelper.deconnSQL(test.getConn());
//			System.exit(0);
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

    }

}
