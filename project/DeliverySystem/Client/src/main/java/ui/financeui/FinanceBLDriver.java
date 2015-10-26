package ui.financeui;

import blImpl.financebl.BankAccountBLImpl;
import blImpl.financebl.FinanceChartBLImpl;
import blImpl.financebl.PaymentBLImpl;
import blImpl.financebl.RevenueBLImpl;
import blService.financeblService.BankAccountBLService;
import blService.financeblService.FinanceChartBLService;
import blService.financeblService.PaymentBLService;
import blService.financeblService.RevenueBLService;

/**
 * Created by Sissel on 2015/10/26.
 */
public class FinanceBLDriver {
    BankAccountBLService babls = new BankAccountBLImpl();
    FinanceChartBLService fcbls = new FinanceChartBLImpl();
    PaymentBLService pbls = new PaymentBLImpl();
    RevenueBLService rbls = new RevenueBLImpl();

    public void dirveAll(){
        // BankAccountBLService Test
        System.out.println("BankAccountBLService Begin:");
        if(babls.getAllAccounts() != null)
            System.out.println("getAllAccounts tested");
        if(babls.addAccount(null) != null)
            System.out.println("addAccount tested");
        if(babls.deleteAccount(null) != null)
            System.out.println("deleteAccount tested");
        if(babls.editAccount(null, "223") != null)
            System.out.println("editAccount tested");
        if(babls.pay("222", "333") != null)
            System.out.println("pay tested");
        if(babls.receive("222", "333") != null)
            System.out.println("receive tested");
        if(babls.filterAccounts(null, "fuck") != null)
            System.out.println("filterAccounts tested");
        if(babls.getTradeHistory(null) != null)
            System.out.println("getTradeHistory tested");
        System.out.println();

        // FinanceChartBLService Test
        System.out.println("FinanceChartBLService Begin:");
        if(fcbls.getHistogram() != null)
            System.out.println("getHistogram tested");
        if(fcbls.getLineChart() != null)
            System.out.println("getLineChart tested");
        if(fcbls.getPieChart() != null)
            System.out.println("getPieChart tested");
        System.out.println();

        // PaymentBLService Test
        System.out.println("PaymentBLService Begin:");
        if(pbls.getNewPaymentID("222333") != null)
            System.out.println("getNewPaymentID tested");
        if(pbls.getPaymentVO("222") != null)
            System.out.println("getPaymentVO tested");
        if(pbls.getPaymentVOs("123", "456") != null)
            System.out.println("getPaymentVOs tested");
        System.out.println();

        // RevenueBLService Test
        System.out.println("RevenueBLService Begin:");
        if(rbls.getNewRevenueID("222333") != null)
            System.out.println("getNewRevenueID tested");
        if(rbls.getRevenueVO("222") != null)
            System.out.println("getRevenueVO tested");
        if(rbls.getRevenueVOs("123", "456") != null)
            System.out.println("getRevenueVOs tested");
        if(rbls.getRevenueVO("222", "333") != null)
            System.out.println("getRevenueVO(2 args) tested");
        if(rbls.getOrderVO() != null)
            System.out.println("getOrderVO tested");
        if(rbls.loadOrder("222333") != null)
            System.out.println("loadOrder tested");
        System.out.println();
    }

    public static void main(String[] args) {
        new FinanceBLDriver().dirveAll();
    }

}
