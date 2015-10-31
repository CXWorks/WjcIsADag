package bl.blImpl.deliverbl.driver;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import message.OperationMessage;
import po.deliverdata.*;
import rmi.deliverdata.*;

/**
 * 
 * @author wjc
 *2015/10/26
 */
public class DeliverblImpl_driver {
	/**
	 * @param args
	 */
	public static void main(String [] args){
		
		try{
			DeliverDataService DeliverDataService=(DeliverDataService)Naming.lookup("rmi://localhost:2333/DeliverDataService");
			System.out.println(DeliverDataService.getFormPO(null) instanceof DeliverPO);
			System.out.println(DeliverDataService.insert(new DeliverPO()) instanceof OperationMessage);
			System.out.println(DeliverDataService.delete(null) instanceof OperationMessage);
			System.out.println(DeliverDataService.update(new DeliverPO()) instanceof OperationMessage);
			System.out.println(DeliverDataService.newID() instanceof String);
			System.out.println(DeliverDataService.clear() instanceof OperationMessage);
			System.out.println(DeliverDataService.getAll() instanceof ArrayList<?>);
			
			
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
