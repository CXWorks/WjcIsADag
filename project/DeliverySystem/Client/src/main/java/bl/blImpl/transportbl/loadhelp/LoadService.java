package bl.blImpl.transportbl.loadhelp;

import java.util.List;
import java.util.Map;

import vo.ordervo.OrderVO;

/** 
 * Client//bl.blImpl.transportbl.loadhelp//LoadService.java
 * @author CXWorks
 * @date 2015年12月17日 下午5:08:15
 * @version 1.0 
 */
public interface LoadService {
	static final int CAR_H=20;
	static final int CAR_LEN=40;
	static final int CAR_WIDTH=30;
	static final int MAX_LOAD=3000;
	public Map<Boolean, List<Integer>> algorithm(int[][] src);
}
