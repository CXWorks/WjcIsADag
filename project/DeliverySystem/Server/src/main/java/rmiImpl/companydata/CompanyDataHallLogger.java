package rmiImpl.companydata;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import cache.VersionSaver;
import operation.Operation;
import rmiImpl.Logger;

/** 
 * Server//rmiImpl.companydata//CompanyDataHallLogger.java
 * @author CXWorks
 * @date Dec 23, 2015 11:39:08 PM
 * @version 1.0 
 */
public class CompanyDataHallLogger extends Logger {
	private static final String FILE_NAME="companyHall";
	
	public CompanyDataHallLogger(){
		super(FILE_NAME);
	}

	

}
