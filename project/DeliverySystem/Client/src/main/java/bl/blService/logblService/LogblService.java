package bl.blService.logblService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
     * @param keyword
     * @param begin
     * @param end
     * @return 搜索结果, 假如没有，则返回内容为空的列表
     */
	public List<LogVO> search(String keyword, Calendar begin, Calendar end);

	/**
	 * 导出为txt文件
	 * @param path
	 * @return
	 */
	public OperationMessage exportToTXT(String path);
}
