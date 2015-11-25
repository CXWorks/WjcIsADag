package serverMain;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;

import po.memberdata.StaffPO;
import po.memberdata.StaffTypeEnum;
import rmi.deliverdata.DeliverDataService;
import rmi.memberdata.MemberDataService;
import rmi.orderdata.OrderDataService;
import rmi.receivedata.ReceiveDataService;
import rmi.storedata.StoreModelDataService;
import rmiImpl.ConnecterHelper;
import rmiImpl.deliverdata.DeliverDataImpl;
import rmiImpl.memberdata.StaffDataImpl;
import rmiImpl.orderdata.OrderDataImpl;
import rmiImpl.receivedata.ReceiveDataImpl;
import rmiImpl.storedata.StoreModelDataImpl;
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
//    	try {
//			LocateRegistry.createRegistry(2333);
////			DeliverDataService test = new DeliverDataImpl();
//			OrderDataService order= new OrderDataImpl();
//			ReceiveDataService test = new ReceiveDataImpl();
//			Naming.rebind("rmi://localhost:2333/ReceiveDataService", test);
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		try {
			StoreModelDataService test = new StoreModelDataImpl();
			test.moveShelf(StoreAreaCode.AIR, 1, 1, StoreAreaCode.ROAD, 1, 1);
			ConnecterHelper.deconnSQL(test.getConn());
			System.exit(0);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

}
