package ui.reflection;

/*
import java.util.ArrayList;

import bl.blImpl.deliverbl.DeliverBLController;
import bl.blImpl.examinebl.ExamineBLManageImpl;
import com.sun.org.apache.bcel.internal.generic.NEW;

import rmi.examineService.ExamineManageService;
import bl.blImpl.accountbl.AccountBLImpl;
import bl.blImpl.deliverbl.DeliverblImpl;
import bl.blImpl.examinebl.ExamineblManageImpl;
import bl.blImpl.examinebl.ExamineblSubmitImpl;
import bl.blImpl.financebl.BankAccountBLImpl;
import bl.blImpl.financebl.FinanceChartBLImpl;
import bl.blImpl.financebl.PaymentBLImpl;
import bl.blImpl.financebl.RevenueBLImpl;
import bl.blImpl.formatCheck.FormatCheckImpl;
import bl.blImpl.logbl.LogblImpl;
import bl.blImpl.manangrbl.ManageblCarImpl;
import bl.blImpl.manangrbl.ManageblCenterImpl;
import bl.blImpl.manangrbl.ManageblHallImpl;
import bl.blImpl.manangrbl.ManageblStaffImpl;
import bl.blImpl.orderbl.OrderBLImpl;
import bl.blImpl.receivebl.ReceiveblImpl;
import bl.blImpl.searchbl.SearchBLImpl;
import bl.blImpl.storebl.StockTackBLImpl;
import bl.blImpl.storebl.StoreIOBLImpl;
import bl.blImpl.storebl.StoreInBLImpl;
import bl.blImpl.storebl.StoreModelBLImpl;
import bl.blImpl.storebl.StoreOutBLImpl;
import bl.blImpl.transportbl.TransportCenterBLImpl;
import bl.blImpl.transportbl.TransportHallBLImpl;
import bl.blService.FormBLService;
import bl.blService.FormatCheckService.FormatCheckService;
import bl.blService.accountblService.AccountBLService;
import bl.blService.deliverblService.DeliverBLService;
import bl.blService.examineblService.ExamineblManageService;
import bl.blService.examineblService.ExamineblSubmitService;
import bl.blService.financeblService.BankAccountBLService;
import bl.blService.financeblService.PaymentBLService;
import bl.blService.financeblService.RevenueBLService;
import bl.blService.initblService.InitializationBLService;
import bl.blService.logblService.LogblService;
import bl.blService.manageblService.ManageblCarService;
import bl.blService.manageblService.ManageblCenterService;
import bl.blService.manageblService.ManageblHallService;
import bl.blService.manageblService.ManageblStaffService;
import bl.blService.orderblService.OrderBLService;
import bl.blService.receiveblService.ReceiveBLService;
import bl.blService.searchblService.SearchBLService;
import bl.blService.storeblService.StockTackBLService;
import bl.blService.storeblService.StoreIOBLService;
import bl.blService.storeblService.StoreInBLService;
import bl.blService.storeblService.StoreModelBLService;
import bl.blService.storeblService.StoreOutBLService;
import bl.blService.transportblService.TransportCenterBLService;
import bl.blService.transportblService.TransportHallBLService;
import ui.configurationui.ConfigurationPanel;
import ui.mainui.MainFrame;
*/

/** 
 * Client//ui.reflection//Main.java
 * @author CXWorks
 * @date 2015年10月26日 下午12:09:46
 * @version 1.0 
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*// TODO Auto-generated method stub
		//inite our classes
		ExamineblManageService c1=new ExamineBLManageImpl();
		AccountBLService c2=new ;
		DeliverBLService c3=new DeliverBLController();
		ExamineblSubmitService c4=new ExamineblSubmitImpl();
		BankAccountBLService c5=new BankAccountBLImpl();
		FinanceChartBLImpl c6=new FinanceChartBLImpl();
		PaymentBLService c7=new PaymentBLImpl();
		RevenueBLService c8=new RevenueBLImpl();
		FormatCheckService c9=new FormatCheckImpl();
		LogblService c10=new LogblImpl();
		ManageblCarService c11=new ManageblCarImpl();
		ManageblCenterService c12=new ManageblCenterImpl();
		ManageblHallService c13=new ManageblHallImpl();
		ManageblStaffService c14=new ManageblStaffImpl();
		OrderBLService c15=new OrderBLImpl();
		ReceiveBLService c16=new ReceiveblImpl();
		SearchBLService c17=new SearchBLImpl();
		StockTackBLService c18=new StockTackBLImpl();
		StoreInBLService c19=new StoreInBLImpl();
		StoreIOBLService c20=new StoreIOBLImpl();
		StoreModelBLService c21=new StoreModelBLImpl();
		StoreOutBLService c22=new StoreOutBLImpl();
		TransportCenterBLService c23=new TransportCenterBLImpl();
		TransportHallBLService c24=new TransportHallBLImpl();
		//For
		//
		ArrayList<Class<?>> t=new ArrayList<Class<?>>();
		ArrayList<Object> to=new ArrayList<Object>();
		to.add(c1);
		to.add(c2);
		to.add(c3);
		to.add(c4);
		to.add(c5);
		to.add(c6);
		to.add(c7);
		to.add(c8);
		to.add(c9);
		to.add(c10);
		to.add(c11);
		to.add(c12);
		to.add(c13);
		to.add(c14);
		to.add(c15);
		to.add(c16);
		to.add(c17);
		to.add(c18);
		to.add(c19);
		to.add(c20);
		to.add(c21);
		to.add(c22);
		to.add(c23);
		to.add(c24);
		t.add(c1.getClass());
		t.add(c2.getClass());
		t.add(c3.getClass());
		t.add(c4.getClass());
		t.add(c5.getClass());
		t.add(c6.getClass());
		t.add(c7.getClass());
		t.add(c8.getClass());
		t.add(c9.getClass());
		t.add(c10.getClass());
		t.add(c11.getClass());
		t.add(c12.getClass());
		t.add(c13.getClass());
		t.add(c14.getClass());
		t.add(c15.getClass());
		t.add(c16.getClass());
		t.add(c17.getClass());
		t.add(c18.getClass());
		t.add(c19.getClass());
		t.add(c20.getClass());
		t.add(c21.getClass());
		t.add(c22.getClass());
		t.add(c23.getClass());
		t.add(c24.getClass());
		MainPanel a=new MainPanel();
		
		a.addClass(t,to);
		
		a.setVisible(true);
		new MainFrame(a);*/
	}

}
