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
        return new OrderVO("1123000001");
    }

    public TransitVO getTransportVO() {
        return new CenterOutVO("050010001201511230000002");
    }

    public OperationMessage clearLocalBuffer() {
        return new OperationMessage();
    }

    public StoreOutVO loadDraft() {
        return null;
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


	/* (non-Javadoc)
	 * @see bl.blService.FormBLService#newID()
	 */
	public String newID() {
		// TODO Auto-generated method stub
		return null;
	}
}
