package vo.logisticsvo;

/** 
 * Client//vo.logisticsvo//LogisticsInfo.java
 * @author CXWorks
 * @date Dec 31, 2015 2:55:46 PM
 * @version 1.0 
 */
public class LogisticsInfo {
	
	private String time;
	private String info;
	private LogisticsInfo(){}
	public static LogisticsInfo build(String time,String info){
		LogisticsInfo ans = new LogisticsInfo();
		ans.time = new String(time);
		ans.info = new String(info);
		return ans;
	}
	public String getTime() {
		return time;
	}
	public String getInfo() {
		return info;
	}
	
}
