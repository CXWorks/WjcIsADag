package factory;

import bl.blImpl.searchbl.SearchBLController;
import bl.blService.searchblService.SearchBLService;

/** 
 * Client//factory//SearchFactory.java
 * @author CXWorks
 * @date 2015年11月21日 下午11:24:17
 * @version 1.0 
 */
public class SearchFactory extends BLFactory {
	private static SearchBLService searchBLService=new SearchBLController();;
	public SearchFactory(){
		searchBLService=new SearchBLController();
	}
	public static SearchBLService getSearchBLService() {
		return searchBLService;
	}
	
}
