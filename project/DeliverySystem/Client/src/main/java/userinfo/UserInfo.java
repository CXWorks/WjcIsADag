package userinfo;

import po.memberdata.StaffTypeEnum;

/** 
 * Client//userinfo//UserInfo.java
 * @author CXWorks
 * @date 2015年11月23日 下午10:40:37
 * @version 1.0 
 */
public class UserInfo {
	private static String userID;
	private static StaffTypeEnum staffType;
	private static String institutionID;
	private UserInfo(){	
	}
	//
	private static boolean logined=false;
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
	
	public static void setInfo(String userID,StaffTypeEnum staffTypeEnum,String institutionID){
		UserInfo.userID=userID;
		UserInfo.staffType=staffTypeEnum;
		UserInfo.institutionID=institutionID;
		UserInfo.logined=true;
	}
}
