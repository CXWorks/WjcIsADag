package tool.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;



import vo.managevo.staff.StaffVO;

/** 
 * Client//tool.ui//StaffVO2ColumnHelper.java
 * @author CXWorks
 * @date 2015年12月2日 下午10:54:36
 * @version 1.0 
 */
public class StaffVO2ColumnHelper extends VO2ColumnHelper<StaffVO> {

	/* (non-Javadoc)
	 * @see tool.ui.VO2ColumnHelper#VO2Entries(vo.CommonVO)
	 */
	@Override
	public Set<Entry<String, String>> VO2Entries(StaffVO vo) {
		Map<String, String> map=new HashMap<String,String>();
		map.put(vo.getStaff().getChinese()+"/"+vo.getID(), vo.getName()+" "+vo.getAge()+" "+vo.getSex().name());
		return map.entrySet();
		
	}
	
}
