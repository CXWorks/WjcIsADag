package blImpl.transportbl.driver;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import message.OperationMessage;
import po.orderdata.OrderPO;
import po.transportdata.LoadPO;
import po.transportdata.TransportPO;
import rmi.orderdata.OrderDataService;
import rmi.transportdata.TransportDataService;

public class TransportBLImpl_driver {
	/**
	 * @param args
	 */
	public static void main(String [] args){
		
		try{
			TransportDataService TransportDataService=(TransportDataService)Naming.lookup("rmi://localhost:2333/TransportDataService");
			System.out.println(TransportDataService.getTransportPO(null) instanceof TransportPO);
			System.out.println(TransportDataService.insert(new LoadPO()) instanceof OperationMessage);
			System.out.println(TransportDataService.delete(null) instanceof OperationMessage);
			System.out.println(TransportDataService.update(new LoadPO()) instanceof OperationMessage);
			System.out.println(TransportDataService.clear() instanceof OperationMessage);
			System.out.println(TransportDataService.newID() instanceof String);
			
			
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
