package rmi.companydata;
import java.util.ArrayList;

import message.OperationMessage;
import po.companydata.*;
/**
 * 
 * @author cxworks
 *2015/10/24
 */
public interface companyDataCenterService {
	/**
	 * 
	 * @return
	 */
	public ArrayList<CenterPO> getCenter();
	/**
	 * 
	 * @return
	 */
	public String newCenterID();
	/**
	 * 
	 * @param center
	 * @return
	 */
	public OperationMessage addHall(CenterPO center);
	/**
	 * 
	 * @param center
	 * @return
	 */
	public OperationMessage deleteCenter(CenterPO center);
	/**
	 * 
	 * @param center
	 * @return
	 */
	public OperationMessage modifyCenter(CenterPO center);
	
}
