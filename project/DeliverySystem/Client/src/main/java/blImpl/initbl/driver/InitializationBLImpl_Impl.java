package blImpl.initbl.driver;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.initialdata.InitialDataPO;
import rmi.initialdata.InitialDataService;
import message.OperationMessage;


public class InitializationBLImpl_Impl {
	/**
	 * @param args
	 */
	public static void main(String [] args){
		
		try{
			InitialDataService InitialDataService=(InitialDataService)Naming.lookup("rmi://localhost:2333/InitialDataService");
			System.out.println(InitialDataService.getInitialDataPO("111") instanceof InitialDataPO);
			System.out.println(InitialDataService.requestInitData("111") instanceof OperationMessage);
			System.out.println(InitialDataService.uploadInitialData("111",null) instanceof OperationMessage);
			System.out.println(InitialDataService.abortInitData("111") instanceof OperationMessage);
			
			
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
