package vo.managevo.institution;
import java.util.ArrayList;

import po.memberdata.StaffPO;

public class HallVO {
	private String hallID;
	private String city;
	private String area;
	private ArrayList<StaffPO> driver;
	private ArrayList<StaffPO> deliver;
	private ArrayList<StaffPO> counterman;
	private String nearCenterID;
}
