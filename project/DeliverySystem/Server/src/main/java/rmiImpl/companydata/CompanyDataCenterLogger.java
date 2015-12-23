package rmiImpl.companydata;

import rmiImpl.Logger;

/** 
 * Server//rmiImpl.companydata//CompanyDataCenterLogger.java
 * @author CXWorks
 * @date Dec 24, 2015 12:19:30 AM
 * @version 1.0 
 */
public class CompanyDataCenterLogger extends Logger {
	private static final String FILE_NAME="companyCenter";
	public CompanyDataCenterLogger(){
		super(FILE_NAME);
	}
}
