package bl.blService.logblService;

import java.util.ArrayList;
import java.util.Calendar;

import vo.FormVO;

/** 
 * Client//blService.logblService//LogblService.java
 * @author CXWorks
 * @date 2015年10月25日 下午2:46:24
 * @version 1.0 
 */
public interface LogblService {
	public ArrayList<FormVO> getHistory();
	/**
	 * 模糊查找，，，，真是要整死我啊
	 * @param info
	 * @return
	 */
	public ArrayList<FormVO> fuzzyQuery(String info);
	/**
	 * 根据日期查找
	 * @param date
	 * @return
	 */
	public ArrayList<FormVO> dateSearch(Calendar date);
}
