package vo.managevo.institution;
import java.util.ArrayList;

import po.memberdata.StaffPO;
import vo.InfoVO;

public class HallVO extends InfoVO{
	
	public HallVO(String hallID, String city, String area,
			ArrayList<StaffPO> driver, ArrayList<StaffPO> deliver,
			ArrayList<StaffPO> counterman, String nearCenterID) {
		super();
		this.hallID = hallID;
		this.city = city;
		this.area = area;
		this.driver = driver;
		this.deliver = deliver;
		this.counterman = counterman;
		this.nearCenterID = nearCenterID;
	}
	
	private String hallID;
	private String city;
	private String area;
	private ArrayList<StaffPO> driver;
	private ArrayList<StaffPO> deliver;
	private ArrayList<StaffPO> counterman;
	private String nearCenterID;
	public HallVO(){}
	//
	public HallVO(String hallID){
		super();
		this.hallID=hallID;
	}
}
