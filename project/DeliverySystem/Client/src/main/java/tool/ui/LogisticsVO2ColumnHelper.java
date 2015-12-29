package tool.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;




import vo.logisticsvo.LogisticsVO;

/** 
 * Client//tool.ui//LogisticsVO2ColumnHelper.java
 * @author CXWorks
 * @date Dec 29, 2015 8:37:47 PM
 * @version 1.0 
 */
public class LogisticsVO2ColumnHelper extends VO2ColumnHelper<LogisticsVO> {

	/* (non-Javadoc)
	 * @see tool.ui.VO2ColumnHelper#VO2Entries(vo.CommonVO)
	 */
	@Override
	public Set<Map.Entry<String, String>> VO2Entries(LogisticsVO vo) {
		List<String> location=vo.getLocation();
		List<String> time=vo.getTime();
		Map<String, String> map= new HashMap<String,String>(location.size());
		for (int i = 0; i < location.size(); i++) {
			map.put(location.get(i), time.get(i));
		}
		return map.entrySet();
	}

}
