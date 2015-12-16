package bl.blImpl.logbl;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import vo.systemvo.LogVO;
import message.OperationMessage;

/** 
 * Client//bl.blImpl.logbl//TXTHelper.java
 * @author CXWorks
 * @date 2015年12月5日 下午6:00:50
 * @version 1.0 
 */
public class TXTHelper {
	private static final String fileName="log.txt";
	public  OperationMessage exportToTXT(List<LogVO> logVOs,String path ){
		try {
			PrintWriter writer=new PrintWriter(new FileOutputStream(path+fileName)) ;
			for (LogVO logVO : logVOs) {
				writer.println(logVO.toString());
			}
			writer.flush();
			writer.close();
			return new OperationMessage();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return new OperationMessage(false, e.getMessage());
		}
	}
}
