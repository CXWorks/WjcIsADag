package rmiImpl.companydata;

import java.util.ArrayList;

import message.OperationMessage;
import po.companydata.CenterPO;
import rmi.companydata.companyDataCenterService;

public class CompanyDataCenterImpl implements companyDataCenterService {

	public ArrayList<CenterPO> getCenter() {
		// TODO Auto-generated method stub
		ArrayList<CenterPO> result=new ArrayList<CenterPO>();
		CenterPO stub=new CenterPO();
		result.add(stub);
		return result;
	}

	public String newCenterID() {
		// TODO Auto-generated method stub
		return "1111111";
	}

	public OperationMessage addHall(CenterPO center) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage deleteCenter(CenterPO center) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage modifyCenter(CenterPO center) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

}