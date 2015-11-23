package bl.blImpl.storebl;

import bl.blService.storeblService.StoreInBLService;
import message.CheckFormMessage;
import message.OperationMessage;
import model.store.StoreAreaCode;
import model.store.StoreLocation;
import rmi.storedata.StoreFormDataService;
import util.R;
import vo.ordervo.OrderVO;
import vo.storevo.StoreInVO;

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
public class StoreInBLImpl implements StoreInBLService {
    public StoreLocation getAvailableLocation(StoreAreaCode area) {
        return new StoreLocation();
    }

    public String getNewStoreInID(String date) {
        return "222333";
    }

    public OperationMessage loadOrder(String orderNumber) {
        return new OperationMessage();
    }

    public OrderVO getOrderVO() {
        return new OrderVO("1123000001");
    }

    public OperationMessage clearLocalBuffer() {
        return new OperationMessage();
    }

    public StoreInVO loadDraft() {
        return new StoreInVO("050010001201511230000002");
    }

    public OperationMessage saveDraft(StoreInVO form) {
        return new OperationMessage();
    }

    public ArrayList<CheckFormMessage> checkFormat(StoreInVO form, boolean isFinal) {
    	ArrayList<CheckFormMessage> result =new ArrayList<CheckFormMessage>();
		CheckFormMessage stub=new CheckFormMessage();
		result.add(stub);
		return result;
    }

    public OperationMessage submit(StoreInVO form) {
        return new OperationMessage();
    }

    public static void drive(StoreFormDataService sfds) throws RemoteException {
        if(sfds.downloadAllStoreInPOs("222333") != null)
            System.out.println("downloadAllStoreInPOs tested");
        if(sfds.getStoreInPO("222333") != null)
            System.out.println("getStoreInPO tested");
        if(sfds.updateStoreInPOs("222333") != null)
            System.out.println("updateStoreInPOs tested");
    }

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        StoreFormDataService sfds = (StoreFormDataService) Naming.lookup
                ("rmi://" + R.string.LocalHost + "/" + R.string.StoreDataService);
        drive(sfds);
    }

	/* (non-Javadoc)
	 * @see bl.blService.FormBLService#newID()
	 */
	public String newID() {
		// TODO Auto-generated method stub
		return null;
	}
}
