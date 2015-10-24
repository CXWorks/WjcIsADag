package rmi.memberdata;

import java.util.ArrayList;

import po.memberdata.StaffPO;
import po.memberdata.StaffTypeEnum;

public interface memberDataService {
	public ArrayList<StaffPO> getStaff(StaffTypeEnum staffTypeEnum);

}
