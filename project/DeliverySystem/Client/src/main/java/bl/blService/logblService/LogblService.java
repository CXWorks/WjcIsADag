package bl.blService.logblService;

import java.util.ArrayList;
import java.util.Calendar;

import message.OperationMessage;
import vo.FormVO;
import vo.systemvo.LogVO;

/** 
 * Client//blService.logblService//LogblService.java
 * @author CXWorks
 * @date 2015年10月25日 下午2:46:24
 * @version 1.0 
 */
public interface LogblService {
	/**
	 * 模糊查找，，，，真是要整死我啊
	 * @param info
	 * @return
	 * @warning 找不到会返回长度为0的list，而不是null
	 */
	public ArrayList<LogVO> fuzzyQuery(String info);
	/**
	 * 根据日期查找
	 * @param date
	 * @return
	 */
	public ArrayList<LogVO> dateSearch(Calendar start,Calendar end);
	/**
	 * 导出为txt文件
	 * @param path
	 * @return
	 */
	public OperationMessage exportToTXT(String path);
}
