package serverMain;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;

import po.memberdata.StaffPO;
import po.memberdata.StaffTypeEnum;
import rmi.memberdata.MemberDataService;
import rmi.receivedata.ReceiveDataService;
import rmiImpl.ConnecterHelper;
import rmiImpl.memberdata.MemberDataImpl;
import rmiImpl.receivedata.ReceiveDataImpl;
import database.SqlHelper;
import message.OperationMessage;

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
			MemberDataService test = new MemberDataImpl();
			ArrayList<StaffPO> tmp = test.getStaff(StaffTypeEnum.DELIVER);
			test.dismissStaff(tmp.get(0));
			ConnecterHelper.deconnSQL(test.getConn());
			System.exit(0);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

}
