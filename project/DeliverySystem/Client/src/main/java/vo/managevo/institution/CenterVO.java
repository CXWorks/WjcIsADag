package vo.managevo.institution;
import java.util.ArrayList;

import po.InfoEnum;
import po.companydata.CenterPO;
import po.memberdata.StaffPO;
import vo.InfoVO;
import vo.managevo.staff.StaffVO;


public class CenterVO extends InstitutionVO{
	
	public CenterVO(){
		super(InfoEnum.CENTER);
	}
	
	public CenterVO(String centerID, String city, ArrayList<StaffVO> storeman,
			ArrayList<StaffVO> counterman) {
		this();
		this.institutionID = centerID;
		this.city = city;
		this.storeman = storeman;
		this.counterman = counterman;
	}
	public CenterVO(String centerID,String city){
		this();
		this.institutionID=centerID;
		this.city=city;
		this.storeman=new ArrayList<StaffVO>();
		this.counterman=new ArrayList<StaffVO>();
	}
	
	private ArrayList<StaffVO> storeman;
	private ArrayList<StaffVO> counterman;
	//
	public CenterVO(CenterPO po){
		this(po.getCenterID(), po.getCity(), null, null);
		this.setStoreman(selfDeepClonePOtoVO(po.getStoreman()));
		this.setCounterman(selfDeepClonePOtoVO(po.getCounterman()));
	}
	//

	private void setStoreman(ArrayList<StaffVO> storeman) {
		this.storeman = storeman;
	}

	private void setCounterman(ArrayList<StaffVO> counterman) {
		this.counterman = counterman;
	}
	public CenterPO toPO(){
		ArrayList<StaffPO> storemanPO=new ArrayList<StaffPO>(this.storeman.size());
		ArrayList<StaffPO> countermanPO=new ArrayList<StaffPO>(this.counterman.size());
		//
		for(int i=0;i<this.storeman.size();i++){
			StaffPO temp=this.storeman.get(i).toPO();
			storemanPO.add(temp);
		}
		for(int i=0;i<this.counterman.size();i++){
			StaffPO temp=this.counterman.get(i).toPO();
			countermanPO.add(temp);
		}
		//
		return new CenterPO(institutionID, city, storemanPO, countermanPO);
	}

	public String getCenterID() {
		return institutionID;
	}

	public String getCity() {
		return city;
	}

	@Override
	public int getStaffCount() {
		return storeman.size() + counterman.size();
	}
}
