package serverMain;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

import rmiImpl.DataFactory;
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
    	try {
    		DataFactory.initializeRMI();
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
//			OrderPO t = test.getFormPO("10000001");
//			t.setFormID("10000002");
//			test.insert(t);
////			test.delete("10000002");
//			ConnecterHelper.deconnSQL(test.getConn());
//			System.exit(0);
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

    }

}
