package userinfo;

import java.rmi.RemoteException;

import bl.clientNetCache.CacheHelper;
import po.memberdata.StaffTypeEnum;
import po.systemdata.SystemState;
import rmi.systemdata.SystemDataService;

/**
 * Client//userinfo//UserInfo.java
 * @author CXWorks
 * @date 2015年11月23日 下午10:40:37
 * @version 1.0
 */
public class UserInfo {
	private static String userID;
	private static SystemState systemState = SystemState.NORMAL;
	private static StaffTypeEnum staffType;
	private static String institutionID;
	private static String userName;
	private static String IP="localhost";
	private static String Port="2333";
	private static String workPath;
	private static boolean connected=true;
	private UserInfo(){
	}
	//
	private static boolean logined = true;
	public static String getUserID() {
		if (logined) {
			return userID;
		} else {
			return null;
		}
	}
	public static StaffTypeEnum getStaffType() {
		if (logined) {
			return staffType;
		} else {
			return null;
		}
	}
	public static String getInstitutionID() {
		if (logined) {
			return institutionID;
		} else {
			return null;
		}
	}

	public static String getUserName() {
		return userName;
	}

	public static SystemState getSystemState() {
		return systemState;
	}

	public static void setInfo(String userID, StaffTypeEnum staffTypeEnum, String institutionID
			,String userName){
		UserInfo.userID = userID;
		UserInfo.staffType = staffTypeEnum;
		UserInfo.institutionID = institutionID;
		UserInfo.logined = true;
		UserInfo.userName=userName;
	}

	public static void setSystemState(SystemState state){
		systemState = state;
	}
	
	public static void changeSystermState(){
		SystemDataService systemDataService=CacheHelper.getSystemDataService();
		try {
			UserInfo.systemState=systemDataService.checkSystemState();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//
	public static String getIP() {
		return IP;
	}
	public static void setIP(String iP) {
		IP = iP;
	}
	public static String getPort() {
		return Port;
	}
	public static void setPort(String port) {
		Port = port;
	}
	public static String getWorkPath() {
		return workPath;
	}
	public static void setWorkPath(String workPath) {
		UserInfo.workPath = workPath;
	}
	
}
