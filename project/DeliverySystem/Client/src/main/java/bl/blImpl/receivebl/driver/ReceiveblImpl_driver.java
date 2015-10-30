package bl.blImpl.receivebl.driver;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import message.OperationMessage;
import po.receivedata.ReceivePO;
import rmi.receivedata.ReceiveDataService;

/**
 * 
 * @author wjc
 *2015/10/26
 */
public class ReceiveblImpl_driver {
	/**
	 * @param args
	 */
	public static void main(String [] args){
		try{
			ReceiveDataService ReceiveDataService=(ReceiveDataService)Naming.lookup("rmi://localhost:2333/ReceiveDataService");
			System.out.println(ReceiveDataService.getReceivePO(null) instanceof ReceivePO);
			System.out.println(ReceiveDataService.insert(new ReceivePO()) instanceof OperationMessage);
			System.out.println(ReceiveDataService.delete(null) instanceof OperationMessage);
			System.out.println(ReceiveDataService.update(new ReceivePO()) instanceof OperationMessage);
			System.out.println(ReceiveDataService.newID() instanceof String);
			System.out.println(ReceiveDataService.init(null) instanceof OperationMessage);
			System.out.println(ReceiveDataService.clear() instanceof OperationMessage);
			System.out.println(ReceiveDataService.show() instanceof ArrayList<?>);
			
			
		}catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
