package factory;

import bl.blImpl.financebl.BankAccountBLImpl;
import bl.blImpl.financebl.FinanceChartBLImpl;
import bl.blImpl.financebl.PaymentBLImpl;
import bl.blImpl.financebl.RevenueBLImpl;
import bl.blService.financeblService.BankAccountBLService;
import bl.blService.financeblService.FinanceChartBLService;
import bl.blService.financeblService.PaymentBLService;
import bl.blService.financeblService.RevenueBLService;
import tool.draft.DraftController;
import tool.vopo.VOPOFactory;

/**
 * Created by Sissel on 2015/11/27.
 */
public class FinanceFactory {
    private static FinanceChartBLService financeChartBLService;
    private static RevenueBLService revenueBLService;
    private static PaymentBLService paymentBLService;
    private static BankAccountBLService bankAccountBLService;

    public static FinanceChartBLService getFinanceChartBLService() {
        if(financeChartBLService == null){
            financeChartBLService = new FinanceChartBLImpl();
        }
        return financeChartBLService;
    }

    public static RevenueBLService getRevenueBLService() {
        if(revenueBLService == null){
            revenueBLService = new RevenueBLImpl(new VOPOFactory(), new DraftController());
        }
        return revenueBLService;
    }

    public static PaymentBLService getPaymentBLService() {
        if(paymentBLService == null){
            paymentBLService = new PaymentBLImpl(new VOPOFactory(), new DraftController());
        }
        return paymentBLService;
    }

    public static BankAccountBLService getBankAccountBLService() {
        if(bankAccountBLService == null){
            bankAccountBLService = new BankAccountBLImpl();
        }
        return bankAccountBLService;
    }
}
