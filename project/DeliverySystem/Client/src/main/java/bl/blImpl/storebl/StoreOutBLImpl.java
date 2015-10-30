package bl.blImpl.storebl;

import bl.blService.storeblService.StoreOutBLService;
import message.CheckFormMessage;
import message.OperationMessage;
import rmi.storedata.StoreFormDataService;
import util.R;
import vo.ordervo.OrderVO;
import vo.storevo.StoreOutVO;
import vo.transitvo.CenterOutVO;
import vo.transitvo.TransitVO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sissel on 2015/10/26.
 */
public class StoreOutBLImpl implements StoreOutBLService {
    public String getNewStoreOutID(String date) {
        return "222333";
    }

    public OperationMessage loadOrder(String orderNumber) {
        return new OperationMessage();
    }

    public OrderVO getOrderVO() {
        return new OrderVO();
    }

    public TransitVO getTransportVO() {
        return new CenterOutVO();
    }

    public OperationMessage clearLocalBuffer() {
        return new OperationMessage();
    }

    public StoreOutVO loadDraft() {
        return new StoreOutVO();
    }

    public OperationMessage saveDraft(StoreOutVO form) {
        return new OperationMessage();
    }

    public ArrayList<CheckFormMessage> checkFormat(StoreOutVO form, boolean isFinal) {
    	ArrayList<CheckFormMessage> result =new ArrayList<CheckFormMessage>();
		CheckFormMessage stub=new CheckFormMessage();
		result.add(stub);
		return result;
    }

    public OperationMessage submit(StoreOutVO form) {
        return new OperationMessage();
    }

    public static void drive(StoreFormDataService sfds) throws RemoteException {
        if(sfds.downloadAllStoreOutPOs("222333") != null)
            System.out.println("downloadAllStoreOutPOs tested");
        if(sfds.getStoreOutPO("222333") != null)
            System.out.println("getStoreOutPO tested");
        if(sfds.updateStoreOutPOs("222333") != null)
            System.out.println("updateStoreOutPOs tested");
    }

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        StoreFormDataService sfds = (StoreFormDataService) Naming.lookup
                ("rmi://" + R.string.LocalHost + "/" + R.string.StoreDataService);
        drive(sfds);
    }
}
