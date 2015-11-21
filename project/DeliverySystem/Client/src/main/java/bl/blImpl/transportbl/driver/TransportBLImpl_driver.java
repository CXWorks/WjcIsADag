package bl.blImpl.transportbl.driver;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import message.OperationMessage;
import po.orderdata.OrderPO;
import po.transportdata.LoadPO;
import po.transportdata.TransportPO;
import rmi.orderdata.OrderDataService;
import rmi.transportdata.CenterOutDataService;

public class TransportBLImpl_driver {
	/**
	 * @param args
	 */
	public static void main(String [] args){
		
		try{
			CenterOutDataService TransportDataService=(CenterOutDataService)Naming.lookup("rmi://localhost:2333/TransportDataService");
			System.out.println(TransportDataService.getFormPO(null) instanceof TransportPO);
			System.out.println(TransportDataService.insert(new LoadPO()) instanceof OperationMessage);
			System.out.println(TransportDataService.delete(null) instanceof OperationMessage);
			System.out.println(TransportDataService.update(new LoadPO()) instanceof OperationMessage);
			System.out.println(TransportDataService.clear() instanceof OperationMessage);
			System.out.println(TransportDataService.newID() instanceof String);
			System.out.println(TransportDataService.getAll() instanceof ArrayList<?>);
			
			
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
