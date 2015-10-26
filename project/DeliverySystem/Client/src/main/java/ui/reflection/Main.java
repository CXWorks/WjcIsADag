package ui.reflection;

import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.NEW;

import rmi.examineService.ExamineManageService;
import blImpl.accountbl.AccountBLImpl;
import blImpl.deliverbl.DeliverblImpl;
import blImpl.examinebl.ExamineblManageImpl;
import blImpl.examinebl.ExamineblSubmitImpl;
import blImpl.financebl.BankAccountBLImpl;
import blImpl.financebl.FinanceChartBLImpl;
import blImpl.financebl.PaymentBLImpl;
import blImpl.financebl.RevenueBLImpl;
import blImpl.formatCheck.FormatCheckImpl;
import blImpl.logbl.LogblImpl;
import blImpl.manangrbl.ManageblCarImpl;
import blImpl.manangrbl.ManageblCenterImpl;
import blImpl.manangrbl.ManageblHallImpl;
import blImpl.manangrbl.ManageblStaffImpl;
import blImpl.orderbl.OrderBLImpl;
import blImpl.receivebl.ReceiveblImpl;
import blImpl.searchbl.SearchBLImpl;
import blImpl.storebl.StockTackBLImpl;
import blImpl.storebl.StoreIOBLImpl;
import blImpl.storebl.StoreInBLImpl;
import blImpl.storebl.StoreModelBLImpl;
import blImpl.storebl.StoreOutBLImpl;
import blImpl.transportbl.TransportCenterBLImpl;
import blImpl.transportbl.TransportHallBLImpl;
import blService.FormBLService;
import blService.FormatCheckService.FormatCheckService;
import blService.accountblService.AccountBLService;
import blService.deliverblService.DeliverBLService;
import blService.examineblService.ExamineblManageService;
import blService.examineblService.ExamineblSubmitService;
import blService.financeblService.BankAccountBLService;
import blService.financeblService.PaymentBLService;
import blService.financeblService.RevenueBLService;
import blService.initblService.InitializationBLService;
import blService.logblService.LogblService;
import blService.manageblService.ManageblCarService;
import blService.manageblService.ManageblCenterService;
import blService.manageblService.ManageblHallService;
import blService.manageblService.ManageblStaffService;
import blService.orderblService.OrderBLService;
import blService.receiveblService.ReceiveBLService;
import blService.searchblService.SearchBLService;
import blService.storeblService.StockTackBLService;
import blService.storeblService.StoreIOBLService;
import blService.storeblService.StoreInBLService;
import blService.storeblService.StoreModelBLService;
import blService.storeblService.StoreOutBLService;
import blService.transportblService.TransportCenterBLService;
import blService.transportblService.TransportHallBLService;
import ui.configurationui.ConfigurationPanel;
import ui.mainui.MainFrame;

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
		// TODO Auto-generated method stub
		//inite our classes
		ExamineblManageService c1=new ExamineblManageImpl();
		AccountBLService c2=new AccountBLImpl();
		DeliverBLService c3=new DeliverblImpl();
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
		new MainFrame(a);
	}

}
