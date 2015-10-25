package rmiImpl.memberdata;

import java.util.ArrayList;

import message.OperationMessage;
import po.memberdata.StaffPO;
import po.memberdata.StaffTypeEnum;
import rmi.memberdata.memberDataService;

public class MemberDataImpl implements memberDataService {

	public ArrayList<StaffPO> getStaff(StaffTypeEnum staffTypeEnum) {
		// TODO Auto-generated method stub
		ArrayList<StaffPO> result =new ArrayList<StaffPO>();
		StaffPO stub=new StaffPO();
		result.add(stub);
		return result;
	}

	public OperationMessage modifyStaff(StaffPO after) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage addStaff(StaffPO staff) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage dismissStaff(StaffPO staff) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public StaffPO searchStaff(StaffPO staff) {
		// TODO Auto-generated method stub
		return new StaffPO();
	}

	public String newStaffID() {
		// TODO Auto-generated method stub
		return "1111";
	}

}
