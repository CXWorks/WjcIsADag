package bl.blImpl.orderbl.driver;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import message.CheckFormMessage;
import message.OperationMessage;
import po.FormPO;
import po.orderdata.OrderPO;
import rmi.examineService.ExamineManageService;
import rmi.orderdata.OrderDataService;
import vo.ordervo.OrderVO;
import vo.ordervo.PredictVO;
import bl.blService.orderblService.OrderBLService;

public class OrderBLImpl_driver{
	/**
	 * @param args
	 */
	public static void main(String [] args){
		
		try{
			OrderDataService OrderDataService=(OrderDataService)Naming.lookup("rmi://localhost:2333/OrderDataService");
			System.out.println(OrderDataService.getFormPO(null) instanceof OrderPO);
			System.out.println(OrderDataService.insert(new OrderPO()) instanceof OperationMessage);
			System.out.println(OrderDataService.delete(null) instanceof OperationMessage);
			System.out.println(OrderDataService.clear() instanceof OperationMessage);
			System.out.println(OrderDataService.update(new OrderPO()) instanceof OperationMessage);
			System.out.println(OrderDataService.newID() instanceof String);
			
			
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
		
		
		
		
		
		
		
		
		
		
	
	
	
